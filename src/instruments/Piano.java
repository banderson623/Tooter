package instruments;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Piano extends InstrumentBase {

    public Piano(){
        addNotesFromWaveFilesInDirectory("Resources/piano");
    }

    @Override
    public String getName()
    {
        return "Baby Grand Piano";
    }


}
