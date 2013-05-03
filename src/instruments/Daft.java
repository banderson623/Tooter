package instruments;

/**
DAFT PUNK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

/**
 * My favorite instrument
 */
public class Daft extends InstrumentBase {

    public Daft(){
        addNotesFromWaveFilesInDirectory("daft");
    }

    @Override
    public String getName()
    {
        return "Daft";
    }
}

