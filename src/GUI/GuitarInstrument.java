package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tae Kim
 * Date: 5/2/13
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class GuitarInstrument extends AbstractInstrument {



    public GuitarInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        instrumentToPlay = new instruments.Guitar();

        // Here we are limiting what is shown in the in the GUI
        String[] keysAvailable = {"E1", "B", "G", "D", "A", "E2"};
        setupNotesForThisInstrument(keysAvailable);

        // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/Resources.images/guitartitle.jpg"));
        titlePanel.add(instrumentsLabel);

        // Now we can add the pre-
        // build notes to the instrument gui
        addNotesToButtonPanel();

        // add components
        this.addComponents();
    }

    @Override
    public String getInstrumentType() {
        return "Resources/guitar";
    }


}
