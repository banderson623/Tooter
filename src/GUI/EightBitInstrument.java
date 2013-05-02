package GUI;

import instruments.Gameboy;

import javax.swing.*;
import java.awt.*;

public class EightBitInstrument extends AbstractInstrument {




    private String[] keys = {"GBA-SP BD1", "GBA-SP BD2", "GBA-SP BD3", "GBA-SP BD4", "GBA-SP Clap", "GBA-SP Perc1",
            "GBA-SP Perc2", "GBA-SP Perc3", "GBA-SP SD1", "GBA-SP SD2"};

    public EightBitInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.instrumentToPlay = new Gameboy();

        // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/images/guitartitle.jpg"));
        titlePanel.add(instrumentsLabel);


        // Create buttons for the instrument
        for (int i = 0; i < keys.length; i++) {
            final JButton key = new JButton(keys[i]);
            key.setPreferredSize(new Dimension(75, 200));
            key.setBackground(Color.WHITE);

            setUpListenersForNoteForKeyAtIndex(i, key, keys[i]);

            buttonPanel.add(key);
        }

        // add components
        this.addComponents();
    }
}
