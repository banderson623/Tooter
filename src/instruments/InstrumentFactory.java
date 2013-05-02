package instruments;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: brian_anderson
 * Date: 5/1/13
 * <p/>
 * Add some readme here about how this operates
 */
public class InstrumentFactory {

    /**
     * Static method
     * Returns a list of instrument classes.
     * @return
     */
    public static List<Class> getListOfInstruments(){
        List<Class> instruments = new ArrayList<Class>();


        String pathToInstruments = (new InstrumentFactory()).getClass().getClassLoader().getResource(".").getPath();
        String packageName = "instruments";
        pathToInstruments += "../../../src/instruments/";


        File dir = new File(pathToInstruments);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".java"); }
        });

        for(File file : files){
            String className = file.getName().replace(".java", "");
            try
            {
                Class instrument = Class.forName(packageName + "." + className);
                Class supers = instrument.getSuperclass();
                if(supers != null){
                    if(supers.getName().contains("InstrumentBase")){
                        instruments.add(instrument);
                    }
                }

            } catch (ClassNotFoundException ignored){
            }
        }

        return instruments;
    }
}
