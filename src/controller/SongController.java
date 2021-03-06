package controller;

import GUI.Session;
import authentication.SessionAuthenticator;
import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.impl.ZmqEndpointFactory;
import com.digitalxyncing.communication.impl.ZmqHostEndpoint;
import instruments.Piano;
import messaging.Song;
import messaging.SongDocument;
import messaging.SongFragment;
import messaging.SongMessageHandlerFactory;
import util.NetworkUtils;

import java.io.IOException;

public class SongController {

    public static enum Status {INVALID_PASSWORD, ERROR, OK}

    public static final int HOST_PORT = 5050;
    public static final int DISCOVERY_PORT_INITIAL = 3030;

    private SongDocument songDocument;
    private Piano piano = new Piano();
    private boolean isHost = true;
    private Endpoint endpoint;

    private boolean hasBeenInitialized = false;

    public void isHost(boolean isHost) {
        this.isHost = isHost;
    }

    // This is encountered when we hit the back button,
    // same session and we don't need to initialize
    public Status initializeOrUseExisting(String password) {
        Status status = Status.OK;
        if (!hasBeenInitialized) {
            status = initialize(password);
        } else {
            // nothing?
        }
        return status;
    }

    public Status initialize(String password) {


        // Brian added...
        // if the password is null, blank, empty then use default
        if (password.length() == 0) {
            password = "default"; // this just makes it easier for testing
        }

        System.out.println("Initializing song document");

        Song song = new Song();
        try {
            this.songDocument = new SongDocument(song);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isHost) {
            this.endpoint = new ZmqHostEndpoint<Song>(HOST_PORT, DISCOVERY_PORT_INITIAL,
                    new SongMessageHandlerFactory(), new SessionAuthenticator(password));
        } else {
            this.endpoint = new ZmqEndpointFactory().buildClientEndpoint(Session.ipToConnectTo,
                    DISCOVERY_PORT_INITIAL, password,
                    new SongMessageHandlerFactory());
            if (this.endpoint == null) {
                // The connection was refused
                return Status.INVALID_PASSWORD;
            }
        }

        if (this.endpoint == null) {
            return Status.ERROR;
        }

        this.endpoint.openOutboundChannel();
        this.endpoint.openInboundChannel();
        System.out.println("Inbound and outbound channels opened");

        if (isHost) {
            System.out.println("Hosting at " + NetworkUtils.getIpAddress() + ":" + HOST_PORT);
            System.out.println("Accepting connections at " + NetworkUtils.getIpAddress() + ":" +
                    DISCOVERY_PORT_INITIAL);
        } else {
            System.out.println("Connected to " + Session.ipToConnectTo + ":" + HOST_PORT);
        }

        song.start();

        hasBeenInitialized = true;

        return Status.OK;
    }

    /**
     * Plays the given {@link SongFragment} and updates the {@link SongDocument}.
     *
     * @param fragment  the {@code SongFragment} to play
     * @param propagate indicates if the update should be propagated to other peers
     */
    public void play(SongFragment fragment, boolean propagate) {
        System.out.println(fragment.getNote().name());
        piano.play(fragment.getNote());
        songDocument.update(fragment);

        if (propagate) {
            // Propagate the change
            endpoint.send(fragment);
        }
    }

    /**
     * Play wrapper method to play {@link messaging.Song.Toot}.
     *
     * @param toot the {@code Toot} to play
     */
    public void play(Song.Toot toot) {
        piano.play(toot.getNotes());
    }

    public void terminate() {
        if (endpoint != null) {
            endpoint.closeInboundChannel();
            endpoint.closeOutboundChannel();
            hasBeenInitialized = false;
            System.out.println("Channels closed");
        }
    }

    public String getHostingAddress() {
        return NetworkUtils.getIpAddress() + ":" + HOST_PORT;
    }

    public SongDocument getDocument() {
        return songDocument;
    }

}
