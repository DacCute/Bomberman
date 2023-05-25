package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tile.TileManager;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, rightPressed, leftPressed, spaceBomb, pause, enter;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 0;
            }

        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 2;
            }

        }

        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.commandNum) {
                case 0:
                    gp.gameState = gp.playState;
                    gp.gameDifficult = gp.easy;
                    break;

                case 1:
                    gp.gameState = gp.Difficult;
                    break;

                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

    public void difficult(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 0;
            }

        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 2;
            }

        }

        if (code == KeyEvent.VK_ENTER) {
            System.out.println("CONFIRM");
            switch (gp.ui.commandNum) {
                case 0:
                    gp.gameDifficult = gp.easy;
                    gp.gameState = gp.playState;
                    System.out.println(gp.gameDifficult);
                    break;

                case 1:
                    gp.gameDifficult = gp.normal;
                    gp.gameState = gp.playState;
                    System.out.println(gp.gameDifficult);
                    break;

                case 2:
                    gp.gameDifficult = gp.hard;
                    gp.gameState = gp.playState;
                    System.out.println(gp.gameDifficult);
                    break;
            }
        }

        switch (gp.gameDifficult) {
            case 1:
                for (int i = 0; i < gp.mons.length; ++i) {
                    if (gp.mons[i] != null) {
                        gp.mons[i].hp = 10;
                        gp.mons[i].speed = 4;
                        gp.mons[i].monster_atk = 5;
                        gp.mons[i].monster_direct = 40;
                        gp.gacha.rate = 10;
                    }
                }

                System.out.println("EASY");
                break;

            case 2:
                for (int i = 0; i < gp.mons.length; ++i) {
                    if (gp.mons[i] != null) {
                        gp.mons[i].hp = 20;
                        gp.mons[i].speed = 8;
                        gp.mons[i].monster_atk = 10;
                        gp.mons[i].monster_direct = 20;
                        gp.gacha.rate = 20;
                    }
                }

                System.out.println("NORMAL");
                break;

            case 3:
                for (int i = 0; i < gp.mons.length; ++i) {
                    if (gp.mons[i] != null) {
                        gp.mons[i].hp = 50;
                        gp.mons[i].speed = 12;
                        gp.mons[i].monster_atk = 20;
                        gp.mons[i].monster_direct = 10;
                        gp.gacha.rate = 30;
                    }
                }

                System.out.println("HARD");
                break;
        }

    }

    public void endgame(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 2;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.commandNum) {
                case 0:
                    gp.gameDifficult = gp.gameDifficult;
                    // System.out.println(gp.gameDifficult);
                    gp.setupGame();
                    gp.player.setDefaultvalues();
                    gp.gameState = gp.playState;
                    gp.tileM = new TileManager(gp);

                    for (int i = 0; i < gp.obj.length; i++) {
                        if (gp.obj[i] != null) {
                            gp.obj[i] = null;
                        }
                    }
                    for (int i = 0; i < gp.player.bomb_count; i++) {
                        if (gp.bombs[i] != null && gp.bombs[i].worldX > 0 && gp.bombs[i].worldY > 0) {
                            gp.bombs[i] = null;
                        }
                    }
                    for (int i = 0; i < gp.explodes.length; i++) {
                        if (gp.explodes[i] != null) {
                            gp.explodes[i] = null;
                        }
                    }

                    gp.ui.GameFinish = false;
                    gp.ui.GameOver = false;

                    break;

                case 1:
                    gp.setupGame();
                    System.out.println(gp.gameDifficult);
                    gp.player.setDefaultvalues();
                    gp.tileM = new TileManager(gp);
                    for (int i = 0; i < gp.player.bomb_count; i++) {
                        if (gp.bombs[i] != null && gp.bombs[i].worldX > 0 && gp.bombs[i].worldY > 0) {
                            gp.bombs[i] = null;
                        }
                    }
                    for (int i = 0; i < gp.explodes.length; i++) {
                        if (gp.explodes[i] != null) {
                            gp.explodes[i] = null;
                        }
                    }

                    gp.ui.GameFinish = false;
                    gp.ui.GameOver = false;

                    break;

                case 2:
                    System.exit(0);
                    break;
            }

        }
    }

    public void playstate(int code) {
        switch (code) {
            case KeyEvent.VK_W: {
                upPressed = true;
            }
                break;

            case KeyEvent.VK_UP: {
                upPressed = true;
            }
                break;

            case KeyEvent.VK_S: {
                downPressed = true;
            }
                break;

            case KeyEvent.VK_DOWN: {
                downPressed = true;
            }
                break;

            case KeyEvent.VK_A: {
                leftPressed = true;
            }
                break;

            case KeyEvent.VK_LEFT: {
                leftPressed = true;
            }
                break;

            case KeyEvent.VK_D: {
                rightPressed = true;
            }
                break;

            case KeyEvent.VK_RIGHT: {
                rightPressed = true;
            }
                break;

            case KeyEvent.VK_SPACE: {
                spaceBomb = true;
            }
                break;

            case KeyEvent.VK_P: {
                pause = true;
                if (gp.gameState == gp.playState) {
                    System.out.println("pause");
                    gp.gameState = gp.pauseState;
                } else if (gp.gameState == gp.pauseState) {
                    System.out.println("play");
                    gp.gameState = gp.playState;
                }
                code = 0;
            }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            enter = true;
        }

        // TITLE MENU
        switch (gp.gameState) {
            case 0: {
                titleState(code);
                break;
            }
            case 1: {
                playstate(code);
                break;
            }
            case 2: {
                if (code == KeyEvent.VK_P)
                    if (gp.gameState == gp.playState) {
                        System.out.println("pause");
                        gp.gameState = gp.pauseState;
                    } else if (gp.gameState == gp.pauseState) {
                        System.out.println("play");
                        gp.gameState = gp.playState;
                    }
                break;
            }
            case 3: {
                difficult(code);
                break;
            }
            case 4: {
                endgame(code);
                break;
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
                break;
            case KeyEvent.VK_UP: {
                upPressed = false;
            }
                break;
            case KeyEvent.VK_S: {
                downPressed = false;
            }
                break;
            case KeyEvent.VK_DOWN: {
                downPressed = false;
            }
                break;
            case KeyEvent.VK_A: {
                leftPressed = false;
            }
                break;
            case KeyEvent.VK_LEFT: {
                leftPressed = false;
            }
                break;
            case KeyEvent.VK_D: {
                rightPressed = false;
            }
                break;
            case KeyEvent.VK_RIGHT: {
                rightPressed = false;
            }
                break;
            case KeyEvent.VK_SPACE: {
                spaceBomb = false;
            }
                break;
            case KeyEvent.VK_P: {
                pause = false;
            }
                break;

        }
    }

}
