package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChooseInstrument extends JPanel {

    private String[] instrumentChoices = {"Piano", "Drums", "TurnTable"};

    public ChooseInstrument(final CardLayout cl, final JPanel panelCont) {
        JPanel chooseInstrument = new JPanel();
        chooseInstrument.setBackground(Color.WHITE);

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        // Instrument image
        JLabel chooseTitle = new JLabel();
        chooseTitle.setIcon(new ImageIcon("instruments.jpg"));
        topPanel.add(chooseTitle);

        // Choose your instrument
        // Add a Text Field "Choose your weapon."
        JLabel choiceText = new JLabel("Choose Your Instrument");
        topPanel.add(choiceText);

        // Add drop down
        final JComboBox<String> comboBox = new JComboBox<String>();
        for(int i = 0; i < instrumentChoices.length; i++)
            comboBox.addItem(instrumentChoices[i]);
        topPanel.add(comboBox);

        // Add submit button
        JButton submitBut = new JButton("Choose");
        submitBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, comboBox.getSelectedItem().toString());
            }
        });
        topPanel.add(submitBut);

        // Back Button
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "main");
            }

        });
        topPanel.add(back);

        chooseInstrument.add(topPanel);
        this.add(chooseInstrument);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
