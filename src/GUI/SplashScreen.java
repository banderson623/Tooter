package GUI;

import com.digitalxyncing.communication.Sniffy;
import com.digitalxyncing.communication.impl.ZmqSniffy;
import controller.SongController;
import util.LibraryUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SplashScreen extends JPanel {
    private JPanel imagePanel;
    private JPanel buttonPanel;
    private JPanel joinPanel;
    private JList<String> ipList = new JList<String>();
    private JButton joinButton;

    static {
        LibraryUtils.setLibraryPath();
    }

    public SplashScreen(final CardLayout cl, final JPanel mainPanel) {
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
        buttonPanel.setSize(new Dimension(500, 100));
        buttonPanel.setBackground(Color.WHITE);

        // Start a song button
        JButton startsongButton = new JButton();
        startsongButton.setText("Start and Host a Song");
        startsongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(mainPanel, "choose");
            }
        });
        buttonPanel.add(startsongButton);

        // Play a song button
        JButton playsongButton = new JButton();
        playsongButton.setText("Play a Saved Song");
        playsongButton.addActionListener(new ActionListener() {
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
        ipList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ipList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        ipList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                joinButton.setEnabled(true);
            }
        });

        JScrollPane listScroller = new JScrollPane(ipList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        joinPanel.add(listScroller);

        // Populate the sessions list
        discoverSessions();

        // Join Button
        joinButton = new JButton();
        joinButton.setText("Join Host");
        joinButton.setEnabled(false);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Session.ipToConnectTo = ipList.getSelectedValue();
                Session.songController.isHost(false);
                cl.show(mainPanel, "choose");
            }
        });
        joinPanel.add(joinButton);

        // Refresh button for session discovery
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discoverSessions();
            }
        });
        joinPanel.add(refreshButton);

        // Add imagePanel to the Panel
        this.add(imagePanel);
        this.add(buttonPanel);
        this.add(joinPanel);


    }

    private void discoverSessions() {
        Sniffy sniffy = new ZmqSniffy();
//        sniffy.discoverXyncersOnPort(SongController.DISCOVERY_PORT_INITIAL, new Sniffy.HostListCallBack() {
//            @Override
//            public void results(java.util.List<String> listOfDiscoveredIPs) {
//            DefaultListModel<String> listModel = new DefaultListModel<String>();
//            for (String ip : listOfDiscoveredIPs) {
//                listModel.addElement(ip);
//                ipList.setModel(listModel);
//            }
//            }
//        });

        final java.util.List<String> listOfIps = new ArrayList<String>();
        listOfIps.add("10.26.4.1");
        listOfIps.add("10.26.0.1");
        sniffy.discoverXyncersOnMySubnetAndFromListOfIPsAndPort(listOfIps,SongController.DISCOVERY_PORT_INITIAL, new Sniffy.HostListCallBack() {
            @Override
            public void results(java.util.List<String> listOfDiscoveredIPs) {
                DefaultListModel<String> listModel = new DefaultListModel<String>();
                for (String ip : listOfDiscoveredIPs) {
                    listModel.addElement(ip);
                    ipList.setModel(listModel);
                }
            }
        });

    }

}
