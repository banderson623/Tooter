package GUI;

import controller.SongController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChooseInstrument extends JPanel {
    private JPanel titlePanel;
    private JPanel choosePanel;
    private JPanel passwordPanel;
    private JPanel backPanel;
    final private JPasswordField passwordField;
    private String[] instrumentChoices = {"piano", "drums", "guitar", "8bit", "kmart","daft","DJ Hero"};



    public ChooseInstrument(final CardLayout cl, final JPanel mainPanel) {
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
        for (int i = 0; i < instrumentChoices.length; i++)
            comboBox.addItem(instrumentChoices[i]);
        choosePanel.add(comboBox);



        // Add Choose button
        JButton chooseButton = new JButton();
        chooseButton.setText("Play with this instrument");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.copyValueOf(passwordField.getPassword());
                if (password.length() == 0) {

                    //use default password - Brian added. Passwords are just annoying now..
                    password = "default";
                    // If you don't want this then we should add
                    // JOptionPane.showMessageDialog(mainPanel, "Please enter a password");
                }
                // We have a problem here.

                SongController.Status status = Session.songController.initializeOrUseExisting(password); //initialize(password);
                switch (status) {
                    case OK:
                        if (Session.ipToConnectTo != null) {
                            for (SessionListener listener : Session.sessionListeners) {
                                listener.onSessionJoin(Session.ipToConnectTo, SongController.HOST_PORT);
                            }
                        }
                        // Yikes!
                        buildAndShowSelectedInstrument(cl,mainPanel,comboBox.getSelectedItem().toString());
//                        cl.show(mainPanel, comboBox.getSelectedItem().toString());
//                        ChosenInstrument.instrumentChosen = comboBox.getSelectedItem().toString();
                        break;
                    case INVALID_PASSWORD:
                        JOptionPane.showMessageDialog(mainPanel, "Incorrect password!");
                        break;
                    case ERROR:
                        JOptionPane.showMessageDialog(mainPanel, "Something went horribly wrong!");
                        break;
                }
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session.songController.terminate();
                Session.songController.isHost(true);
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

    // Responsibly shows the instrument to be played
    protected void buildAndShowSelectedInstrument(final CardLayout cl,
                                                  final JPanel mainPanel,
                                                  String instrumentName){

        // Get all the components in the main panel
        Component[] comps = mainPanel.getComponents();
        for(int i = 0; i < comps.length; i++){
            // If we have set a name...
            if(comps[i].getName() != null){
                // and the name is the same as the instrument name we are switching too
                if(comps[i].getName().equals(instrumentName)){
                    // and we can cast...
                    AbstractInstrument isInstrument = (AbstractInstrument) comps[i];
                    if(isInstrument != null){
                        // ... then we better setup that instrument!
                        isInstrument.activateInstrument();
                    }
                }
            }
        }
        cl.show(mainPanel, instrumentName);
    }

}
