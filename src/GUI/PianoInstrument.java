package GUI;

import javax.swing.*;
import java.awt.*;

public class PianoInstrument extends AbstractInstrument {

    public PianoInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        instrumentToPlay = new instruments.Piano();

        // Build these lovely notes after inspecting the instrument
        setupNotesForThisInstrument();

         // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/images/pianotitle.jpg"));
        titlePanel.add(instrumentsLabel);

        // Now we can add the pre-build notes to the instrument gui
        addNotesToButtonPanel();

        // add components
        this.addComponents();
    }

    @Override
    public String getInstrumentType() {
        return "piano";
    }
}
