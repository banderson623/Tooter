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

    private String[] keys = {"E1", "B", "G", "D", "A", "E2"};

    public GuitarInstrument(final CardLayout cl, final JPanel mainPanel) {
        // Call Abstract Instrument's constructor
        super(cl, mainPanel);

        // Instantiate the instrument
        instrumentToPlay = new instruments.Guitar();

        // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/images/guitartitle.jpg"));
        titlePanel.add(instrumentsLabel);


        // Create buttons for the guitar
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

    @Override
    public String getInstrumentType() {
        return "guitar";
    }


}
