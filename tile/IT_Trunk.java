package tile;

import CCTAdventure.GamePanel;

public class IT_Trunk extends InteractiveTile {

    public IT_Trunk(GamePanel gamePanel) {
        super(gamePanel);

        setDown1(setup("/tiles/trunk", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        removeCollisionArea();
    }

    private void removeCollisionArea() {
        getCollisionArea().x = 0;
        getCollisionArea().y = 0;
        getCollisionArea().width = 0;
        getCollisionArea().height = 0;
        setCollisionDefaultX(getCollisionArea().x);
        setCollisionDefaultY(getCollisionArea().y);
    }
}
