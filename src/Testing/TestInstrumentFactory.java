package Testing;

import instruments.InstrumentFactory;

import java.util.List;

/**
 * User: brian_anderson
 * Date: 5/2/13
 * <p/>
 * Add some readme here about how this operates
 */
public class TestInstrumentFactory {

    public static void main(String[] args) {
        List<Class> instruments = InstrumentFactory.getListOfInstruments();
    }
}
