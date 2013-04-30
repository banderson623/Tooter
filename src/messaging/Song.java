package messaging;

import GUI.Session;
import instruments.Instrument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * This must be thread safe.
 */
public class Song{
    // Represent notes over time, first parameter will be time (milliseconds)
    public static ScheduledExecutorService playerExecutor = Executors.newScheduledThreadPool(1);
    public static SongPlayer playing = null;
    private LinkedList<Toot> toots;
    Long firstTootTime;

    public class SongPlayer implements Runnable {
        private List<Toot> toots;
        public SongPlayer(List<Toot> toots) {
            this.toots = toots;
        }

        public void run() {
            Long start = System.currentTimeMillis();
            Long tootTime = 0L;
            Iterator<Toot> it = toots.iterator();
            while(it.hasNext()) {
                Toot toot = it.next();
                while(true) {
                    tootTime = System.currentTimeMillis();
                    if(tootTime > toot.getTime() + start) {
                        Session.songController.play(toot);
                        break;
                    }
                }
            }
            playing = null;
        }
    }

    public Song(){
        toots = new LinkedList<Toot>();
        firstTootTime = 0L;
    }

    public synchronized void start(){
        if(firstTootTime == 0){
            firstTootTime = System.currentTimeMillis();
        }
    }

    /**
     * Not synchronized...
     * ... this does not change or access state of the object
     * ... we want the time it was played to be saved, then added to the toots
     * ... in a blocking way, but save the time entered still
     * @param oneSingleLonelyNote
     */
    public void addNote(Instrument.Note oneSingleLonelyNote){
        long relativeTimeInMilliseconds = System.currentTimeMillis() - firstTootTime;
        addToTootOrMakeANewOneForTime(relativeTimeInMilliseconds, oneSingleLonelyNote);
    }

    /**
     * Responsible for playing the song
     */
    public void play(){
        synchronized(this) {
            // Copy this locally.
            final List<Toot> myToots = new LinkedList<Toot>(toots);
            if(playing == null) {
                playing = new SongPlayer(myToots);
                Song.playerExecutor.execute(playing);
            }
            else {
                System.out.println("A song is already playing!");
            }
        }
    }

    public synchronized String toString(){
        String toReturn = "";
        for(Toot toot : toots){
            toReturn += toot.toString() + "\n";
        }
        return toReturn;
    }

    private void addToTootOrMakeANewOneForTime(long timeInMilliseconds, Instrument.Note someNote){
        Toot t = new Toot();
        t.add(someNote);
        t.setTime(timeInMilliseconds);
        synchronized(this){ //lock to add note (object state)
            toots.add(t);
        }
    }


    /**
     * Support for Serialization! ------------------------------------------
     */

    public byte[] toByteArray() throws IOException
    {
        System.out.println("Serializing to: \n" + toSerializedString());
        char[] chars = toSerializedString().toCharArray();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        for(char c : chars){
            bytes.write(c);
        }
        return bytes.toByteArray();
    }

    /**
     * The writeObject method is responsible for writing the state of the object for its particular class
     * @return String - our serialized object
     * @throws IOException
     */
    public String toSerializedString()
    {
//        String out = "";
        StringBuffer out = new StringBuffer();
        List<Toot> myToots;
        synchronized(this) {
            myToots = new LinkedList<Toot>(toots);
        }
        out.append(firstTootTime);
        for(Toot t : myToots){
            out.append("*");
            out.append(t.getTime());
            for(Instrument.Note n : t.getNotes()){
                out.append('|');
                out.append(n.id());
            }
        }
        return out.toString();
    }

    public void intializeFromSerializedString(String inputString)
    {
        synchronized(this) {
            String[] tootStrings = inputString.split("\\*");
            toots = new LinkedList<Toot>();
            firstTootTime = Long.parseLong(tootStrings[0]);
            for(int i=1;i<tootStrings.length;++i) {
                String tootString = tootStrings[i];
                Toot toot = new Toot();
                String[] noteStrings = tootString.split("\\|");
                toot.setTime(Long.parseLong(noteStrings[0]));
                for(int j=1;j<noteStrings.length;++j) {
                    final String noteString = noteStrings[j];
                    if(noteString.isEmpty()) {
                        continue;
                    }
                    toot.add(new Instrument.Note()
                    {
                        @Override
                        public String id()
                        {
                            return noteString;
                        }

                        @Override
                        public String name()
                        {
                            return "noname";
                        }

                        @Override
                        public String getFileName()
                        {
                            return noteString;
                        }
                    });
                }
                toots.add(toot);
            }
        }
    }

    private void readObjectNoData() throws ObjectStreamException {

    }



    /**
     * This is an event in a song ... that happens at a specific time
     */
    public static class Toot {
        private List<Instrument.Note> sounds;
        private Long time;

        public Toot(){
            sounds = new LinkedList<Instrument.Note>();
        }

        public void add(Instrument.Note note){
            sounds.add(note);
        }

        public List<Instrument.Note> getNotes(){
            return sounds;
        }

        public void setTime(Long t){
            time = t;
        }

        public Long getTime(){
            return time;
        }

        public String toString(){
            return time.toString() + ":\t" + sounds.toString();
        }

        public byte[] toByteArray() {
            StringBuilder sb = new StringBuilder();
            sb.append(time);
            for (Instrument.Note note : sounds) {
                sb.append('|').append(note.id());
            }
            char[] chars = sb.toString().toCharArray();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            for (char c : chars){
                bytes.write(c);
            }
            return bytes.toByteArray();
        }
    }
}
