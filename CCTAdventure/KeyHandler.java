package CCTAdventure;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class KeyHandler {
    private boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, projectileKeyPressed;
    private final GamePanel gamePanel;
    private boolean showDebugText = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (gamePanel.getGameState()) {
            case GamePanel.TITLE_STATE -> handleTitleState(code);
            case GamePanel.PLAY_STATE -> handlePlayState(code);
            case GamePanel.PAUSE_STATE -> handlePauseState(code);
            case GamePanel.DIALOGUE_STATE -> handleDialogueState(code);
            case GamePanel.CHARACTER_STATE -> handleCharacterState(code);
            case GamePanel.OPTION_STATE -> handleOptionState(code);
            case GamePanel.GAME_OVER_STATE -> handleGameOverState(code);
            case GamePanel.TRADE_STATE -> handleTradeState(code);
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        KeyCode code = event.getCode();
        
        switch (code) {
            case W -> upPressed = false;
            case S -> downPressed = false;
            case A -> leftPressed = false;
            case D -> rightPressed = false;
            case SPACE -> spacePressed = false;
            case F -> projectileKeyPressed = false;
        }
    }

    private void handleTitleState(KeyCode code) {
        if (gamePanel.getUi().getTitleScreenState() == 0) {
            handleMainTitleScreen(code);
        } else if (gamePanel.getUi().getTitleScreenState() == 1) {
            handleCharacterSelectionScreen(code);
        }
    }

    private void handleMainTitleScreen(KeyCode code) {
        switch (code) {
            case UP -> {
                gamePanel.getUi().decrementCommandNumber();
                gamePanel.playSoundEffect(9);
            }
            case DOWN -> {
                gamePanel.getUi().incrementCommandNumber();
                gamePanel.playSoundEffect(9);
            }
            case ENTER -> enterPressed = true;
        }
    }

    private void handlePlayState(KeyCode code) {
        switch (code) {
            case W -> upPressed = true;
            case S -> downPressed = true;
            case A -> leftPressed = true;
            case D -> rightPressed = true;
            case P -> gamePanel.setGameState(GamePanel.PAUSE_STATE);
            case C -> gamePanel.setGameState(GamePanel.CHARACTER_STATE);
            case ESCAPE -> gamePanel.setGameState(GamePanel.OPTION_STATE);
            case ENTER -> enterPressed = true;
            case SPACE -> spacePressed = true;
            case F -> projectileKeyPressed = true;
            case T -> showDebugText = !showDebugText;
            case R -> handleMapReload();
        }
    }

    private void handleCharacterSelectionScreen(KeyCode code) {
        switch (code) {
            case UP -> {
                gamePanel.getUi().decrementCommandNumber(3);
                gamePanel.playSoundEffect(9);
            }
            case DOWN -> {
                gamePanel.getUi().incrementCommandNumber(3);
                gamePanel.playSoundEffect(9);
            }
            case ENTER -> enterPressed = true;
        }
    }

    private void handleCharacterScreenEnter() {
        switch (gamePanel.getUi().getCommandNumber()) {
            case 0 -> System.out.println("Fighter selected!");
            case 1 -> {
                System.out.println("Rogue selected!");
                startGame();
            }
            case 2 -> {
                System.out.println("Sorcerer selected!");
                startGame();
            }
            case 3 -> gamePanel.getUi().setTitleScreenState(0);
        }
    }

    private void startGame() {
        gamePanel.setGameState(GamePanel.PLAY_STATE);
        gamePanel.playMusic(0);
    }

    private void handleMapReload() {
        switch (gamePanel.getCurrentMap()) {
            case 0 -> gamePanel.getTileManager().loadMap("/maps/worldV3.txt", 0);
            case 1 -> gamePanel.getTileManager().loadMap("/maps/interior01.txt", 1);
            case 2 -> gamePanel.getTileManager().loadMap("/maps/WORLD.txt", 2);
            case 3 -> gamePanel.getTileManager().loadMap("/maps/house.txt", 3);
        }
    }

    private void handlePauseState(KeyCode code) {
        if (code == KeyCode.P) {
            gamePanel.setGameState(GamePanel.PLAY_STATE);
        }
    }

    private void handleDialogueState(KeyCode code) {
        if (code == KeyCode.ENTER) {
            gamePanel.setGameState(GamePanel.PLAY_STATE);
        }
    }

    private void handleCharacterState(KeyCode code) {
        switch (code) {
            case C -> gamePanel.setGameState(GamePanel.PLAY_STATE);
            case ENTER -> gamePanel.getPlayer().selectItem();
            case UP, DOWN, LEFT, RIGHT -> handleInventoryMovement(code);
        }
    }

    private void handleInventoryMovement(KeyCode code) {
        switch (code) {
            case UP -> {
                if (gamePanel.getUi().getPlayerSlotRow() > 0) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().decrementPlayerSlotRow();
                }
            }
            case DOWN -> {
                if (gamePanel.getUi().getPlayerSlotRow() < 3) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().incrementPlayerSlotRow();
                }
            }
            case LEFT -> {
                if (gamePanel.getUi().getPlayerSlotCol() > 0) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().decrementPlayerSlotCol();
                }
            }
            case RIGHT -> {
                if (gamePanel.getUi().getPlayerSlotCol() < 4) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().incrementPlayerSlotCol();
                }
            }
        }
    }

    private void handleOptionState(KeyCode code) {
        switch (code) {
            case ESCAPE -> gamePanel.setGameState(GamePanel.PLAY_STATE);
            case ENTER -> enterPressed = true;
            case UP, DOWN -> handleOptionMenuNavigation(code);
            case LEFT, RIGHT -> handleVolumeControl(code);
        }
    }

    private void handleOptionMenuNavigation(KeyCode code) {
        int maxCommandNumber = switch (gamePanel.getUi().getSubState()) {
            case 0 -> 5;
            case 3 -> 1;
            default -> 0;
        };

        if (code == KeyCode.UP) {
            gamePanel.getUi().decrementCommandNumber(maxCommandNumber);
        } else {
            gamePanel.getUi().incrementCommandNumber(maxCommandNumber);
        }
        gamePanel.playSoundEffect(9);
    }

    private void handleVolumeControl(KeyCode code) {
        if (gamePanel.getUi().getSubState() == 0) {
            handleMusicAndSoundVolume(code);
        }
    }

    private void handleMusicAndSoundVolume(KeyCode code) {
        if (gamePanel.getUi().getCommandNumber() == 1) {
            handleMusicVolume(code);
        } else if (gamePanel.getUi().getCommandNumber() == 2) {
            handleSoundVolume(code);
        }
    }

    private void handleMusicVolume(KeyCode code) {
        if (code == KeyCode.LEFT && gamePanel.getMusic().getVolumeScale() > 0) {
            gamePanel.getMusic().decrementVolume();
            gamePanel.getMusic().checkVolume();
            gamePanel.playSoundEffect(9);
        } else if (code == KeyCode.RIGHT && gamePanel.getMusic().getVolumeScale() < 5) {
            gamePanel.getMusic().incrementVolume();
            gamePanel.getMusic().checkVolume();
            gamePanel.playSoundEffect(9);
        }
    }

    private void handleSoundVolume(KeyCode code) {
        if (code == KeyCode.LEFT && gamePanel.getSoundEffect().getVolumeScale() > 0) {
            gamePanel.getSoundEffect().decrementVolume();
            gamePanel.playSoundEffect(9);
        } else if (code == KeyCode.RIGHT && gamePanel.getSoundEffect().getVolumeScale() < 5) {
            gamePanel.getSoundEffect().incrementVolume();
            gamePanel.playSoundEffect(9);
        }
    }

    private void handleGameOverState(KeyCode code) {
        switch (code) {
            case UP -> {
                gamePanel.getUi().decrementCommandNumber(1);
                gamePanel.playSoundEffect(9);
            }
            case DOWN -> {
                gamePanel.getUi().incrementCommandNumber(1);
                gamePanel.playSoundEffect(9);
            }
            case ENTER -> enterPressed = true;
        }
    }

    private void handleTradeState(KeyCode code) {
        switch (code) {
            case ENTER -> enterPressed = true;
            case ESCAPE -> handleTradeEscape();
            default -> handleTradeNavigation(code);
        }
    }

    private void handleTradeEscape() {
        if (gamePanel.getUi().getSubState() == 1 || gamePanel.getUi().getSubState() == 2) {
            gamePanel.getUi().setSubState(0);
        }
    }

    private void handleTradeNavigation(KeyCode code) {
        switch (gamePanel.getUi().getSubState()) {
            case 0 -> handleTradeMainMenu(code);
            case 1 -> handleNpcInventory(code);
            case 2 -> handleInventoryMovement(code);
        }
    }

    private void handleTradeMainMenu(KeyCode code) {
        if (code == KeyCode.UP) {
            gamePanel.getUi().decrementCommandNumber(2);
            gamePanel.playSoundEffect(9);
        } else if (code == KeyCode.DOWN) {
            gamePanel.getUi().incrementCommandNumber(2);
            gamePanel.playSoundEffect(9);
        }
    }

    private void handleNpcInventory(KeyCode code) {
        switch (code) {
            case UP -> {
                if (gamePanel.getUi().getNpcSlotRow() > 0) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().decrementNpcSlotRow();
                }
            }
            case DOWN -> {
                if (gamePanel.getUi().getNpcSlotRow() < 3) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().incrementNpcSlotRow();
                }
            }
            case LEFT -> {
                if (gamePanel.getUi().getNpcSlotCol() > 0) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().decrementNpcSlotCol();
                }
            }
            case RIGHT -> {
                if (gamePanel.getUi().getNpcSlotCol() < 4) {
                    gamePanel.playSoundEffect(9);
                    gamePanel.getUi().incrementNpcSlotCol();
                }
            }
        }
    }

    // Getters and setters
    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }
    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }
    public boolean isEnterPressed() { return enterPressed; }
    public boolean isSpacePressed() { return spacePressed; }
    public boolean isProjectileKeyPressed() { return projectileKeyPressed; }
    public boolean isShowDebugText() { return showDebugText; }

    // Fluent setters
    public KeyHandler setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
        return this;
    }

    public KeyHandler setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
        return this;
    }

    public KeyHandler setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
        return this;
    }

    public KeyHandler setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
        return this;
    }

    public KeyHandler setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
        return this;
    }

    public KeyHandler setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
        return this;
    }

    public KeyHandler setProjectileKeyPressed(boolean projectileKeyPressed) {
        this.projectileKeyPressed = projectileKeyPressed;
        return this;
    }

    public KeyHandler setShowDebugText(boolean showDebugText) {
        this.showDebugText = showDebugText;
        return this;
    }
}


