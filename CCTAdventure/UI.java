package CCTAdventure;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entity.Entity;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;

public class UI {

    private final GamePanel gamePanel;
    private Graphics2D graphics2D;
    private final BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    private Font maruMonica;
    private List<String> messages = new ArrayList<>();
    private List<Integer> messageCounter = new ArrayList<>();
    private boolean gameFinished = false;
    private String currentDialogue;
    private int commandNumber;
    private int titleScreenState;
    private int playerSlotCol;
    private int playerSlotRow;
    private int npcSlotCol;
    private int npcSlotRow;
    private int subState;
    private int counter;
    private Entity npc;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        setupFonts();

        OBJ_Heart heart = new OBJ_Heart(gamePanel);
        this.heart_full = heart.getImage1();
        this.heart_half = heart.getImage2();
        this.heart_blank = heart.getImage3();

        OBJ_ManaCrystal manaCrystal = new OBJ_ManaCrystal(gamePanel);
        this.crystal_full = manaCrystal.getImage1();
        this.crystal_blank = manaCrystal.getImage2();

        OBJ_Coin_Bronze bronzeCoin = new OBJ_Coin_Bronze(gamePanel);
        this.coin = bronzeCoin.getImage1();
    }

    private void setupFonts() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(inputStream));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        setupDefaultGraphics(graphics2D);

        if (gamePanel.getGameState() == gamePanel.getTitleState()) {
            drawTitleScreen();
        }

        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife();
            drawPlayerMana();
            drawMessages();
        }

        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPlayerLife();
            drawPlayerMana();
            drawPauseScreen();
        }

        if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            drawDialogueScreen();
        }

        if (gamePanel.getGameState() == gamePanel.getCharacterState()) {
            drawCharacterScreen();
            drawInventoryScreen(gamePanel.getPlayer(), true);
        }

        if (gamePanel.getGameState() == gamePanel.getOptionState()) {
            drawOptionScreen();
        }

        if (gamePanel.getGameState() == gamePanel.getGameOverState()) {
            drawGameOverScreen();
        }

        if (gamePanel.getGameState() == gamePanel.getTransitionState()) {
            drawTransitionScreen();
        }

        if (gamePanel.getGameState() == gamePanel.getTradeState()) {
            drawTradeScreen();
        }
    }

    private void setupDefaultGraphics(Graphics2D graphics2D) {
        graphics2D.setFont(maruMonica);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
    }

    // ... Rest of the code remains unchanged ...

    public UI setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
        return this;
    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public UI setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
        return this;
    }

    public int getCommandNumber() {
        return commandNumber;
    }

    public UI setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
        return this;
    }

    public int getTitleScreenState() {
        return titleScreenState;
    }

    public UI setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
        return this;
    }

    public int getPlayerSlotCol() {
        return playerSlotCol;
    }

    public UI setPlayerSlotCol(int playerSlotCol) {
        this.playerSlotCol = playerSlotCol;
        return this;
    }

    public int getPlayerSlotRow() {
        return playerSlotRow;
    }

    public UI setPlayerSlotRow(int playerSlotRow) {
        this.playerSlotRow = playerSlotRow;
        return this;
    }

    public int getSubState() {
        return subState;
    }

    public UI setSubState(int subState) {
        this.subState = subState;
        return this;
    }

    public Entity getNpc() {
        return npc;
    }

    public UI setNpc(Entity npc) {
        this.npc = npc;
        return this;
    }

    public int getNpcSlotCol() {
        return npcSlotCol;
    }

    public UI setNpcSlotCol(int npcSlotCol) {
        this.npcSlotCol = npcSlotCol;
        return this;
    }

    public int getNpcSlotRow() {
        return npcSlotRow;
    }

    public UI setNpcSlotRow(int npcSlotRow) {
        this.npcSlotRow = npcSlotRow;
        return this;
    }
}
