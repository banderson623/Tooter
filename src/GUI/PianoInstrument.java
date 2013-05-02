package GUI;

import messaging.Song;
import messaging.SongFragment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PianoInstrument extends AbstractInstrument {


    private instruments.Piano piano;


    private String[] keys = {"A", "B", "C", "D", "E", "F", "G"};

    public PianoInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        this.piano = new instruments.Piano();

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
            key.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    SongFragment fragment = new SongFragment(piano.getNoteByName(key.getText()));
                    Session.songController.play(fragment, true);
                }
            });
            buttonPanel.add(key);
        }

        // add components
        this.addComponents();
    }
}
