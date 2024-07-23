

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Logo extends JPanel {
    private Image logoGaEdu;
    private Image logoIconGaEdu;
    private int yPosicao;
    private int yPosicaoIcon;
    private boolean runningIcon;
    private Clip soundLogo;
    private boolean terminouLogo;
    private int counter;
    private JLabel presentsLabel; 

    public Logo(Dimension size) {
        this.setPreferredSize(size);
        this.setBackground(Color.WHITE);
        this.setLayout(null); 

        try {
            logoGaEdu = ImageIO.read(getClass().getResource("Source/GaEduLogo.png"));
            logoIconGaEdu = ImageIO.read(getClass().getResource("Source/GaEduIconLogo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        yPosicao = -logoGaEdu.getHeight(null); 
        yPosicaoIcon = -logoIconGaEdu.getHeight(null); 
        runningIcon = false;
        terminouLogo = false;

        presentsLabel = new JLabel("PRESENTS");
        presentsLabel.setFont(new Font("Serif", Font.BOLD, 32));
        presentsLabel.setForeground(Color.BLACK);
        presentsLabel.setBounds((size.width - 150) / 2, size.height / 2 + 200, 200, 50);
        presentsLabel.setVisible(false); 
        this.add(presentsLabel);

        startAnimation();
    }

    public void startAnimation() {
        Timer timer = new Timer(35, (e) -> {
            
            yPosicao += 10; 
            if (yPosicao >= (getHeight() - logoGaEdu.getHeight(null)) / 2) {
                yPosicao = (getHeight() - logoGaEdu.getHeight(null)) / 2; 

                Timer delayTimer = new Timer(1000, (evt) -> {
                    runningIcon = true; 
                    ((Timer) evt.getSource()).stop(); 
                });
                delayTimer.setRepeats(false);
                delayTimer.start();
            }

            if (runningIcon) {
                yPosicaoIcon += 7;

                if (yPosicaoIcon >= (getHeight() - logoIconGaEdu.getHeight(null)) / 2 - 30) {
                    yPosicaoIcon = (getHeight() - logoIconGaEdu.getHeight(null)) / 2 - 30; 
                    terminouLogo = true;
                    counter++;
                }
            }

            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (logoGaEdu != null) {
            int xPosicao = (getWidth() - logoGaEdu.getWidth(null)) / 2;
            g2.drawImage(logoGaEdu, xPosicao, yPosicao, this);
        }

        if (runningIcon && logoIconGaEdu != null) {
            int xPosicaoIcon = (getWidth() - logoIconGaEdu.getWidth(null)) / 2;
            g2.drawImage(logoIconGaEdu, xPosicaoIcon, yPosicaoIcon, this);
        }

        if (terminouLogo && counter == 2) {
            playSound("Source/eoPixNadaAinda.wav");
        }

        if (terminouLogo && !presentsLabel.isVisible()) {
            presentsLabel.setVisible(true);
        }

    }

    private void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            soundLogo = AudioSystem.getClip();
            soundLogo.open(audioIn);
            soundLogo.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
