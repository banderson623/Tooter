package messaging;

import instruments.Instrument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This must be thread safe.
 */
public class Song{
    // Represent notes over time, first parameter will be time (milliseconds)
    private LinkedList<Toot> toots;
    Long firstTootTime;

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
        }

//        Executors.newScheduledThreadPool(1,)
    }

    public synchronized boolean update(byte[] serializedToot) {
        // TODO
        return false;
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
        Scanner sc = new Scanner(inputString);
        while(sc.hasNext()){

        }
    }

    private void readObjectNoData() throws ObjectStreamException {

    }



    /**
     * This is an event in a song ... that happens at a specific time
     */
    public class Toot {
        private List sounds;
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
            // TODO
            return null;
        }
    }
}
