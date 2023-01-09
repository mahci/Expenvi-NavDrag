package tools;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sounder {

    private static Clip hitClip, missClip;

    static {
        try {
//            final File startErrFile = new File("./res/start_err.wav");
            final File hitFile = new File("./res/hit.wav");
            final File missFile = new File("./res/miss.wav");
//            final File taskEndFile = new File("./res/end.wav");

//            startErrClip = AudioSystem.getClip();
//            startErrClip.open(AudioSystem.getAudioInputStream(startErrFile));

            hitClip = AudioSystem.getClip();
            hitClip.open(AudioSystem.getAudioInputStream(hitFile));

            missClip = AudioSystem.getClip();
            missClip.open(AudioSystem.getAudioInputStream(missFile));

//            taskEndClip = AudioSystem.getClip();
//            taskEndClip.open(AudioSystem.getAudioInputStream(taskEndFile));

        } catch (NullPointerException | IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

//    public static void playStartError() {
//        startErrClip.setMicrosecondPosition(0); // Reset to the start of the file
//        startErrClip.start();
//    }

    public static void playHit() {
        hitClip.setMicrosecondPosition(0); // Reset to the start of the file
        hitClip.start();
    }

    public static void playMiss() {
        missClip.setMicrosecondPosition(0); // Reset to the start of the file
        missClip.start();
    }

//    public static void playTaskEnd() {
//        taskEndClip.setMicrosecondPosition(0); // Reset to the start of the file
//        taskEndClip.start();
//    }
}
