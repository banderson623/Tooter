package instruments;

/**
 * User: brian_anderson
 * Date: 4/29/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Drums extends InstrumentBase {

    public Drums(){
        addNotesFromWaveFilesInDirectory("Resources/drums");
    }

    @Override
    public String getName()
    {
        return "Drum";
    }
}