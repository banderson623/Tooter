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
        public String name();
    }


    public void play(Note noteToPlay);


    // At some point might need to play a note
    public List<Note> getSupportedNotes();

}
