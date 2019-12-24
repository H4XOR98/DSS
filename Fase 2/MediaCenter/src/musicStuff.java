import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class musicStuff {
    void playMusic(String musicLocation){
        try{
            // open the sound file
            File musicPath = new File (musicLocation);

            if (musicPath.exists()) {

                // create an audiostream
                AudioInputStream audioinput = AudioSystem.getAudioInputStream(musicPath);

                // play the audio clip
                Clip clip = AudioSystem.getClip();
                clip.open(audioinput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                // stop button
                JOptionPane.showMessageDialog(null, "Stop");
                long clipTimePosition = clip.getMicrosecondPosition();
                clip.stop();
            }

            else JOptionPane.showMessageDialog(null, "Ficheiro não existe!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


 /*
        //So para wav
        String filePath = "/Users/goncaloalmeida/Downloads/carlos.mp3";
        musicStuff musicObject = new musicStuff();
        musicObject.playMusic(filePath);*/