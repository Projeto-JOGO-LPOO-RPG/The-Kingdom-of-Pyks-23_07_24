import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {

        Gameplay gameplay = new Gameplay();    

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The Kingdom Of Pyks");

        ImageIcon icon = new ImageIcon("Source/Icon The Kingdom of Pyks.png");
        java.awt.Image image = icon.getImage();
        window.setIconImage(image);

        Logo logoAnimation = new Logo(new Dimension(800, 600));
        window.add(logoAnimation);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        logoAnimation.startAnimation();

        new Thread(() -> {
            try {
                Thread.sleep(8000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            window.getContentPane().removeAll(); 
            GamePanel gamePanel = new GamePanel(gameplay);
            window.add(gamePanel);
            window.revalidate();
            window.repaint();

            
            gamePanel.requestFocusInWindow();
            gamePanel.startGameThread();
            
        }).start();
    }
}