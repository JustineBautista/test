package objects;

import javax.imageio.ImageIO;

import CCTAdventure.GamePanel;
import CCTAdventure.UtilityTool;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends Object {

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);

        setName("Key");
        setDescription("[" + getName() + "]\nIt opens a door");
        setPrice(100);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            setImage1(UtilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
