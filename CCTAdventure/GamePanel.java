package CCTAdventure;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tile.InteractiveTile;
import tile.TileManager;
import javafx.scene.image.WritableImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import entity.Player;

public class GamePanel extends Pane {
    // Screen settings
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenColumns = 20;
    private final int maxScreenRows = 12;
    private final int screenWidth = tileSize * maxScreenColumns;
    private final int screenHeight = tileSize * maxScreenRows;
    
    // World settings
    private final int maxWorldColumns = 50;
    private final int maxWorldRows = 50;
    private final int worldWidth = tileSize * maxWorldColumns;
    private final int worldHeight = tileSize * maxWorldRows;
    private final int maxMaps = 10;
    
    // Game states
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    private final int characterState = 4;
    private final int optionState = 5;
    private final int gameOverState = 6;
    private final int transitionState = 7;
    private final int tradeState = 8;
    private int gameState;
    
    // Game components
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    private final int FPS = 60;
    
    // Game systems
    private final KeyHandler keyHandler;
    private final CollisionDetector collisionDetector;
    private final TileManager tileManager;
    private final AssetManager assetManager;
    private final SoundManager soundManager;
    private final UIManager uiManager;
    private final EventHandler eventHandler;
    private final ConfigManager configManager;
    
    // Game entities
    private final Player player;
    private final List<Asset> assets = new ArrayList<>();
    private final Asset[][] objects;
    private final Asset[][] npcs;
    private final Asset[][] monsters;
    private final InteractiveTile[][] interactiveTiles;
    private final List<Asset> projectiles = new ArrayList<>();
    private final List<Asset> particles = new ArrayList<>();
    
    // Game state
    private int currentMap = 0;
    private boolean fullScreenOn = false;

    public GamePanel() {
        initializeComponents();
        setupCanvas();
        setupGameLoop();
        setupInputHandling();
    }

    private void initializeComponents() {
        keyHandler = new KeyHandler(this);
        collisionDetector = new CollisionDetector(this);
        tileManager = new TileManager(this);
        assetManager = new AssetManager(this);
        soundManager = new SoundManager();
        uiManager = new UIManager(this);
        eventHandler = new EventHandler(this);
        configManager = new ConfigManager(this);
        
        player = new Player(this, keyHandler);
        objects = new Asset[maxMaps][20];
        npcs = new Asset[maxMaps][10];
        monsters = new Asset[maxMaps][20];
        interactiveTiles = new InteractiveTile[maxMaps][100];
    }

    private void setupCanvas() {
        gameCanvas = new Canvas(screenWidth, screenHeight);
        gc = gameCanvas.getGraphicsContext2D();
        getChildren().add(gameCanvas);
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }
                
                double elapsedTime = (now - lastUpdate) / 1_000_000_000.0;
                if (elapsedTime >= 1.0 / FPS) {
                    update();
                    render();
                    lastUpdate = now;
                }
            }
        };
    }

    private void setupInputHandling() {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(keyHandler::handleKeyPress);
        gameCanvas.setOnKeyReleased(keyHandler::handleKeyRelease);
        gameCanvas.requestFocus();
    }

    public void startGame() {
        setUpGame();
        gameLoop.start();
    }

    public void setUpGame() {
        assetManager.setObjects();
        assetManager.setNPCs();
        assetManager.setMonsters();
        assetManager.setInteractiveTiles();
        gameState = titleState;
    }

    private void update() {
        if (gameState == playState) {
            player.update();
            updateNPCs();
            updateMonsters();
            updateProjectiles();
            updateParticles();
            updateInteractiveTiles();
        }
    }

    private void updateNPCs() {
        for (Asset npc : npcs[currentMap]) {
            if (npc != null) {
                npc.update();
            }
        }
    }

    private void updateMonsters() {
        for (int i = 0; i < monsters[currentMap].length; i++) {
            Asset monster = monsters[currentMap][i];
            if (monster != null) {
                if (monster.isAlive() && !monster.isDying()) {
                    monster.update();
                }
                if (!monster.isAlive()) {
                    monster.checkDrop();
                    monsters[currentMap][i] = null;
                }
            }
        }
    }

    private void updateProjectiles() {
        projectiles.removeIf(projectile -> {
            if (projectile != null) {
                if (projectile.isAlive()) {
                    projectile.update();
                    return false;
                }
                return true;
            }
            return true;
        });
    }

    private void updateParticles() {
        particles.removeIf(particle -> {
            if (particle != null) {
                if (particle.isAlive()) {
                    particle.update();
                    return false;
                }
                return true;
            }
            return true;
        });
    }

    private void updateInteractiveTiles() {
        for (InteractiveTile tile : interactiveTiles[currentMap]) {
            if (tile != null) {
                tile.update();
            }
        }
    }

    private void render() {
        gc.clearRect(0, 0, screenWidth, screenHeight);
        
        if (gameState == titleState) {
            uiManager.drawTitleScreen(gc);
        } else {
            // Draw game elements
            tileManager.draw(gc);
            drawInteractiveTiles();
            drawGameElements();
            uiManager.draw(gc);
        }

        if (keyHandler.isShowDebugText()) {
            drawDebugInfo();
        }
    }

    private void drawInteractiveTiles() {
        for (InteractiveTile tile : interactiveTiles[currentMap]) {
            if (tile != null) {
                tile.draw(gc);
            }
        }
    }

    private void drawGameElements() {
        // Clear and populate assets list
        assets.clear();
        assets.add(player);
        
        // Add active NPCs
        for (Asset npc : npcs[currentMap]) {
            if (npc != null) assets.add(npc);
        }
        
        // Add visible objects
        for (Asset object : objects[currentMap]) {
            if (object != null) assets.add(object);
        }
        
        // Add active monsters
        for (Asset monster : monsters[currentMap]) {
            if (monster != null) assets.add(monster);
        }
        
        // Add projectiles and particles
        assets.addAll(projectiles);
        assets.addAll(particles);
        
        // Sort by Y position for proper layering
        assets.sort((a, b) -> Integer.compare(a.getWorldY(), b.getWorldY()));
        
        // Draw all assets
        for (Asset asset : assets) {
            asset.draw(gc);
        }
    }

    private void drawDebugInfo() {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 20));
        
        int x = 10;
        int y = 20;
        int lineHeight = 20;
        
        gc.fillText("WorldX: " + player.getWorldX(), x, y);
        y += lineHeight;
        gc.fillText("WorldY: " + player.getWorldY(), x, y);
        y += lineHeight;
        gc.fillText("Col: " + (player.getWorldX() + player.getCollisionArea().getX()) / tileSize, x, y);
        y += lineHeight;
        gc.fillText("Row: " + (player.getWorldY() + player.getCollisionArea().getY()) / tileSize, x, y);
    }

}

