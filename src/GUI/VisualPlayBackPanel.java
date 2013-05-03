package GUI;

import instruments.Instrument;
import messaging.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 5/2/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class VisualPlayBackPanel extends JPanel
{
    public static final int DesiredFPS = 30;
    private boolean canPlay = false;
    private JPanel region;
    private int height;
    private int width;
    private List<Song.Toot> toots;

    private List<Sprite> visibleSprites;
    private List<Sprite> spritesToRemove;
    private long startPlaybackTime = 0L;
    private long stoppedPlaybackTime = 0L;

    private long leftSideIsTime = 0L;
    public static int timeScale = 10; // (100 ms is 1 px)

    // The master timer!
    javax.swing.Timer timer;


    public VisualPlayBackPanel(final int width, final int height){
        super();
        setSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setLayout(null);

        this.height = height;
        this.width = width;
        toots = new LinkedList<Song.Toot>();
        visibleSprites = new LinkedList<Sprite>();
        spritesToRemove = new LinkedList<Sprite>();
    }


    public void loadSong(Song songToPlay)
    {
        if(songToPlay != null){
            toots = songToPlay.getToots();
        }
        // Left side is first note time
        leftSideIsTime = toots.get(0).getTime();

        buildSpriteForToots();
        //Now we have the toots, time to place them in the jpanel
    }


    /**
     * Will display the toots in the jpanel
     */
    private void buildSpriteForToots(){
        for(Song.Toot toot : toots){
            Sprite sprite = new Sprite(toot.getTime(), toot.getNotes());
            add(sprite);
            visibleSprites.add(sprite);
        }
    }


    private void tick(){
        // update the sprites position
        for(Sprite sprite : visibleSprites){
            long timeInSongNow = System.currentTimeMillis() - startPlaybackTime;
            sprite.updateForTime(timeInSongNow + leftSideIsTime);
            if(sprite.leftBound < 0){
                spritesToRemove.add(sprite);
            }
        }

        // remove those that are off the screen to the left.
        for(Sprite removeMe : spritesToRemove){
            remove(removeMe);
        }

        visibleSprites.removeAll(spritesToRemove);
        // all done...
        if(visibleSprites.size() == 0){
            stop();
        }

    }

    public void stop(){
        timer.stop();
        stoppedPlaybackTime = 0L;
    }

    public void pause() {
        timer.stop();
        stoppedPlaybackTime = System.currentTimeMillis() - startPlaybackTime;
    }

    public void play(){
        timer = new javax.swing.Timer((1000/DesiredFPS), new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tick();
            }
        });
        timer.setRepeats(true);
        timer.start();
        startPlaybackTime = System.currentTimeMillis() - stoppedPlaybackTime;
    }


    /**
     * This is the note/sprite that will move
     * along the play head as the song plays...
     */
    private class Sprite extends JPanel
    {
        private Long time;
        private int leftBound = 0;
        private final int height = 5;
        private int yOffset = 0;


        public Sprite(Long atTime, List<Instrument.Note> notesInToot){
            super();
            setSize(5, height);
            yOffset = 0;

            // Really just cheat and get the first note
            Instrument.Note note = notesInToot.get(0);
            String name = note.name();
            int noteType = (((int)name.charAt(0)) % 10);
            yOffset = (height*noteType);

            int colorType =(((int) note.getFileName().charAt(0)) % 7);


            Color spriteColor = Color.yellow;
            switch(colorType){
                case 0: spriteColor = Color.yellow; break;
                case 1: spriteColor = Color.blue; break;
                case 2: spriteColor = Color.green; break;
                case 3: spriteColor = Color.pink; break;
                case 4: spriteColor = Color.orange; break;
                case 5: spriteColor = Color.lightGray; break;
                case 6: spriteColor = Color.cyan; break;
                case 7: spriteColor = Color.magenta; break;
                default: spriteColor = Color.yellow; break;
            }

            setBackground(spriteColor);

            time = atTime;
            leftBound = (int)(time/VisualPlayBackPanel.timeScale);
            setLocation(leftBound,yOffset);
        }


        /**
         * Call this when the thing needs to move...
         */
        public void updateForTime(Long currentPlaybackTime){
            leftBound = (int)(time - currentPlaybackTime)/VisualPlayBackPanel.timeScale;
            //time is the time this should be drawn for
            // the left side is now going to be
            // the toots time - the current Playback Time.
            setLocation(leftBound, yOffset);
        }
    }
}
