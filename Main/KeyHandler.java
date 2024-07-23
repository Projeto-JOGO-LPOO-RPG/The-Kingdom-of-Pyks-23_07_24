
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private Jogador1 jogador1;

    public boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed, inventoryPressed, healPressed;
	private String direcao = "any";


    public KeyHandler(Jogador1 jogador1) {
        this.jogador1 = jogador1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            direcao = "cima";
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            direcao = "baixo";
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            direcao = "esquerda";
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            direcao = "direita";
        }

        if (code == KeyEvent.VK_I) {
            inventoryPressed = true;
        }

        if (code == KeyEvent.VK_P) {
            pausePressed = true;
            
        }
       if (code == KeyEvent.VK_H) {
            healPressed = true;
        }
    

        jogador1.setSpriteDirecao();
		jogador1.getSpriteDirecao(direcao);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_P) {
            pausePressed = false;
        }
        if (code == KeyEvent.VK_I) {
            inventoryPressed = false;
        }
    }

}