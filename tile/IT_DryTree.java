package tile;

import java.awt.*;

import CCTAdventure.GamePanel;
import objects.OBJ_Axe;
import objects.Weapon;

public class IT_DryTree extends InteractiveTile {

    private final GamePanel gamePanel;

    public IT_DryTree(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        setDown1(setup("/tiles/drytree", getGamePanel().getTileSize(), getGamePanel().getTileSize()));

        setDestructible(true);
        setCollisionDefaultX(-2);
        setCollisionDefaultY(-2);
        setCurrentLife(3);
    }

    @Override
    public boolean isCorrectWeapon(Weapon weapon) {
        return weapon instanceof OBJ_Axe;
    }

    @Override
    public void playSoundEffect() {
        gamePanel.playSoundEffect(7);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gamePanel);
        tile.setWorldX(getWorldX());
        tile.setWorldY(getWorldY());
        tile.setIndex(getIndex());
        return tile;
    }

    @Override
    public Color getParticleColor() {
        return new Color(65, 50, 30);
    }

    @Override
    public int getParticleSize() {
        return 6; // 6 pixels
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
