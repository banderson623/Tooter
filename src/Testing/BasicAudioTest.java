package Testing;

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
        Thread.sleep(1000);
        piano.play(piano.getSupportedNotes().get(1));

        Random r = new Random();

        for(int i = 0; i < 10; i++){
            Thread.sleep(200 * r.nextInt(5));
            piano.play(piano.getSupportedNotes().get(r.nextInt(4)));

        }
        Thread.sleep(3000);


//        piano.play(piano.getSupportedNotes().get(1));
//        piano.play(piano.getSupportedNotes().get(2));
//        piano.play(piano.getSupportedNotes().get(3));
    }
}
