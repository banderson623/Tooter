package messaging;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.communication.MessageHandlerFactory;

public class SongMessageHandlerFactory implements MessageHandlerFactory<Song> {

    @Override
    public MessageHandler build(Endpoint<Song> endpoint, byte[] bytes) {
        return new SongMessageHandler(endpoint, bytes);
    }

}
