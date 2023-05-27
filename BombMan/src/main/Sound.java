package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;

    URL soundURL[] = new URL[15];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/BGM2.wav");
        soundURL[1] = getClass().getResource("/sound/Clear.wav");
        soundURL[2] = getClass().getResource("/sound/Over.wav");
        soundURL[4] = getClass().getResource("/sound/bomb_sound.wav");
        soundURL[8] = getClass().getResource("/sound/coin.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
