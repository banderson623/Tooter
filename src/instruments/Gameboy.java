package instruments;

/**
 * User: brian_anderson
 * Date: 4/14/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Gameboy extends InstrumentBase {

    public Gameboy(){
        addNotesFromWaveFilesInDirectory("gameboy");
    }


    @Override
    public String getName()
    {
        return "8Bit4Life";
    }
}
