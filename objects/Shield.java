package objects;

import CCTAdventure.GamePanel;

public class Shield extends Object {
    private int defenseValue;

    public Shield(GamePanel gamePanel) {
        super(gamePanel);
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
}
