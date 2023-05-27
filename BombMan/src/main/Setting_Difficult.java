package main;

public class Setting_Difficult {
    GamePanel gp;
    public int difficult;

    public Setting_Difficult(GamePanel gp) {
        this.gp = gp;
    }

    public void setDifficult(int difficult) {
        switch (difficult) {
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
                break;
        }
    }

}
