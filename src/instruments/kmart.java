package instruments;

/**
 * User: brian_anderson
 * Date: 4/14/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Kmart extends InstrumentBase{

    public Kmart(){
        addNotesFromWaveFilesInDirectory("ship");
    }


    @Override
    public String getName()
    {
        return "Ship My Pants";
    }

}
