package messaging;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.communication.MessageHandlerFactory;

public class SongMessageHandlerFactory implements MessageHandlerFactory<Song> {
    @Override
    public MessageHandler build(Endpoint<Song> endpoint, byte[] bytes) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
