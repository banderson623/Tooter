package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class ChooseInstrument extends JPanel{
    private JPanel titlePanel;
    private JPanel choosePanel;
    private JPanel passwordPanel;
    private JPanel backPanel;
    final private JPasswordField passwordField;

    private String[] instrumentChoices = {"Piano", "Drums", "TurnTable"};

    public ChooseInstrument(final CardLayout cl, final JPanel mainPanel){
        // Set the size, color and layout of this panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(1000, 800));
        this.setBackground(Color.WHITE);

        // Panel for the Title
        titlePanel = new JPanel();
        titlePanel.setSize(new Dimension(1000, 400));
        titlePanel.setBackground(Color.WHITE);

        // Add image to the image panel
        JLabel instrumentsLabel = new JLabel();
        instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        instrumentsLabel.setIcon(new ImageIcon("Resources/images/instruments.jpg"));
        titlePanel.add(instrumentsLabel);

        // Panel for the dropdown menu and choose button
        choosePanel = new JPanel();
        choosePanel.setSize(new Dimension(1000, 300));
        choosePanel.setBackground(Color.WHITE);

        // Add dropdown menu with instruments
        final JComboBox<String> comboBox = new JComboBox<String>();
        for(int i = 0; i < instrumentChoices.length; i++)
            comboBox.addItem(instrumentChoices[i]);
        choosePanel.add(comboBox);

        // Add Choose button
        JButton chooseButton = new JButton();
        chooseButton.setText("Choose");
        chooseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, comboBox.getSelectedItem().toString());
                String password = String.copyValueOf(passwordField.getPassword());
                Session.songController.initialize(password);
            }
        });
        choosePanel.add(chooseButton);

        // Panel for the password input
        passwordPanel = new JPanel();
        passwordPanel.setSize(new Dimension(1000, 50));
        passwordPanel.setBackground(Color.WHITE);

        // Add password label and input box
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password: ");
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Panel for the back button
        backPanel = new JPanel();
        backPanel.setSize(new Dimension(1000, 50));
        backPanel.setBackground(Color.WHITE);

        // Add back button
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Session.songController.terminate();
                cl.show(mainPanel, "splash");

            }
        });
        backPanel.add(backButton);

        // Add panels to the main panel
        this.add(titlePanel);
        this.add(choosePanel);
        this.add(passwordPanel);
        this.add(backPanel);
    }

}
