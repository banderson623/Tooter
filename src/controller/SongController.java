package controller;

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

    private static final int HOST_PORT = 5050;
    private static final int CLIENT_PORT = 4040;
    private static final int DISCOVERY_PORT = 3030;

    private SongDocument songDocument;
    private Piano piano = new Piano();
    private boolean isHost = true;
    private Endpoint<Song> endpoint;
    private String hostAddress;
    private int hostPort;

    public void isHost(boolean isHost) {
        this.isHost = isHost;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public void initialize(String password) {
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
            this.endpoint = new ZmqEndpointFactory().buildClientEndpoint(hostAddress, DISCOVERY_PORT, password,
                    Song.class, new SongMessageHandlerFactory());
//            this.endpoint = new ZmqClientEndpoint<Song>(hostAddress, hostPort, CLIENT_PORT,
//                    new SongMessageHandlerFactory());
        }

        this.endpoint.openOutboundChannel();
        this.endpoint.openInboundChannel();
        System.out.println("Inbound and outbound channels opened");
        if (isHost) {
            System.out.println("Hosting at " + NetworkUtils.getIpAddress() + ":" + HOST_PORT);
            System.out.println("Accepting connections at " + NetworkUtils.getIpAddress() + ":" + DISCOVERY_PORT);
        } else {
            System.out.println("Connected to " + hostAddress + ":" + hostPort);
        }

        song.start();
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
