package messaging;

import com.digitalxyncing.document.impl.DocumentFragment;

public class SongFragment extends DocumentFragment<Song> {

    private Song.Toot toot;

    public SongFragment(Song.Toot toot) {
        super(toot.toByteArray());
        this.toot = toot;
    }

    public Song.Toot getToot() {
        return toot;
    }
}
