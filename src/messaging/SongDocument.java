package messaging;

import com.digitalxyncing.document.impl.Document;
import com.digitalxyncing.document.impl.DocumentFragment;

import java.io.IOException;

public class SongDocument extends Document<Song> {

    private Song song;

    public SongDocument(Song song) throws IOException {
        super(song.toByteArray());
        this.song = song;
    }

    @Override
    protected byte[] serialize(Song song) {
        try {
            return song.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    @Override
    public boolean update(DocumentFragment<Song> songDocumentFragment) {
        return song.update(songDocumentFragment.getPrefixedByteArray());
    }

    @Override
    public Song getFullState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
