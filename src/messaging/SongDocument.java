package messaging;

import com.digitalxyncing.document.impl.Document;
import com.digitalxyncing.document.impl.DocumentFragment;

public class SongDocument extends Document<Song> {


    public SongDocument(byte[] documentData) {
        super(documentData);
    }

    @Override
    protected byte[] serialize(Song song) {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean update(DocumentFragment<Song> songDocumentFragment) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Song getFullState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
