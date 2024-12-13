package objects;

import javax.imageio.ImageIO;

import CCTAdventure.GamePanel;
import CCTAdventure.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Shield_Wood extends Shield {

    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);

        setName("Wooden Shield");
        setDescription("[" + getName() + "]\nMade of wood");
        setDefenseValue(1);
        setPrice(35);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/shield_wood.png")));
            setImage1(UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
