package objects;

import javax.imageio.ImageIO;

import CCTAdventure.GamePanel;
import CCTAdventure.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Sword_Normal extends Weapon {

    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);

        setName("Normal Sword");
        setDescription("[" + getName() + "]\nAn old sword");
        setAttackValue(1);
        getAttackArea().width = 36;
        getAttackArea().height = 36;
        setPrice(20);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sword_normal.png")));
            setImage1(UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
