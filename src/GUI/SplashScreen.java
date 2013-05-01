package GUI;

import util.LibraryUtils;

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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class SplashScreen extends JPanel{
	private JPanel imagePanel;
	private JPanel buttonPanel;
	private JPanel joinPanel;
	
	static {
        LibraryUtils.setLibraryPath();
     }
	
	public SplashScreen(final CardLayout cl, final JPanel mainPanel){
		// Set the size, color and layout of this panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(1000, 800));
		this.setBackground(Color.WHITE);
		
		// Panel for images
		imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
		imagePanel.setSize(new Dimension(1000, 400));
		imagePanel.setBackground(Color.WHITE);
		
			// Add image to the image panel
		    JLabel dxlogoLabel = new JLabel();
		    dxlogoLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		    dxlogoLabel.setIcon(new ImageIcon("Resources/images/DX.jpg"));
		    imagePanel.add(dxlogoLabel);
		    
		    // Add presents label
		    JLabel presentsLabel = new JLabel();
		    presentsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		    presentsLabel.setText("PRESENTS...");
		    imagePanel.add(presentsLabel);
		
		    // Add image to the image panel
		    JLabel tooterlogoLabel = new JLabel();
		    tooterlogoLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		    tooterlogoLabel.setIcon(new ImageIcon("Resources/images/tooterlogo.png"));
		    imagePanel.add(tooterlogoLabel);
		    
		// Panel for Buttons
		buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(1000, 200));
		buttonPanel.setBackground(Color.WHITE);
		
			// Start a song button
			JButton startsongButton = new JButton();
			startsongButton.setText("Start Song");
			startsongButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(mainPanel, "choose");
				}		         		
         	});
			buttonPanel.add(startsongButton);
			
			// Play a song button
			JButton playsongButton = new JButton();
			playsongButton.setText("Play Song");
			playsongButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(mainPanel, "play");
				}		         		
         	});
			buttonPanel.add(playsongButton);
		    
		// Panel to join a song
		joinPanel = new JPanel();
		//joinPanel.setLayout();
		joinPanel.setSize(new Dimension(500, 200));
		joinPanel.setMaximumSize(new Dimension(500, 300));
		joinPanel.setBackground(Color.WHITE);
		joinPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// JList to hold the IP's that you may connect to
            // TODO GRAB THE LIST OF HOSTS
	    	JList<String> ipList = new JList<String>();
			ipList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			ipList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			
			JScrollPane listScroller = new JScrollPane(ipList);
			listScroller.setPreferredSize(new Dimension(250, 80));
	    	joinPanel.add(listScroller);
	    	
	    	// Join Button
	    	JButton joinButton = new JButton();
	    	joinButton.setText("Join Host");
	    	joinButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(mainPanel, "choose");
				}		         		
         	});
			joinPanel.add(joinButton);
	    	
		
	    // Add imagePanel to the Panel
		this.add(imagePanel);
		this.add(buttonPanel);
		this.add(joinPanel);
		
		
	}
	
}
