package userInterface;

import instruments.Instrument;
import instruments.Piano;
import messaging.Song;
import util.LibraryUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates a basic graphical way to play and compose music
 */
public class commandLinePerformer
{
    static
    {
        LibraryUtils.setLibraryPath();
    }

    public static void main(String[] args)
    {
        //Setup a piano
        Instrument piano = new Piano();

//        Song myRecordedSong = new Song();
//        myRecordedSong.intializeFromSerializedString("1367298182076*7136|piano/d.wav*7695|piano/e.wav*8271|piano/a.wav*9406|piano/c.wav*9990|piano/d.wav*11669|piano/a.wav");
//        myRecordedSong.play();
//        return;


        // Start a new song
        Song mySong = new Song();

        Map<Character,Instrument.Note> keys = new HashMap<Character, Instrument.Note>();

        char keyAt[] = {'a','s','d','f','g','h','z','x','c','v'};
        List<Instrument.Note> notes = piano.getSupportedNotes();
        int availableNotes = notes.size();
        for(int i = 0; i < notes.size() && i < keyAt.length; ++i){
            // Pair up notes and keys
            keys.put(keyAt[i],notes.get(i));
        }


        try {
            // print instructions
            System.out.println("-----------------------------------------------------------");
            System.out.println("Notes are mapped to keys...");
            System.out.println("Keys: " + keys.toString());
            System.out.println("----------------------------------------------------------");
            System.out.println("'q' to quit");
            System.out.println("----------------------------------------------------------");

            boolean exiting = false;
            mySong.start();
            while(!exiting){
                char input = (char)System.in.read();
                if(input >= 'a' && input <= 'z'){
                    if(keys.containsKey(input)){
                        Instrument.Note noteToPlay = keys.get(input);
                        System.out.println("Playing " + noteToPlay);
                        mySong.addNote(noteToPlay);
                        piano.play(noteToPlay);
                    } else if(input == 'q' || input == 'Q'){
                        exiting = true;
                    } else {
                        System.out.println("No note mapped to '" + input + "' key");
                    }
                }
            }

            System.out.println("Song");
            System.out.println(mySong);
            System.out.println(mySong.toSerializedString());
            System.out.println("Playing song...");
            //mySong.play();

        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }

    }
}
