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

public class SongController {

    private SongDocument songDocument;
    private Piano piano;
    private boolean isHost;
    private Endpoint<Song> endpoint;

    public SongController(boolean isHost) {
        this.isHost = isHost;
        this.piano = new Piano();
        this.endpoint = isHost ? new ZmqHostEndpoint<Song>(5050, new SongMessageHandlerFactory()) :
                new ZmqClientEndpoint<Song>("127.0.0.1", 5050, 4040, new SongMessageHandlerFactory());
        try {
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
