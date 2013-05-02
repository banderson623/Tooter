package GUI;

import instruments.Instrument;
import messaging.SongFragment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Tae Kim
 * Date: 5/2/13
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractInstrument extends JPanel implements SessionListener{

    protected JPanel titlePanel;
    protected JPanel buttonPanel;
    protected JPanel ipPanel;
    protected JPanel backPanel;
    protected JLabel ipLabel;
    protected char[] keyboardKeysToUse = {'a','s','d','f','g','h','j','k'};
    protected Instrument instrumentToPlay;
    final JPanel mainPanel;
    private AbstractInstrument thisInstrument;

    // is the instrument playbable now?
    protected boolean enabled;


    public AbstractInstrument(final CardLayout cl, final JPanel mainPanel){
        // Set the layout, size, and color of each instrument
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(1000, 800));
        this.setBackground(Color.WHITE);

        this.mainPanel = mainPanel;
        this.thisInstrument =  this;

        // Set default to piano
        instrumentToPlay = new instruments.Piano();

        // remove keyboard events, from last instrument?
        // I wish Java had a destructor....
//        cleanUpKeyBindings();

        // Panel for the Title
        titlePanel = new JPanel();
        titlePanel.setSize(new Dimension(1000, 400));
        titlePanel.setBackground(Color.WHITE);

        // Panel for the buttons
        buttonPanel = new JPanel();
        buttonPanel.setSize(new Dimension(1000, 300));
        buttonPanel.setBackground(Color.WHITE);


        // Panel for the IP info and back button
        ipPanel = new JPanel();
        ipPanel.setSize(new Dimension(1000, 50));
        ipPanel.setBackground(Color.WHITE);

        // Add ip information
        ipLabel = new JLabel();
        ipLabel.setText("You are hosting at: " + Session.songController.getHostingAddress() + ".");
        ipPanel.add(ipLabel);

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
                cl.show(mainPanel, "choose");
                ChosenInstrument.instrumentChosen = "";
            }
        });
        backPanel.add(backButton);

        // Add save song button
        JButton saveButton = new SaveSongButton(mainPanel);
        backPanel.add(saveButton);


    }

    protected void cleanUpKeyBindings(){
        // Clean up keybindings!
        mainPanel.getActionMap().clear();
        mainPanel.getInputMap().clear();
    }



    protected void setUpListenersForNoteForKeyAtIndex(
                                         int i,
                                         final JButton key,
                                         final String noteName)
    {
        // going to reuse this.. moved it out :)
        AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(thisInstrument.getInstrumentType().equals(ChosenInstrument.instrumentChosen)){
                    SongFragment fragment = new SongFragment(instrumentToPlay.getNoteByName(noteName));
                    Session.songController.play(fragment, true);
                }
            }
        };

        key.addActionListener(buttonPressed);

        // If we are less than the home row, then add a keyboard listening too
        if(i < keyboardKeysToUse.length){
            String keyboardKeyKey = "key_" + keyboardKeysToUse[i];
            // Register this key down input, to trigger an action in the map...
            mainPanel.getInputMap().put(KeyStroke.getKeyStroke(keyboardKeysToUse[i]),keyboardKeyKey);
            // define the map's action here...
            mainPanel.getActionMap().put(keyboardKeyKey,buttonPressed);
        }
    }

    public void addComponents(){
        // Add all of the components
        this.add(titlePanel);
        this.add(buttonPanel);
        this.add(ipPanel);
        this.add(backPanel);
    }

    @Override
    public void onSessionJoin(final String address, final int port) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipLabel.setText("You're connected to " + address + ":" + port);
            }
        });
    }

//    public void setEnable(boolean isEnabled){
//        this.enabled = isEnabled;
//    }


    public abstract String getInstrumentType();

}
