package Testing;

import instruments.DrumMachine;
import instruments.Instrument;
import instruments.Piano;

import java.util.Random;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public class BasicAudioTest {

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("Hello!");
        Instrument piano = new Piano();
        piano.play(piano.getSupportedNotes().get(0));
//        Thread.sleep(1000);
//        piano.play(piano.getSupportedNotes().get(1));
        Instrument drums = new DrumMachine();

        Random r = new Random();

        for(int i = 0; i < 50; i++){
            Thread.sleep(100 * r.nextInt(5));
            piano.play(piano.getSupportedNotes().get(r.nextInt(4)));
            piano.play(drums.getSupportedNotes().get(r.nextInt(drums.getSupportedNotes().size())));


        }
        Thread.sleep(3000);
    }
}
