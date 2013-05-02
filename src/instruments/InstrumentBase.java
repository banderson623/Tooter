package instruments;

import Resources.Sentinel;
import util.LibraryUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * User: brian_anderson Date: 4/13/13
 * <p/>
 * Add some readme here about how this operates
 */
public abstract class InstrumentBase implements Instrument {
    public LinkedList<Note> autoNotes;


    protected String getPathToSoundKits() {
        String fullPath = getClass().getClassLoader().getResource(".").getPath();
        fullPath += "../../../Resources/";
        return fullPath;
    }

    protected void playSoundFileFromPath(String filePath) {
        // TODO: do the work of playing the sound file...
        try {
            AudioInputStream audioInputStream;
            if (LibraryUtils.isRunningFromJar()) {
                InputStream inputStream = getClass().getResourceAsStream("/" + filePath);
                InputStream bufferedIn = new BufferedInputStream(inputStream);
                audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            } else {
                File soundFile = new File(getPathToSoundKits() + filePath);
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            }
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addNotesFromWaveFilesInDirectory(String directory) {

        autoNotes = new LinkedList<Note>();

        File[] files = null;
        if (LibraryUtils.isRunningFromJar()) {
            try {
                String[] resources = getResources(directory);
                files = new File[resources.length];
                for (int i = 0; i < resources.length; i++) {
                    URL url = getClass().getResource("/" + resources[i]);
                    File file = new File(url.getPath());
                    files[i] = file;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File dir = new File(getPathToSoundKits() + "/" + directory);
            files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    return filename.endsWith(".wav");
                }
            });
        }

        for (File file : files) {
            String fileName = file.getName();
            final String name = fileName.substring(0, fileName.indexOf("."));
            final String path = directory + "/" + file.getName();
            Note auto = new Note() {

                @Override
                public String id() {
                    return getFileName();
                }

                @Override
                public String name() {
                    return name;
                }

                @Override
                public String getFileName() {
                    return path;
                }


                public String toString() {
                    return name;
                }
            };
            autoNotes.add(auto);
        }
    }


    @Override
    public void play(Note noteToPlay) {
// Uncomment if you want it only to play it's own note
//        if(autoNotes.contains(noteToPlay)){
        playSoundFileFromPath(noteToPlay.getFileName());
//        }
    }

    @Override
    public List<Note> getSupportedNotes() {
        return autoNotes;
    }

    @Override
    public Note getNoteByName(String name) {
        for (Note note : getSupportedNotes()) {
            if (note.name().equalsIgnoreCase(name)) {
                return note;
            }
        }
        return null;
    }

    @Override
    public void play(List<Note> notesToPlay) {
        for (Note note : notesToPlay) {
            play(note);
        }
    }

    /**
     * Retrieves an array of paths representing the wav files in the given resource directory (relative to /Resources).
     *
     * @param directory
     * @return
     * @throws IOException
     */
    private String[] getResources(String directory) throws IOException {
        CodeSource src = Sentinel.class.getProtectionDomain().getCodeSource();
        List<String> list = new ArrayList<String>();

        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry ze;

            while ((ze = zip.getNextEntry()) != null) {
                String entryName = ze.getName();
                if (entryName.startsWith(directory) && entryName.endsWith(".wav")) {
                    list.add(entryName);
                }
            }

        }
        return list.toArray(new String[list.size()]);
    }


}
