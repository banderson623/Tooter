package messaging;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.communication.MessageHandlerFactory;

public class SongMessageHandlerFactory<Song> implements MessageHandlerFactory {
    @Override
    public MessageHandler build(Endpoint endpoint, byte[] bytes) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
