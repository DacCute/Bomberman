package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;

    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/start_game.wav");
        soundURL[1] = getClass().getResource("/sound/bomberman.wav");
        soundURL[2] = getClass().getResource("/sound/footstep_sound.wav");
        soundURL[3] = getClass().getResource("/sound/monster_attacked.wav");
        soundURL[4] = getClass().getResource("/sound/BOMB_SFX.wav");
        soundURL[5] = getClass().getResource("/sound/congrat.wav");
        soundURL[6] = getClass().getResource("/sound/end_game.wav");
        soundURL[7] = getClass().getResource("/sound/BGM2.wav");
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
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
