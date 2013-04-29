package messaging;

import com.digitalxyncing.document.impl.DocumentFragment;
import instruments.Instrument;
import instruments.Piano;

/**
 * Represents a fragment of a {@link SongDocument}. For now, this is merely a single note of a song but it could be
 * more coarse grained and consist of several notes.
 */
public class SongFragment extends DocumentFragment<Song> {

    private Instrument.Note note;

    public SongFragment(Instrument.Note note) {
        this.note = note;
        this.data = note.getFileName().getBytes();
    }

    public SongFragment(String fragmentString) {
        // TODO This is crude, but using it for testing purposes...
        if (fragmentString.startsWith("piano")) {
            Piano piano = new Piano();
            String name = fragmentString.substring(fragmentString.indexOf("/") + 1, fragmentString.indexOf("."));
            this.note = piano.getNoteByName(name);
            this.data = note.getFileName().getBytes();
        } else {
            System.out.println("Unrecognized note " + fragmentString);
        }
    }

    public Instrument.Note getNote() {
        return note;
    }
}
