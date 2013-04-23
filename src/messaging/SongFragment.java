package messaging;

import com.digitalxyncing.document.impl.DocumentFragment;
import instruments.Instrument;

public class SongFragment extends DocumentFragment<Song> {

    private Instrument.Note note;

    public SongFragment(Instrument.Note note) {
        this.data = note.id().getBytes();
        this.note = note;
    }

    public SongFragment(String fragmentString) {
        // TODO
    }

    public Instrument.Note getNote() {
        return note;
    }
}
