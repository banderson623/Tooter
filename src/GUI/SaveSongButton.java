package GUI;

import messaging.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Tae Kim
 * Date: 5/2/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class SaveSongButton extends JButton{
    public SaveSongButton(final JPanel mainPanel){
        this.setText("Save Song");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    // Get the file to save to
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().toLowerCase().endsWith(".song")) {
                        file = new File(file.getPath() + ".song");
                    }

                    // Save the song
                    Song song = Session.songController.getDocument().getFullState();
                    try {
                        FileWriter fstream = new FileWriter(file);
                        BufferedWriter out = new BufferedWriter(fstream);
                        out.write(song.toSerializedString());
                        out.close();
                    } catch (Exception e1) {//Catch exception if any
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
