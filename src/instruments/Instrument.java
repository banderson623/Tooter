package instruments;

import java.util.List;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * Simple intstrument interface for real playing together
 *
 */
public interface Instrument {

    public interface Note {
        /**
         * A unique id for this note.
         * @return
         */
        public String id();
        public String name();
        public String getFileName();
    }

    public String getName();

    public void play(Note noteToPlay);
    public void play(List<Note> notesToPlay);


    // At some point might need to play a note
    public List<Note> getSupportedNotes();

}
