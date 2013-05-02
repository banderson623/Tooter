package messaging;

import com.digitalxyncing.document.impl.DocumentFragment;
import instruments.*;

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
        if (fragmentString.startsWith("Resources/piano")) {
            Piano piano = new Piano();
            String name = fragmentString.substring(fragmentString.lastIndexOf("/") + 1, fragmentString.indexOf("."));
            this.note = piano.getNoteByName(name);
            this.data = note.getFileName().getBytes();
        } else if (fragmentString.startsWith("Resources/drums")) {
            Drums drums = new Drums();
            String name = fragmentString.substring(fragmentString.lastIndexOf("/") + 1, fragmentString.indexOf("."));
            this.note = drums.getNoteByName(name);
            this.data = note.getFileName().getBytes();
        } else if (fragmentString.startsWith("Resources/guitar")) {
            Guitar guitar = new Guitar();
            String name = fragmentString.substring(fragmentString.lastIndexOf("/") + 1, fragmentString.indexOf("."));
            this.note = guitar.getNoteByName(name);
            this.data = note.getFileName().getBytes();
        } else if (fragmentString.startsWith("Resources/gameboy")) {
            Gameboy gameboy = new Gameboy();
            String name = fragmentString.substring(fragmentString.lastIndexOf("/") + 1, fragmentString.indexOf("."));
            this.note = gameboy.getNoteByName(name);
            this.data = note.getFileName().getBytes();
        } else if (fragmentString.startsWith("Resources/ship")) {
            Kmart kmart = new Kmart();
            String name = fragmentString.substring(fragmentString.lastIndexOf("/") + 1, fragmentString.indexOf("."));
            this.note = kmart.getNoteByName(name);
            this.data = note.getFileName().getBytes();
        } else {
            System.out.println("Unrecognized note " + fragmentString);
        }
    }

    public Instrument.Note getNote() {
        return note;
    }
}
