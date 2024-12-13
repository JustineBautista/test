package entity;

import CCTAdventure.GamePanel;

public class NPC_Guard extends NPC{

	public NPC_Guard(GamePanel gamePanel) {
        super(gamePanel);

        setName("Old Man");
        getAnimationImages();
    }

    public void getAnimationImages() {
        setUp1(setup("/npc/oldman_up_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setUp2(setup("/npc/oldman_up_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown1(setup("/npc/oldman_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown2(setup("/npc/oldman_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft1(setup("/npc/oldman_left_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft2(setup("/npc/oldman_left_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight1(setup("/npc/oldman_right_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight2(setup("/npc/oldman_right_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
    }

    @Override
    public void setupAI() {
        super.setupAI();
    }

    @Override
    public void speak() {
        super.speak();
    }
}
