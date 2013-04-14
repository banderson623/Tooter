package instruments;

/**
 * User: brian_anderson
 * Date: 4/14/13
 * <p/>
 * Add some readme here about how this operates
 */
public class DrumMachine extends InstrumentBase {

    public DrumMachine(){
        addNotesFromWaveFilesInDirectory("roland_tr_77");
    }

    @Override
    public String getName()
    {
        return "Drums!";
    }

}
