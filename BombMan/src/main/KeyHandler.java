package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed, spaceBomb;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W: {
                upPressed = true;
            }
            case KeyEvent.VK_UP: {
                upPressed = true;
            }
            case KeyEvent.VK_S: {
                downPressed = true;
            }
            case KeyEvent.VK_DOWN: {
                downPressed = true;
            }
            case KeyEvent.VK_A: {
                leftPressed = true;
            }
            case KeyEvent.VK_LEFT: {
                leftPressed = true;
            }
            case KeyEvent.VK_D: {
                rightPressed = true;
            }
            case KeyEvent.VK_RIGHT: {
                rightPressed = true;
            }
            case KeyEvent.VK_SPACE: {
                spaceBomb = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W: {
                upPressed = false;
            }
            case KeyEvent.VK_UP: {
                upPressed = false;
            }
            case KeyEvent.VK_S: {
                downPressed = false;
            }
            case KeyEvent.VK_DOWN: {
                downPressed = false;
            }
            case KeyEvent.VK_A: {
                leftPressed = false;
            }
            case KeyEvent.VK_LEFT: {
                leftPressed = false;
            }
            case KeyEvent.VK_D: {
                rightPressed = false;
            }
            case KeyEvent.VK_RIGHT: {
                rightPressed = false;
            }
            case KeyEvent.VK_SPACE: {
                spaceBomb = false;
            }

        }
    }

}
