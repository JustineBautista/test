package objects;

import java.awt.*;

import CCTAdventure.GamePanel;
import entity.Entity;
import entity.Projectile;

public class OBJ_Fireball extends Projectile {

    public OBJ_Fireball(GamePanel gamePanel) {
        super(gamePanel);

        setName("Fireball");
        setSpeed(6);
        setMaxLife(80);
        setCurrentLife(getMaxLife());
        setAttackPower(2);
        setUseCost(1);
        setAlive(false);

        getAnimationImages();
    }

    public void getAnimationImages() {
        setUp1(setup("/ability/fireball_up_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setUp2(setup("/ability/fireball_up_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown1(setup("/ability/fireball_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown2(setup("/ability/fireball_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft1(setup("/ability/fireball_left_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft2(setup("/ability/fireball_left_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight1(setup("/ability/fireball_right_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight2(setup("/ability/fireball_right_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
    }

    @Override
    public boolean haveEnoughResource(Entity user) {
        return user.getCurrentMana() >= getUseCost();
    }

    @Override
    public void subtractResource(Entity user) {
        user.setCurrentMana(user.getCurrentMana() - getUseCost());
    }

    @Override
    public Color getParticleColor() {
        return new Color(240, 50, 0);
    }

    @Override
    public int getParticleSize() {
        return 10; // pixels
    }

    @Override
    public int getParticleSpeed() {
        return 1;
    }

    @Override
    public int getParticleMaxLife() {
        return 20; // How long it will last
    }
}
