package GUI;

import javax.swing.*;
import java.awt.*;

public class PianoInstrument extends AbstractInstrument {

    private String[] keys = {"A", "B", "C", "D", "E", "F", "G"};

    public PianoInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        instrumentToPlay = new instruments.Piano();

         // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/images/pianotitle.jpg"));
        titlePanel.add(instrumentsLabel);


        // Create buttons for the piano
        for (int i = 0; i < keys.length; i++) {
            final JButton key = new JButton(keys[i]);
            key.setPreferredSize(new Dimension(75, 200));
            key.setBackground(Color.WHITE);

            // this is generic enough :)
            setUpListenersForNoteForKeyAtIndex(i, key, keys[i]);

            buttonPanel.add(key);
        }

        // add components
        this.addComponents();
    }
}
