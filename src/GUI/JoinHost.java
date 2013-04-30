package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinHost extends JPanel {

    public JoinHost(final CardLayout cl, final JPanel panelCont) {
        JPanel splash = new JPanel();
        splash.setBackground(Color.WHITE);

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);

        // IP Label
        JLabel ipLabel = new JLabel();
        ipLabel.setText("Please Enter the IP of the Host");
        topPanel.add(ipLabel);
        // IP Input Box
        final JTextField ipInput = new JTextField();
        topPanel.add(ipInput);
        // Port Label
        JLabel portLabel = new JLabel();
        ipLabel.setText("Please Enter the Port of the Host");
        topPanel.add(portLabel);
        // Port Input Box
        final JTextField portInput = new JTextField();
        topPanel.add(portInput);
        // Submit Button
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String address = ipInput.getText().trim();
                int port = Integer.valueOf(portInput.getText().trim());
                Session.songController.setHostAddress(address);
                Session.songController.setHostPort(port);
                Session.songController.isHost(false);
                for (SessionListener listener : Session.sessionListeners) {
                    listener.onSessionJoin(address, port);
                }
                cl.show(panelCont, "choice");
            }

        });
        topPanel.add(submit);
        // Back Button
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                cl.show(panelCont, "main");
            }

        });
        topPanel.add(back);

        splash.add(topPanel);
        this.add(splash);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}