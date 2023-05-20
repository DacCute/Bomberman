package main;

import entity.Entity;

public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBotWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBotRow = entityBotWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.action) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.MapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.MapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.MapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileM.MapTileNum[entityRightCol][entityBotRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.MapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.MapTileNum[entityLeftCol][entityBotRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.MapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.MapTileNum[entityRightCol][entityBotRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        boolean checkout = false; // check if player is inside bomb or not
        for (int i = 0; i < gp.obj.length; ++i) {

            if (gp.obj[i] != null) {

                // Get entity's solid Area Position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid Area Position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.action) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        // intersects is a function to check collision or not between 2 rectangles
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (player == true) { // only Player can pick the items
                                index = i;
                                if (i == 3) {
                                    if (checkout == false) {
                                        if (gp.player.worldY - 20 <= gp.obj[i].worldY
                                                && gp.player.worldY + 20 <= gp.obj[i].worldY + gp.tileSize) {
                                            checkout = false;
                                        } else {
                                            checkout = true;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (player == true) {
                                index = i;
                                if (i == 3) {
                                    if (checkout == false) {
                                        if (gp.player.worldY + 20 >= gp.obj[i].worldY
                                                && gp.player.worldY - 20 <= gp.obj[i].worldY + gp.tileSize) {
                                            checkout = false;
                                        } else {
                                            checkout = true;

                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (player == true) {
                                index = i;
                                if (i == 3) {
                                    if (checkout == false) {
                                        if (gp.player.worldX - 15 <= gp.obj[i].worldX
                                                && gp.player.worldX + 15 <= gp.obj[i].worldX + gp.tileSize) {
                                            checkout = false;
                                        } else {
                                            checkout = true;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (player == true) {
                                index = i;
                                if (i == 3) {
                                    if (checkout == false) {
                                        if (gp.player.worldX + 15 >= gp.obj[i].worldX
                                                && gp.player.worldX - 15 <= gp.obj[i].worldX + gp.tileSize) {
                                            checkout = false;
                                        } else {
                                            checkout = true;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        if (checkout == true) {
            entity.collisionOn = true;
        }
        return index;

    }

    public boolean check_monster(Entity entity, boolean player) {
        boolean monster = false;
        boolean checkout = false; // check if player is inside bomb or not
        for (int i = 0; i < gp.mons.length; ++i) {
            if (gp.mons[i] != null) {

                // Get entity's solid Area Position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid Area Position
                gp.mons[i].solidArea.x = gp.mons[i].worldX + gp.mons[i].solidArea.x;
                gp.mons[i].solidArea.y = gp.mons[i].worldY + gp.mons[i].solidArea.y;

                switch (entity.action) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.mons[i].solidArea)) {
                            if (player == true) {
                                checkout = true;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.mons[i].solidArea)) {
                            if (player == true) {
                                checkout = true;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.mons[i].solidArea)) {
                            if (player == true) {
                                checkout = true;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.mons[i].solidArea)) {
                            if (player == true) {
                                checkout = true;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.mons[i].solidArea.x = gp.mons[i].solidAreaDefaultX;
                gp.mons[i].solidArea.y = gp.mons[i].solidAreaDefaultY;
            }
            if (checkout == true) {
                entity.collisionOn = true;
                monster = true;
            }
        }
        return monster;
    }

}
