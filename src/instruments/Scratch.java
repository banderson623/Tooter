package instruments;

/**
 DJ Heros!
 */

/**
 * My favorite instrument
 */
public class Scratch extends InstrumentBase {

    public Scratch(){
        addNotesFromWaveFilesInDirectory("scratch");
    }

    @Override
    public String getName()
    {
        return "DJ Hero!";
    }
}


