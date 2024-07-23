
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } else {
            System.out.println("Imagem não encontrada: " + imageFile.getAbsolutePath());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}