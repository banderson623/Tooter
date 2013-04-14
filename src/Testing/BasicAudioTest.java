package Testing;

import instruments.Instrument;
import instruments.Piano;

import java.util.ArrayList;
import java.util.List;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public class BasicAudioTest {

    public static void main(String[] args){
        System.out.println("Hello!");
        Instrument piano = new Piano();
        List<Instrument.Note> notes = new ArrayList<Instrument.Note>();
        notes.add(piano.getSupportedNotes().get(0));
        notes.add(piano.getSupportedNotes().get(1));
        notes.add(piano.getSupportedNotes().get(2));
        notes.add(piano.getSupportedNotes().get(3));
        piano.play(notes);
    }
}
