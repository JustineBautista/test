package entity;

import java.util.Random;

import CCTAdventure.GamePanel;

public class Monster extends Entity {

    public Monster(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    public void setupAI() {
        super.setupAI();
        setupProjectileAI();
    }

    private void setupProjectileAI() {
        int i = new Random().nextInt(100) + 1;

        if (i > 99
                && !getProjectile().isAlive()
                && getProjectileAvailableCounter() == 30
                && getProjectile().haveEnoughResource(this)) {

            getProjectile().set(getWorldX(), getWorldY(), getDirection(), true, this);
            getProjectile().subtractResource(this);
            getGamePanel().getProjectiles().add(getProjectile());
            setProjectileAvailableCounter(0);
        }
    }
}
