package instruments;

import java.util.LinkedList;
import java.util.List;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Piano extends InstrumentBase {

    public class NoteA implements Note {

        @Override
        public String name()
        {
            return "A";
        }

        @Override
        public String getFileName(){
            return "playA.wav";
        }

    }

    public class NoteB implements Note {

        @Override
        public String name()
        {
            return "B";
        }

        @Override
        public String getFileName(){
            return "playA.wav";
        }
    }


    @Override
    public void play(Note noteToPlay)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName()
    {
        return "Baby Grand Piano";
    }

    @Override
    public List<Note> getSupportedNotes()
    {
        List<Note> myNotes = new LinkedList<Note>();
        myNotes.add(new NoteA());
        myNotes.add(new NoteB());
        return myNotes;
    }
}
