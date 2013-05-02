package GUI;

import instruments.Gameboy;
import messaging.SongFragment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EightBitInstrument extends AbstractInstrument {


    private Gameboy gameboy;


    private String[] keys = {"GBA-SP BD1", "GBA-SP BD2", "GBA-SP BD3", "GBA-SP BD4", "GBA-SP Clap", "GBA-SP Perc1",
            "GBA-SP Perc2", "GBA-SP Perc3", "GBA-SP SD1", "GBA-SP SD2"};

    public EightBitInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.gameboy = new Gameboy();

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
            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    SongFragment fragment = new SongFragment(gameboy.getNoteByName(key.getText()));
                    Session.songController.play(fragment, true);
                }
            });
            buttonPanel.add(key);
        }

        // add components
        this.addComponents();
    }
}
