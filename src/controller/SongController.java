package controller;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.impl.ZmqClientEndpoint;
import com.digitalxyncing.communication.impl.ZmqHostEndpoint;
import instruments.Instrument;
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
        Song song = new Song();
        try {
            this.songDocument = new SongDocument(song);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.endpoint = isHost ? new ZmqHostEndpoint<Song>(HOST_PORT, new SongMessageHandlerFactory(songDocument)) :
                new ZmqClientEndpoint<Song>(hostAddress, hostPort, CLIENT_PORT,
                        new SongMessageHandlerFactory(songDocument));
        this.endpoint.openOutboundChannel();
        this.endpoint.openInboundChannel();
        System.out.println("Inbound and outbound channels opened");
        System.out.println("Hosting: " + isHost);
        if (isHost) {
            System.out.println("Hosting at " + NetworkUtils.getIpAddress() + ":" + HOST_PORT);
        } else {
            System.out.println("Connected to " + hostAddress + ":" + hostPort);
        }
        song.start();
    }

    public void play(Instrument.Note note) {
        System.out.println(note.name());
        SongFragment fragment = new SongFragment(note);
        piano.play(note);
        songDocument.update(fragment);

        // Propagate the change
        endpoint.send(fragment);
    }

    public void terminate() {
        if (endpoint != null) {
            endpoint.closeInboundChannel();
            endpoint.closeOutboundChannel();
            System.out.println("Channels closed");
        }
    }

    public String getClientAddress() {
        return NetworkUtils.getIpAddress() + ":" + CLIENT_PORT;
    }

    public String getHostingAddress() {
        return NetworkUtils.getIpAddress() + ":" + HOST_PORT;
    }

}
