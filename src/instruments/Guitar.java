package instruments;

/**
 * User: brian_anderson
 * Date: 4/14/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Guitar extends InstrumentBase {
    public Guitar(){
        addNotesFromWaveFilesInDirectory("Resources/guitar");
    }

    @Override
    public String getName()
    {
        return "The Shane";
    }
}
