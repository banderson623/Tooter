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

import java.io.IOException;
import java.net.InetAddress;

public class SongController {

    private static final int HOST_PORT = 5050;
    private static final int CLIENT_PORT = 4040;

    private SongDocument songDocument;
    private Piano piano;
    private boolean isHost;
    private Endpoint<Song> endpoint;

    public SongController(boolean isHost, String hostAddress, int hostPort) {
        this.isHost = isHost;
        this.piano = new Piano();
        this.endpoint = isHost ? new ZmqHostEndpoint<Song>(HOST_PORT, new SongMessageHandlerFactory()) :
                new ZmqClientEndpoint<Song>(hostAddress, hostPort, CLIENT_PORT, new SongMessageHandlerFactory());
        this.endpoint.openOutboundChannel();
        this.endpoint.openInboundChannel();
        try {
            if (isHost) {
                System.out.println("Hosting at " + InetAddress.getLocalHost().getHostAddress() + ":" + HOST_PORT);
            } else {
                System.out.println("Connected to " + hostAddress + ":" + hostPort);
            }
            Song song = new Song();
            song.start();
            this.songDocument = new SongDocument(song);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void play(Instrument.Note note) {
        Song.Toot toot = new Song.Toot();
        toot.add(note);
        SongFragment fragment = new SongFragment(toot);
        piano.play(note);
        songDocument.update(fragment);

    }

    public void terminate() {
        if (endpoint != null) {
            endpoint.closeInboundChannel();
            endpoint.closeOutboundChannel();
        }
    }

}
