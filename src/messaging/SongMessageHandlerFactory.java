package messaging;

import com.digitalxyncing.communication.Endpoint;
import com.digitalxyncing.communication.MessageHandler;
import com.digitalxyncing.communication.MessageHandlerFactory;

public class SongMessageHandlerFactory implements MessageHandlerFactory<Song> {

    private SongDocument songDocument;

    public SongMessageHandlerFactory(SongDocument songDocument) {
        this.songDocument = songDocument;
    }

    @Override
    public MessageHandler build(Endpoint<Song> endpoint, byte[] bytes) {
        return new SongMessageHandler(songDocument, endpoint, bytes);
    }

}
