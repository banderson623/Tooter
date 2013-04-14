package Testing;

import instruments.Instrument;
import instruments.Piano;

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
        piano.play(piano.getSupportedNotes().get(0));
        piano.play(piano.getSupportedNotes().get(1));
        piano.play(piano.getSupportedNotes().get(2));
        piano.play(piano.getSupportedNotes().get(3));
    }
}
