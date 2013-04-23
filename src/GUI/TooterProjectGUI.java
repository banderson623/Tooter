package GUI;

import util.LibraryUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TooterProjectGUI {

    static {
        LibraryUtils.setLibraryPath();
    }

	private String[] instrumentChoices = {"Piano", "Drums", "TurnTable"};
	private JFrame frame = new JFrame();
	private JPanel panelCont = new JPanel();
	private JPanel panelChoice = new JPanel();
	private JPanel panelPiano = new JPanel();
	CardLayout cl = new CardLayout();
	
	public TooterProjectGUI() {
		panelCont.setLayout(cl);
		
		// choice panel
		JPanel InstrumentChoice = new JPanel();
		InstrumentChoice.setLayout(new GridLayout(2, 1, 5, 5));
        
		        // Create the top panel
		        JPanel topPanel = new JPanel();
		        			        
		        	// Add image to top panel
		        	JLabel label = new JLabel();  
		        	label.setIcon(new ImageIcon("tooterlogo.png"));// your image here  
		        	topPanel.add(label);  
		        	
		        // Create the bottom panel
		        JPanel botPanel = new JPanel();
		 
		        	
		        	// Add a Text Field "Choose your weapon."
		        	JLabel choiceText = new JLabel("Choose Your Weapon ");
		        	
		        	// Add drop down
		         	final JComboBox comboBox = new JComboBox();
		         	for(int i = 0; i < instrumentChoices.length; i++)
		         		comboBox.addItem(instrumentChoices[i]);
			        
			        // Add submit button
			        JButton submitBut = new JButton("Choose");
		         	submitBut.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							cl.show(panelCont, comboBox.getSelectedItem().toString());
						}		         		
		         	});
			        
		          	
		         	botPanel.add(choiceText);
		         	botPanel.add(comboBox);
		         	botPanel.add(submitBut);

		        InstrumentChoice.add(topPanel);
		        InstrumentChoice.add(botPanel);
		panelChoice.add(InstrumentChoice);
		
		// piano panel
		Piano piano = new Piano(cl, panelCont, true);
		panelPiano.add(piano);
		
		panelCont.add(panelChoice, "choice");
		panelCont.add(panelPiano, "Piano");
		cl.show(panelCont, "choice");
		
		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new TooterProjectGUI();
			}
	});

	}

}
