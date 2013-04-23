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
    public boolean update(DocumentFragment<Song> songDocumentFragment) {
        song.addNote(((SongFragment)songDocumentFragment).getNote());
        return true;
    }

    @Override
    public Song getFullState() {
        return song;
    }
}
