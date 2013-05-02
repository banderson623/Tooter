package GUI;


import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 5/2/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShipMyPantsInstrument extends AbstractInstrument {

    public ShipMyPantsInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.instrumentToPlay = new instruments.Kmart();

        // Build these lovely notes after inspecting the instrument
        setupNotesForThisInstrument();

        // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        titlePanel.add(instrumentsLabel);


        // Now we can add the prebuild notes to the instrument gui
        addNotesToButtonPanel();

        // add components
        this.addComponents();
    }

    @Override
    public String getInstrumentType() {
        return "kmart";
    }
}