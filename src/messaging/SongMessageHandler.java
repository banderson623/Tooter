package messaging;

import GUI.Session;
import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.document.Message;

public class SongMessageHandler extends MessageHandler<Song> {

    public SongMessageHandler(Endpoint<Song> endpoint, byte[] message) {
        super(endpoint, message);
    }

    @Override
    protected void handle(byte[] bytes, int i, String origin, Message.MessageType messageType) {
        switch (messageType) {
            case DOCUMENT_FRAGMENT:
                // Update the document
                String songFragmentString = new String(bytes, i, bytes.length - i);
                System.out.println("Handler: " + songFragmentString);
                SongFragment fragment = new SongFragment(songFragmentString);
                fragment.setOrigin(origin);
                Session.songController.play(fragment, endpoint.isHost());
                break;
            case FULL_DOCUMENT:
                // TODO: Reconcile the complete document, perhaps by simply setting the SongController's document
                break;
            case FULL_DOCUMENT_REQUEST:
                // Send the complete document
                endpoint.send(Session.songController.getDocument());
                break;
        }
    }

}
