package instruments;

/**
 * My favorite instrument
 */
public class Kmart extends InstrumentBase {
    public Kmart(){
        addNotesFromWaveFilesInDirectory("ship");
    }

    @Override
    public String getName()
    {
        return "Kmart";
    }
}
