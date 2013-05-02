package instruments;

/**
 * User: brian_anderson
 * Date: 4/14/13
 * <p/>
 * Add some readme here about how this operates
 */
public class Brian  extends InstrumentBase{

        public Brian(){
            addNotesFromWaveFilesInDirectory("Resources/brian");
        }

        @Override
        public String getName()
        {
            return "Brian";
        }
}
