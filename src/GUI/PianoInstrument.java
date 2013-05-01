package GUI;

import messaging.SongFragment;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PianoInstrument extends JPanel implements SessionListener{
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JPanel ipPanel;
	private JPanel backPanel;
    private JLabel ipLabel;
    private instruments.Piano piano;
	
	
	private String[] keys = {"A", "B", "C", "D", "E", "F", "G"};
	
	public PianoInstrument(final CardLayout cl, final JPanel mainPanel){
		// Set the size, color and layout of this panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(1000, 800));
		this.setBackground(Color.WHITE);

        // Instantiate the instrument
        this.piano = new instruments.Piano();
		
		// Panel for the Title
		titlePanel = new JPanel();
		titlePanel.setSize(new Dimension(1000, 400));
		titlePanel.setBackground(Color.WHITE);
			
			// Add image to the image panel
			JLabel instrumentsLabel = new JLabel();
			instrumentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			instrumentsLabel.setIcon(new ImageIcon("Resources/images/pianotitle.jpg"));
		    titlePanel.add(instrumentsLabel);
		
		// Panel for the buttons
		buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(1000, 300));
		buttonPanel.setBackground(Color.WHITE);
		
			// Create buttons for the piano
	        for(int i = 0; i < keys.length; i++){
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
			backButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					cl.show(mainPanel, "choose");
				}
			});
			backPanel.add(backButton);
			
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
}
