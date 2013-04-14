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
        public String getName();
        public String getFileForNote();
    }

    /**
     * Plays the note.
     * @param noteToPlay
     */
    public void play(Note noteToPlay);

    /**
     * The name of the instrument
     * @return the String name of the instrument
     */
    public String getName();

    /**
     * List of notes that this instruments can play
     * @return a list of Note Interface types
     */
    public List<Note> getSupportedNotes();

}
