package GUI;

import controller.SongController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Piano extends JPanel {

    private SongController songController;

    public Piano(final CardLayout cl, final JPanel panelCont, boolean isHost) {
        JPanel pianoChoice = new JPanel();
        pianoChoice.setLayout(new GridLayout(3, 1, 5, 5));

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Add image to top panel
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("keys.jpg"));
        topPanel.add(label);

        // Add Title
        JLabel title = new JLabel();
        title.setIcon(new ImageIcon("pianotitle.png"));
        topPanel.add(title);

        // Create the keys panel
        JPanel keysPanel = new JPanel();
        keysPanel.setPreferredSize(new Dimension(200, 200));
        // Add the keys
        JButton keyA = new JButton("A");
        keyA.setPreferredSize(new Dimension(50, 250));
        keyA.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        JButton keyB = new JButton("B");
        keyB.setPreferredSize(new Dimension(50, 250));
        keyB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        JButton keyC = new JButton("C");
        keyC.setPreferredSize(new Dimension(50, 250));
        keyC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        JButton keyD = new JButton("D");
        keyD.setPreferredSize(new Dimension(50, 250));
        keyD.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        JButton keyE = new JButton("E");
        keyE.setPreferredSize(new Dimension(50, 250));
        keyE.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        JButton keyF = new JButton("F");
        keyF.setPreferredSize(new Dimension(50, 250));
        keyF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        JButton keyG = new JButton("G");
        keyG.setPreferredSize(new Dimension(50, 250));
        keyG.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        keysPanel.add(keyA);
        keysPanel.add(keyB);
        keysPanel.add(keyC);
        keysPanel.add(keyD);
        keysPanel.add(keyE);
        keysPanel.add(keyF);
        keysPanel.add(keyG);

        // Creates the bottom panel
        JPanel botPanel = new JPanel();

        // Create a button to go back
        JButton back = new JButton();
        back.setText("Choose a new instrument");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "choice");

            }

        });
        botPanel.add(back);


        pianoChoice.add(topPanel);
        pianoChoice.add(keysPanel);
        pianoChoice.add(botPanel);

        this.add(pianoChoice);

        this.songController = new SongController(isHost, null, 0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
