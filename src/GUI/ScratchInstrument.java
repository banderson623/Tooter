package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * User: brian_anderson
 * Date: 5/2/13
 * <p/>
 * Add some readme here about how this operates
 */
public class ScratchInstrument  extends AbstractInstrument {

    public ScratchInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.instrumentToPlay = new instruments.Scratch();

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
        return "scratch";
    }
}
