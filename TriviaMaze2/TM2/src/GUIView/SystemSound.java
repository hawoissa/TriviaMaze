
package GUIView;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

/**
 * This class creates different sounds,
 * for different methods when playing the Maze game.
 */
public class SystemSound {
    private AudioInputStream myAudioInputStream;
    /** Holds the data of clip to perform the Clip interface operation .*/
    private Clip myClip;

    /**
     * Initializes the SystemSound fields.
     * @param thePath the other file path.
     */
    public SystemSound(final File thePath){
        try {

            //myFilePath = thePath;
            myAudioInputStream = AudioSystem.getAudioInputStream((thePath).getAbsoluteFile());
            // create a clip reference
            myClip = AudioSystem.getClip();
            // Open audioClipSystem to myAudionInputStream
            myClip.open(myAudioInputStream);
       } catch (IOException | LineUnavailableException e){ // | -> indicates one or the other exception
//            System.out.println();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the sound.
     */
    public void gameSounds() {
        try {
            if (myClip != null) { // if myClip is not empty
                myClip.loop(Clip.LOOP_CONTINUOUSLY); // loop myClip continuously
                myClip.start();                // start the clip
                Thread.sleep(1700);     // play's the audio for 3 seconds
                myClip.stop();               // Stop's the sound after 3 minutes
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
