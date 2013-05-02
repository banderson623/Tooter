package Testing;

import instruments.Drums;
import instruments.Instrument;
import instruments.Piano;
import messaging.Song;

import java.util.LinkedList;
import java.util.Random;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public class BasicAudioTest {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Hello!");
        Instrument piano = new Piano();
        Song testSong = new Song();
        testSong.start();

//        Resources.piano.play(Resources.piano.getSupportedNotes().get(0));
//        Thread.sleep(1000);
//        Resources.piano.play(Resources.piano.getSupportedNotes().get(1));
        LinkedList<Instrument.Note> orchesta = new LinkedList<Instrument.Note>();

//        orchesta.addAll((new Gameboy()).getSupportedNotes());
//        orchesta.addAll((new DrumMachine()).getSupportedNotes());
//        orchesta.addAll((new Piano()).getSupportedNotes());
        orchesta.addAll((new Drums()).getSupportedNotes());
//        orchesta.addAll((new Brian()).getSupportedNotes());
//        orchesta.addAll((new Kmart()).getSupportedNotes());
        Random r = new Random();

//        for(int i = 0; i < soundKit.getSupportedNotes().size(); i++) {
//            Thread.sleep(500);
//            Resources.piano.play(soundKit.getSupportedNotes().get(i));
//        }

        for(int i = 0; i < 10; i++) {
            Thread.sleep(500 * r.nextInt(4));
            Instrument.Note noteToPlay = orchesta.get(r.nextInt(orchesta.size()));
//            testSong.addNote(noteToPlay);
            piano.play(noteToPlay);
        }
        System.out.println(testSong);
        System.out.println(testSong.toSerializedString());
        System.out.println("Finishing up...");
//        Thread.sleep(5000);
    }
}
