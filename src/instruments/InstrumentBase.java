package instruments;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

/**
 * User: brian_anderson
 * Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public abstract class InstrumentBase implements Instrument{
    protected void playSoundFileFromPath(String filePath){
        // TODO: do the work of playing the sound file...
        try {
            String fullPath = getClass().getClassLoader().getResource(".").getPath();
            fullPath += "/piano/c.wav";
            File soundFile = new File(fullPath);
            System.out.println("defaultSound " + filePath);  // check the URL!
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new URL(fullPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start( );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void play(Note noteToPlay){
        playSoundFileFromPath(noteToPlay.getFileName());
    }
}
