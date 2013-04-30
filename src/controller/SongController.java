package controller;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.HostEndpoint;
import com.digitalxyncing.communication.impl.ZmqClientEndpoint;
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

    public void initialize() {
        System.out.println("Initializing song document");
        Song song = new Song();
        try {
            this.songDocument = new SongDocument(song);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (isHost) {
            HostEndpoint<Song> hostEndpoint = new ZmqHostEndpoint<Song>(HOST_PORT,
                    new SongMessageHandlerFactory());
            //hostEndpoint.addClient("127.0.0.1", CLIENT_PORT);
            this.endpoint = hostEndpoint;
        } else {
            this.endpoint = new ZmqClientEndpoint<Song>(hostAddress, hostPort, CLIENT_PORT,
                    new SongMessageHandlerFactory());
        }

        this.endpoint.openOutboundChannel();
        this.endpoint.openInboundChannel();
        System.out.println("Inbound and outbound channels opened");
        if (isHost) {
            System.out.println("Hosting at " + NetworkUtils.getIpAddress() + ":" + HOST_PORT);
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
