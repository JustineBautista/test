package entity;

import CCTAdventure.GamePanel;
import objects.OBJ_Axe;
import objects.OBJ_Key;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Normal;

public class NPC_Merchant extends NPC {

    public NPC_Merchant(GamePanel gamePanel) {
        super(gamePanel);

        setName("Merchant");

        getAnimationImages();
        setDialogue();
        setItems();
    }

    public void getAnimationImages() {
        setUp1(setup("/npc/merchant_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setUp2(setup("/npc/merchant_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown1(setup("/npc/merchant_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setDown2(setup("/npc/merchant_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft1(setup("/npc/merchant_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setLeft2(setup("/npc/merchant_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight1(setup("/npc/merchant_down_1", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
        setRight2(setup("/npc/merchant_down_2", getGamePanel().getTileSize(), getGamePanel().getTileSize()));
    }

    public void setDialogue() {
        getDialogues()[0] = "He he, so you found me. \nI have some good stuff. \nDo you want to trade?";
    }

    public void setItems() {
        getInventory().add(new OBJ_Potion_Red(getGamePanel()));
        getInventory().add(new OBJ_Key(getGamePanel()));
        getInventory().add(new OBJ_Sword_Normal(getGamePanel()));
        getInventory().add(new OBJ_Axe(getGamePanel()));
        getInventory().add(new OBJ_Shield_Wood(getGamePanel()));
        getInventory().add(new OBJ_Shield_Blue(getGamePanel()));
    }

    @Override
    public void speak() {
        super.speak();

        getGamePanel().setGameState(getGamePanel().getTradeState());
        getGamePanel().getUi().setNpc(this);
    }
}
