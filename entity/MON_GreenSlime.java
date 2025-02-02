package entity;

import java.awt.*;
import java.util.Random;

import CCTAdventure.GamePanel;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;
import objects.OBJ_Rock;

public class MON_GreenSlime extends Monster {

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        setName("Green Slime");
        setDirection("down");
        setSpeed(1);
        setMaxLife(4);
        setCurrentLife(getMaxLife());
        setAttackPower(5);
        setDefensePower(0);
        setExp(2);

        setProjectile(new OBJ_Rock(gamePanel));
        setMaxAmmo(5);
        setCurrentAmmo(getMaxAmmo());

        setCollisionArea(new Rectangle(3, 18, 42, 30));
        setCollisionDefaultX(getCollisionArea().x);
        setCollisionDefaultY(getCollisionArea().y);

        getAnimationImages();
    }

    public void getAnimationImages() {
        setUp1(setup("/monster/greenslime_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setUp2(setup("/monster/greenslime_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown1(setup("/monster/greenslime_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown2(setup("/monster/greenslime_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft1(setup("/monster/greenslime_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft2(setup("/monster/greenslime_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight1(setup("/monster/greenslime_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight2(setup("/monster/greenslime_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
    }

    @Override
    public void damageReaction() {
        setActionLockCounter(0);
        setDirection(getGamePanel().getPlayer().getDirection());
    }

    @Override
    public void setupAI() {
        super.setupAI();
    }

    @Override
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;

        if (i < 50) {
            dropObject(new OBJ_Coin_Bronze(getGamePanel()));
        }

        if (i >= 50 && i < 75) {
            dropObject(new OBJ_Heart(getGamePanel()));
        }

        if (i >= 75 && i < 100) {
            dropObject(new OBJ_ManaCrystal(getGamePanel()));
        }
    }

}
