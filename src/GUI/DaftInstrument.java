package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tae Kim
 * Date: 5/2/13
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class DaftInstrument extends AbstractInstrument {


    public DaftInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.instrumentToPlay = new instruments.Daft();

        // Build these lovely notes after inspecting the instrument
        setupNotesForThisInstrument();

        // Add image to the image panel
//        JLabel instrumentsLabel = new JLabel();
//        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
//        instrumentsLabel.setIcon(new ImageIcon("Resources/images/drumtitle.jpg"));
//        titlePanel.add(instrumentsLabel);


        // Now we can add the prebuild notes to the instrument gui
        addNotesToButtonPanel();

        // add components
        this.addComponents();
    }

    @Override
    public String getInstrumentType() {
        return "daft";
    }
}

