package objects;

import javax.imageio.ImageIO;

import CCTAdventure.GamePanel;
import CCTAdventure.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Axe extends Weapon {

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);

        setName("Woodcutter's Axe");
        setDescription("[" + getName() + "]\nA bit rusty, but still \nenough for cutting trees");
        setAttackValue(2);
        getAttackArea().width = 30;
        getAttackArea().height = 30;
        setPrice(75);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/axe.png")));
            setImage1(UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
