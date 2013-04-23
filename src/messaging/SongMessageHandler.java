package messaging;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.document.Message;
import com.digitalxyncing.document.impl.DocumentFragment;

public class SongMessageHandler extends MessageHandler<Song> {

    private SongDocument songDocument;

    public SongMessageHandler(SongDocument songDocument, Endpoint<Song> endpoint, byte[] message) {
        super(endpoint, message);
        this.songDocument = songDocument;
    }

    @Override
    protected void handle(byte[] bytes, int i, String origin, Message.MessageType messageType) {
        switch (messageType) {
            case DOCUMENT_FRAGMENT:
                String songFragmentString = new String(bytes, i, bytes.length - i);
                System.out.println(songFragmentString);
                DocumentFragment<Song> fragment = new SongFragment(songFragmentString);
                fragment.setOrigin(origin);
                songDocument.update(fragment);
                if (endpoint.isHost()) {
                    // Propagate update to clients
                    endpoint.send(fragment);
                }
                break;
            case FULL_DOCUMENT:
                // TODO
                break;
            case FULL_DOCUMENT_REQUEST:
                endpoint.send(songDocument);
                break;
        }
    }

}
