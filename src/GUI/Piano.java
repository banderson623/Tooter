package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Piano extends JPanel implements SessionListener {

    private String[] keyNames = {"A", "B", "C", "D", "E", "F", "G"};
    private instruments.Piano piano;
    private JLabel status;


    public Piano(final CardLayout cl, final JPanel panelCont) {
        JPanel pianoChoice = new JPanel();
        pianoChoice.setLayout(new GridLayout(5, 1, 0, 0));
        pianoChoice.setPreferredSize(new Dimension(600, 750));
        pianoChoice.setBackground(Color.WHITE);

        this.piano = new instruments.Piano();

        // Add image to top panel
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("Resources/images/keys.jpg"));
        pianoChoice.add(label);

        // Add Title
        JLabel title = new JLabel();
        title.setIcon(new ImageIcon("Resources/images/pianotitle.png"));
        pianoChoice.add(title);

        // Create the keys panel
        JPanel keysPanel = new JPanel();
        keysPanel.setBackground(Color.WHITE);

        // Add the keys
        for (int i = 0; i < keyNames.length; i++) {
            final JButton key = new JButton(keyNames[i]);
            key.setPreferredSize(new Dimension(50, 120));
            key.setBackground(Color.WHITE);
            key.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Session.songController.play(piano.getNoteByName(key.getText()));
                }

            });
            keysPanel.add(key);
        }


        // Creates the bottom panel
        JPanel botPanel = new JPanel();
        botPanel.setBackground(Color.WHITE);
        // Create a button to go back
        JButton back = new JButton();
        back.setText("Choose a new instrument");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Terminate the session
                Session.songController.terminate();
                cl.show(panelCont, "choice");

            }

        });
        botPanel.add(back);

        // Create the IP Panel
        JPanel ipPanel = new JPanel();
        ipPanel.setBackground(Color.WHITE);
        // Add a label for IP and Port info
        status = new JLabel();
        status.setText("You're hosting at " + Session.songController.getClientAddress() + ".");
        ipPanel.add(status);

        pianoChoice.add(keysPanel);
        pianoChoice.add(botPanel);
        pianoChoice.add(ipPanel);
        this.add(pianoChoice);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void onSessionJoin(final String address, final int port) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                status.setText("You're connected to " + address + ":" + port);
            }
        });
    }
}
