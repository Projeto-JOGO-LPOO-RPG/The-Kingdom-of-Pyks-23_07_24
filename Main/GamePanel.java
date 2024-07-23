import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    private final int fps = 60;

    private KeyHandler keyH;
    private Thread gameThread;

    private Jogador1 jogador1;

    private int playerX;
    private int playerY;
    private int playerSpeed;
    private Image currentSpriteCharacter;
    private boolean isSprite1 = true;
    private boolean showingInventory = false;
    private boolean paused = false;
    private int spriteTimer = 0;

    private Mapa gameMap;
    private int[][] mapArray;

    private int cameraX;
    private int cameraY;

    private List<Inimigo> inimigos;

    private Combate combate;



    public GamePanel(Gameplay gameplay) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.keyH = new KeyHandler(gameplay.getJogador1());
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.jogador1 = gameplay.getJogador1();
        this.playerX = jogador1.getCoordX();
        this.playerY = jogador1.getCoordY();
        this.playerSpeed = jogador1.getVelocidade();

        jogador1.setSpriteDirecao();
        this.currentSpriteCharacter = jogador1.getSpriteJogadorBaixo1().getImage();

        combate = new Combate(jogador1, inimigos);

        this.cameraX = 0;
        this.cameraY = 0;

        this.mapArray = new int[][] {{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,4,4,4,4,4},
        {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,4,1,4,1,1,4,4,1,4,1,4,1,1,4,1,4,4,4,4,4,4,1,4,4,4,4,4,4,4,4,4,2,4,4,4,4,4},
        {4,4,4,4,4,4,1,1,1,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,4,1,1,1,1,1,1,4,4,4,4,4,4,2,4,4,4,4,4},
        {4,4,4,5,5,5,1,1,1,1,1,1,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,4,4,4,4,4,2,4,4,4,4,4},
        {4,4,4,5,5,5,5,5,5,5,1,1,4,4,4,4,4,4,4,1,1,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,4,1,1,3,3,3,1,1,4,1,1,4,1,2,4,4,4,4,4},
        {4,4,4,5,5,5,5,5,5,5,1,1,4,4,4,4,4,4,4,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,1,1,4,4,4,4,3,3,3,3,3,1,1,1,1,1,1,1,2,4,4,4,4,4},
        {4,4,4,5,5,1,1,1,5,5,1,1,1,4,4,4,4,4,1,6,1,1,1,1,1,5,5,5,5,5,1,5,5,1,1,1,4,4,4,4,4,4,3,3,3,3,6,1,1,1,1,1,1,1,2,2,4,4,4,4},
        {4,4,4,1,1,1,1,1,1,5,5,1,1,1,7,4,4,4,4,4,1,1,1,5,5,5,5,5,5,5,5,5,5,5,5,1,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,4,4,4},
        {4,4,4,1,1,1,1,1,1,5,5,1,1,1,4,4,4,4,4,4,1,1,1,5,1,4,4,4,1,1,1,1,1,5,5,1,1,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,4,4,4},
        {4,4,4,4,1,1,1,1,1,5,5,1,1,1,4,4,4,4,1,1,6,1,1,5,1,4,4,4,4,4,4,1,1,5,5,1,1,4,4,4,4,1,1,1,1,1,1,1,1,5,1,1,1,1,1,2,4,4,4,4},
        {4,4,4,4,4,1,1,1,1,5,5,5,1,4,4,4,4,1,1,1,1,1,1,5,4,4,4,4,4,4,4,1,1,5,1,7,1,4,4,1,1,1,5,5,5,1,1,5,5,5,5,5,5,5,1,2,4,4,4,4},
        {4,4,4,4,4,4,1,1,1,1,5,5,1,4,4,4,4,1,1,1,1,1,5,5,4,4,4,4,1,1,1,5,5,5,1,1,4,4,4,1,1,1,5,5,5,5,5,5,5,5,5,5,5,5,1,1,1,4,4,4},
        {4,4,4,4,1,1,1,1,1,1,5,5,1,4,4,4,4,6,1,1,1,1,5,5,4,4,4,7,1,1,5,5,1,1,1,4,4,4,1,6,1,1,5,5,5,1,1,1,1,1,1,1,1,5,1,1,1,4,4,4},
        {4,4,4,1,1,1,1,1,1,1,1,5,1,1,4,4,4,4,1,1,1,1,5,5,1,4,4,1,1,1,5,1,1,4,4,4,4,4,1,1,1,1,5,5,1,1,1,1,4,4,4,1,1,5,5,1,1,1,4,4},
        {4,4,4,1,1,6,1,5,5,5,5,5,5,1,4,4,4,1,1,1,1,1,5,5,1,4,4,4,1,1,5,5,1,1,4,4,1,1,1,1,5,5,1,1,1,1,4,1,4,4,4,1,1,1,1,1,1,1,4,4},
        {4,4,1,1,1,1,1,5,5,1,1,5,5,1,4,4,4,1,1,1,5,5,5,5,1,4,4,4,1,1,5,5,1,1,4,4,7,1,1,1,5,5,1,1,1,1,4,4,4,4,4,4,1,1,1,2,1,4,4,4},
        {4,4,1,1,1,1,1,5,4,4,4,1,5,1,1,4,4,7,1,1,5,5,5,5,1,4,4,4,1,1,5,5,5,5,4,4,1,1,5,5,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,4,4,4},
        {4,4,1,1,1,1,5,5,4,4,4,1,5,1,1,1,1,1,1,1,5,1,1,1,1,1,4,4,1,1,1,5,5,1,4,4,1,1,5,5,5,1,1,1,1,1,4,1,1,7,1,4,4,1,1,2,1,4,4,4},
        {4,4,1,1,1,5,5,1,1,4,4,4,5,1,5,5,1,1,5,5,5,1,1,1,1,4,4,4,1,1,1,1,5,1,4,4,1,5,5,1,5,5,5,1,1,4,4,4,4,1,4,4,4,4,1,2,1,4,4,4},
        {4,4,1,1,1,5,1,1,4,4,4,4,5,5,5,5,5,5,5,5,5,1,1,1,1,4,4,4,4,1,1,1,5,1,4,4,1,5,5,1,1,5,5,1,1,4,4,4,4,1,4,1,4,4,1,2,1,4,4,4},
        {4,4,1,1,1,5,1,1,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,1,1,5,1,1,1,5,5,5,1,4,1,1,1,4,4,4,4,4,4,4,4,4,1,1,2,1,4,4,4},
        {4,4,1,1,5,5,1,1,1,1,1,1,1,1,1,1,1,4,4,1,1,6,1,1,1,1,1,1,4,4,4,1,5,5,5,5,5,5,1,4,4,1,1,1,4,4,4,4,4,4,1,1,4,4,1,2,1,4,4,4},
        {4,4,1,1,5,1,7,1,1,1,3,1,1,1,6,1,1,4,4,4,1,1,1,1,1,1,7,1,4,4,4,1,1,5,5,5,5,1,1,4,4,4,4,1,4,4,1,1,4,1,5,5,5,4,4,2,1,1,4,4},
        {4,4,1,1,5,1,1,1,1,3,3,3,3,3,3,1,1,4,4,4,4,4,4,4,1,1,1,1,4,4,4,4,1,1,1,1,1,1,1,1,4,4,1,1,1,1,1,1,1,5,5,5,5,5,1,2,1,7,4,4},
        {4,4,4,1,1,1,1,1,1,3,3,3,3,3,3,1,1,1,4,4,4,4,4,4,4,1,1,1,1,4,4,4,1,1,1,1,1,1,1,6,1,1,4,4,4,4,1,5,5,5,5,5,5,5,4,2,1,1,4,4},
        {4,4,4,4,4,1,1,1,1,1,1,3,1,1,1,1,1,1,1,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,4,4,4,4,1,5,5,5,5,4,2,1,1,4,4},
        {4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,6,4,4,4,4,4,4,4,1,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,7,1,4,4,4,4,4,4,4,4,4,2,1,1,4,4},
        {4,4,4,4,4,4,4,4,1,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,4,1,1,1,1,4,1,1,1,4,1,1,1,1,4,4,1,1,1,1,1,4,1,4,2,4,1,4,4},
        {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,4,4,4,4},
        {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,2,4,4,4,4}};

        this.gameMap = new Mapa(mapArray);


        inimigos = new ArrayList<>();
        iniciarInimigos();
    }


    private void iniciarInimigos() {
        for (int i = 0; i < 10; i++) {
            Inimigo inimigo;
            boolean vivo = false;
    
            if (i == 5) { 
                inimigo = Inimigo.gerarChefe("Chefe", 0);
            } else {
                inimigo = Inimigo.escolherInimigo("", 1);
            }
    
            while (!vivo) {
                int x = (int)(Math.random() * mapArray[0].length);
                int y = (int)(Math.random() * mapArray.length);
    
                if (mapArray[y][x] == 1) {
                    inimigo.setCoordX(x * tileSize);
                    inimigo.setCoodY(y * tileSize);
    
                    inimigos.add(inimigo);
                    vivo = true;
                }
            }
        }
    }

    private boolean isCollisionInimigos(int x, int y) {
        for (Inimigo inimigo : inimigos) {
            int inimigoX = inimigo.getCoordX();
            int inimigoY = inimigo.getCoordY();
    
            if (x < inimigoX + tileSize/1.5 &&
                x + tileSize/1.5 > inimigoX &&
                y < inimigoY + tileSize/1.5 &&
                y + tileSize/1.5 > inimigoY) {
                return true; 
            }

        if (inimigo.getCoordX() < 0) inimigoX = 0;
        if (inimigoY < 0) inimigoY = 0;
        if (inimigoX > screenWidth - tileSize) inimigoX = screenWidth - tileSize;
        if (inimigoY > screenHeight - tileSize) inimigoY = screenHeight - tileSize;
        }
        return false;
    }

    
    private boolean isCollisionMap(int x, int y) {
        int left = x;
        int right = x + 36 - 1;
        int top = y;
        int bottom = y + 36 - 1;

        for (int row = 0; row < gameMap.getRows(); row++) {
            for (int col = 0; col < gameMap.getCols(); col++) {
                int tileType = mapArray[row][col];
                if (tileType == 2 ||
                    tileType == 3 ||
                    tileType == 4 ||
                    tileType == 6) {
                    int tileLeft = col * tileSize;
                    int tileRight = tileLeft + tileSize - 1;
                    int tileTop = row * tileSize;
                    int tileBottom = tileTop + tileSize - 1;

                

                    if (right >= tileLeft && left <= tileRight && bottom >= tileTop && top <= tileBottom) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void iniciarCombate(Jogador1 jogador, Inimigo inimigo) {
        combate.combater(inimigo);
   
    }

    public void update() {
        int newPlayerX = playerX;
        int newPlayerY = playerY;

        if (keyH.upPressed) {
            newPlayerY -= playerSpeed;
        } else if (keyH.downPressed) {
            newPlayerY += playerSpeed;
        } else if (keyH.leftPressed) {
            newPlayerX -= playerSpeed;
        } else if (keyH.rightPressed) {
            newPlayerX += playerSpeed;
        }
    
        if (!isCollisionMap(newPlayerX, newPlayerY) && !isCollisionInimigos(newPlayerX, newPlayerY)) {
            playerX = newPlayerX;
            playerY = newPlayerY;
            jogador1.setCoordX(playerX);
            jogador1.setCoodY(playerY);
        }

        for (Inimigo inimigo : inimigos) {
            int inimigoX = inimigo.getCoordX();
            int inimigoY = inimigo.getCoordY();
        
            inimigo.movimentarInimigo();
            int novoInimigoX = inimigo.getCoordX();
            int novoInimigoY = inimigo.getCoordY();
        
            if (!isCollisionMap(novoInimigoX, novoInimigoY)) {
                inimigo.setCoordX(novoInimigoX);
                inimigo.setCoodY(novoInimigoY);
            } else {
                inimigo.setCoordX(inimigoX);
                inimigo.setCoodY(inimigoY);
            }
        
            if (isCollisionInimigos(playerX, playerY)) {
                iniciarCombate(jogador1, inimigo);
            }
        }

        spriteTimer++;
        if (spriteTimer > fps / 3) {
            isSprite1 = !isSprite1;
            spriteTimer = 0;
        }
        
        if (keyH.upPressed) {
            currentSpriteCharacter = isSprite1 ? jogador1.getSpriteJogadorCima1().getImage() : jogador1.getSpriteJogadorCima2().getImage();
        } else if (keyH.downPressed) {
            currentSpriteCharacter = isSprite1 ? jogador1.getSpriteJogadorBaixo1().getImage() : jogador1.getSpriteJogadorBaixo2().getImage();
        } else if (keyH.leftPressed) {
            currentSpriteCharacter = isSprite1 ? jogador1.getSpriteJogadorEsq1().getImage() : jogador1.getSpriteJogadorEsq2().getImage();
        } else if (keyH.rightPressed) {
            currentSpriteCharacter = isSprite1 ? jogador1.getSpriteJogadorDir1().getImage() : jogador1.getSpriteJogadorDir2().getImage();
        } else {
            currentSpriteCharacter = isSprite1 ? jogador1.getSpriteJogadorBaixo1().getImage() : jogador1.getSpriteJogadorBaixo2().getImage();
        }
        
        cameraX = playerX - screenWidth / 2;
        cameraY = playerY - screenHeight / 2;
        
        if (cameraX < 0) {
            cameraX = 0;
        }
        if (cameraY < 0) {
            cameraY = 0;
        }
        if (cameraX > gameMap.getCols() * tileSize - screenWidth) {
            cameraX = gameMap.getCols() * tileSize - screenWidth;
        }
        if (cameraY > gameMap.getRows() * tileSize - screenHeight) {
            cameraY = gameMap.getRows() * tileSize - screenHeight;
        }
    }
    
     public void togglePause() {
        paused = !paused;
    }

    public void toggleInventory() {
        showingInventory = !showingInventory;
    }

@Override
public void run() {
    clearScreen();
    double drawInterval = 1000000000 / fps;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    System.out.println("Rodando");

    while (gameThread != null) {
        currentTime = System.nanoTime();

        delta += (currentTime - lastTime) / drawInterval;
        timer += (currentTime - lastTime);
        lastTime = currentTime;

        if (keyH.pausePressed) {
            togglePause();
            keyH.pausePressed = false;
        }

        if (delta >= 1) {
            if (!paused) {
                update();
            }
            repaint();

            delta--;
            drawCount++;
        }

        if (timer >= 1000000000) {
            System.out.println("fps: " + drawCount);
            drawCount = 0;
            timer = 0;
        }
    }
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gameMap.drawMap(g2, cameraX, cameraY);

        g2.drawImage(currentSpriteCharacter, playerX - cameraX, playerY - cameraY, tileSize, tileSize, this);

        for (Inimigo inimigo : inimigos) {
            Image currentSpriteEnemy = getSpriteForInimigo(inimigo);
            g2.drawImage(currentSpriteEnemy, inimigo.getCoordX() - cameraX, inimigo.getCoordY() - cameraY, tileSize, tileSize, this);
        }
    }

    private Image getSpriteForInimigo(Inimigo inimigo) {
        String direcaoInimigo = inimigo.getDirecaoAtual();
        if (direcaoInimigo.equals("cima")) {
            return isSprite1 ? inimigo.getSpriteInimigoCima1().getImage() : inimigo.getSpriteInimigoCima2().getImage();
        } else if (direcaoInimigo.equals("baixo")) {
            return isSprite1 ? inimigo.getSpriteInimigoBaixo1().getImage() : inimigo.getSpriteInimigoBaixo2().getImage();
        } else if (direcaoInimigo.equals("esquerda")) {
            return isSprite1 ? inimigo.getSpriteInimigoEsq1().getImage() : inimigo.getSpriteInimigoEsq2().getImage();
        } else if (direcaoInimigo.equals("direita")) {
            return isSprite1 ? inimigo.getSpriteInimigoDir1().getImage() : inimigo.getSpriteInimigoDir2().getImage();
        } else {
            return isSprite1 ? inimigo.getSpriteInimigoBaixo1().getImage() : inimigo.getSpriteInimigoBaixo2().getImage();
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
