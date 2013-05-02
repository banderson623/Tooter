package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class TooterProjectGUI {
    private JFrame mainFrame;
    private CardLayout cl;
    private JPanel mainPanel;
    private JPanel splashPanel;
    private JPanel playPanel;
    private JPanel choosePanel;

    private JPanel pianoPanel;
    private JPanel drumPanel;
    private JPanel guitarPanel;
    private JPanel eightBitPanel;

    // probably shouldn't be public...
    public HashMap<String,AbstractInstrument> instrumentPanels;

    public TooterProjectGUI(){
        // Initialize main frames & panels
        cl = new CardLayout();
        mainFrame = new JFrame();
        mainPanel = new JPanel();

        // Set card layout (enables switching through panels)
        mainPanel.setLayout(cl);

        // Set size and color of window frame
        mainFrame.setSize(new Dimension(1000, 800));
        mainFrame.getContentPane().setBackground(Color.WHITE);

        // Initialize different page panels
        splashPanel = new SplashScreen(cl, mainPanel);
        playPanel = new PlaySongScreen(cl, mainPanel);
        choosePanel = new ChooseInstrument(cl, mainPanel);

        // Store Instrument Panels like this...
        instrumentPanels = new HashMap<String, AbstractInstrument>();

        instrumentPanels.put("piano",  new PianoInstrument(cl, mainPanel));
        instrumentPanels.put("drums",  new DrumInstrument(cl, mainPanel));
        instrumentPanels.put("guitar", new GuitarInstrument(cl, mainPanel));
        instrumentPanels.put("8bit",   new EightBitInstrument(cl, mainPanel));

        for(String name : instrumentPanels.keySet()){
            Session.sessionListeners.add((SessionListener) instrumentPanels.get(name));
            instrumentPanels.get(name).setName(name);
            mainPanel.add(instrumentPanels.get(name),name);
        }

        // Add components to the main Panel
        mainPanel.add(splashPanel, "splash");
        mainPanel.add(playPanel,   "play");
        mainPanel.add(choosePanel, "choose");

        // Beginning page to show
        cl.show(mainPanel, "splash");

        // Add components to the main Frame
        mainFrame.add(mainPanel);

        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new TooterProjectGUI();
            }
        });
    }
}
