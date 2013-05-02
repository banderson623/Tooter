package messaging;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.communication.MessageHandlerFactory;

public class SongMessageHandlerFactory implements MessageHandlerFactory {

    @Override
    public MessageHandler build(Endpoint endpoint, byte[] bytes) {
        return new SongMessageHandler(endpoint, bytes);
    }

}
