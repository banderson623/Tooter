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

    public static enum Status { INVALID_PASSWORD, ERROR, OK }

    public static final int HOST_PORT = 5050;
    public static final int DISCOVERY_PORT = 3030;

    private SongDocument songDocument;
    private Piano piano = new Piano();
    private boolean isHost = true;
    private Endpoint<Song> endpoint;

    public void isHost(boolean isHost) {
        this.isHost = isHost;
    }

    public Status initialize(String password) {
        System.out.println("Initializing song document");
        Song song = new Song();
        try {
            this.songDocument = new SongDocument(song);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isHost) {
            this.endpoint = new ZmqHostEndpoint<Song>(HOST_PORT, DISCOVERY_PORT,
                    new SongMessageHandlerFactory(), new SessionAuthenticator(password));
        } else {
            this.endpoint = new ZmqEndpointFactory().buildClientEndpoint(Session.ipToConnectTo, DISCOVERY_PORT, password,
                    Song.class, new SongMessageHandlerFactory());
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
            System.out.println("Accepting connections at " + NetworkUtils.getIpAddress() + ":" + DISCOVERY_PORT);
        } else {
            System.out.println("Connected to " + Session.ipToConnectTo + ":" + HOST_PORT);
        }

        song.start();

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
            System.out.println("Channels closed");
        }
    }

    public String getHostingAddress() {
        return NetworkUtils.getIpAddress() + ":" + HOST_PORT;
    }

    public void addClient(String address, int port) {
        if (isHost) {
            ((ZmqHostEndpoint<Song>) endpoint).addClient(address, port);
        }
    }

    public SongDocument getDocument() {
        return songDocument;
    }

}
