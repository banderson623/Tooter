package GUI;

import instruments.Gameboy;
import instruments.Instrument;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class EightBitInstrument extends AbstractInstrument {


    public EightBitInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.instrumentToPlay = new Gameboy();

        // Build these lovely notes after inspecting the instrument
        setupNotesForThisInstrument();

        // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/Resources.images/8bittitle.jpg"));
        titlePanel.add(instrumentsLabel);

        // Now we can add the prebuild notes to the instrument gui
        addNotesToButtonPanel();


        // add components
        this.addComponents();
    }



    @Override
    public String getInstrumentType() {
        return "8bit";
    }
}
