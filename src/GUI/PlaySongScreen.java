package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlaySongScreen extends JPanel{
	private JPanel titlePanel;
	private JPanel choosePanel;
	private JPanel playPanel;
	private JPanel backPanel;
	
	public PlaySongScreen(final CardLayout cl, final JPanel mainPanel){
		// Set the size, color and layout of this panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(1000, 800));
		this.setBackground(Color.WHITE);
		
		// Panel for Title
		titlePanel = new JPanel();
		titlePanel.setSize(new Dimension(1000, 500));
		titlePanel.setBackground(Color.WHITE);
				
				// Add image to the image panel
				JLabel dxlogoLabel = new JLabel();
				dxlogoLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			    dxlogoLabel.setIcon(new ImageIcon("Resources/images/playsongtitle.jpg"));
			    titlePanel.add(dxlogoLabel);
			
		// Panel for choosing file
	    choosePanel = new JPanel();
	    choosePanel.setSize(new Dimension(1000, 50));
	    choosePanel.setBackground(Color.WHITE);
	    	
	    	// Text Field to show file location
	    	final JTextField filelocationTextField = new JTextField();
	    	filelocationTextField.setEditable(false);
	    	filelocationTextField.setPreferredSize(new Dimension(300, 25));
	    	filelocationTextField.setText("No Song Selected...");
	    	choosePanel.add(filelocationTextField);
	    	
	    
	    	// Button to open a File Chooser
	    	JButton opensongButton = new JButton();
	    	opensongButton.setText("Choose Song");
	    	opensongButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser fc = new JFileChooser();
				    int rVal = fc.showOpenDialog(PlaySongScreen.this);
				    if (rVal == JFileChooser.APPROVE_OPTION) {
				    	filelocationTextField.setText(fc.getCurrentDirectory() + "\\" + fc.getSelectedFile().getName());
				    }
				    if (rVal == JFileChooser.CANCEL_OPTION) {
				        filelocationTextField.setText("No Song Selected...");
				    }					
				}
	    		
	    	});
	    	choosePanel.add(opensongButton);
	    	
	   // Panel to hold the Play Button
	   playPanel = new JPanel();
	   playPanel.setSize(new Dimension(1000, 200));
	   playPanel.setBackground(Color.WHITE);
	   
	    	// Play Button
	    	JButton playButton = new JButton(new ImageIcon("Resources/images/play.jpg"));
	    	playButton.setBackground(Color.WHITE);
	    	playButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	    	playButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(filelocationTextField.getText().equals("No Song Selected...") || filelocationTextField.getText().equals("Please Choose a Song...")){
						filelocationTextField.setText("Please Choose a Song...");
					}
					else{
						// TODO play the song
					}
				}
	    	});
	    	playPanel.add(playButton);
	    
	    // Panel to hold the Back Button
	    backPanel = new JPanel();
	    backPanel.setSize(new Dimension(1000, 50));
	    backPanel.setBackground(Color.WHITE);
	    	
	    	// Back button
	    	JButton backButton = new JButton();
	    	backButton.setText("Back");
	    	backButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	    	backButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					cl.show(mainPanel, "splash");					
				}
	    	});
	    	backPanel.add(backButton);
	    
	    // Add all of the panels
	    this.add(titlePanel);
	    this.add(choosePanel);
	    this.add(playPanel);
	    this.add(backPanel);
	}

}
