import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Inimigo extends Entidade {
    private int XP;
    private double coeficienteXP;
    private int dificuldadeInimigo;
    private String nomeInimigo;
    private String direcaoAtual;

    private ImageIcon spriteInimigoCima1;
    private ImageIcon spriteInimigoCima2;
    private ImageIcon spriteInimigoBaixo1;
    private ImageIcon spriteInimigoBaixo2;
    private ImageIcon spriteInimigoDir1;
    private ImageIcon spriteInimigoDir2;
    private ImageIcon spriteInimigoEsq1;
    private ImageIcon spriteInimigoEsq2;

    protected int timerMovimento;
    protected static final int INTERVALO_MOVIMENTO = 10; 

    private Random random;
    
    public Inimigo(int dificuldadeInimigo, String nomeEntidade, int pontosVida, int pontosDefesa, int pontosAtaque, int pontosMagia, 
                   int coordX, int coordY, int velocidade, int numSprite, boolean isVivo) {
        super(nomeEntidade, pontosVida, pontosDefesa, pontosAtaque, pontosMagia, coordX, coordY, velocidade, numSprite, isVivo);
        this.dificuldadeInimigo = dificuldadeInimigo;
        this.random = new Random();
        this.direcaoAtual = "nenhuma"; 
        setSpriteDirecao(); 
    }

    public abstract void setSpriteDirecao();

    protected void loadSpriteDirecao(String pathCima1, String pathCima2, String pathBaixo1, String pathBaixo2, 
                                     String pathDir1, String pathDir2, String pathEsq1, String pathEsq2) {
        try {
            spriteInimigoCima1 = loadSprite(pathCima1);
            spriteInimigoCima2 = loadSprite(pathCima2);
            spriteInimigoBaixo1 = loadSprite(pathBaixo1);
            spriteInimigoBaixo2 = loadSprite(pathBaixo2);
            spriteInimigoDir1 = loadSprite(pathDir1);
            spriteInimigoDir2 = loadSprite(pathDir2);
            spriteInimigoEsq1 = loadSprite(pathEsq1);
            spriteInimigoEsq2 = loadSprite(pathEsq2);
        } catch (Exception e) {
            System.err.println("Erro ao carregar sprites do inimigo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private ImageIcon loadSprite(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("Arquivo não encontrado: " + path);
        }
        return new ImageIcon(path);
    }

    public Image getSpriteDirecao(String direcao) {
        switch (direcao) {
            case "cima":
                return spriteInimigoCima1.getImage();
            case "baixo":
                return spriteInimigoBaixo1.getImage();
            case "esquerda":
                return spriteInimigoEsq1.getImage();
            case "direita":
                return spriteInimigoDir1.getImage();
            default:
                return spriteInimigoBaixo1.getImage();
        }
    }

    public String obterNomeInimigo(String nomeEntidade) {
        this.nomeInimigo = nomeEntidade;
        return nomeInimigo;
    }

    public static Inimigo escolherInimigo(String nomeInimigo, int dificuldadeInimigo) {
        Random random = new Random();
        int inimigoIndex = random.nextInt(3) + 1;

        switch (inimigoIndex) {
            case 1:
                return new Goomba("Goomba", dificuldadeInimigo);
            case 2:
                return new Goblin("Goblin", dificuldadeInimigo);
            case 3:
                return new Dickie("Dickie", dificuldadeInimigo);

        }
        return new FinalBoss(nomeInimigo, dificuldadeInimigo);
    }

    public static Inimigo gerarChefe(String nomeInimigo, int dificuldadeInimigo){
    return new FinalBoss(nomeInimigo, dificuldadeInimigo);}



    public int gerarXP() {
        switch (dificuldadeInimigo) {
            case 1:
                coeficienteXP = 0.50;
                break;
            case 2:
                coeficienteXP = 0.70;
                break;
            case 3:
                coeficienteXP = 1.00;
                break;
            default:
                coeficienteXP = 0.50; 
                break;
        }

        XP = (int) (coeficienteXP * 300);
        return XP;
    }

    public String movimentarInimigo() {
        int direcao = random.nextInt(5); 
        float randomFloat = random.nextFloat();
        timerMovimento++;
        if(randomFloat > 0.8){
            if (timerMovimento >= INTERVALO_MOVIMENTO) {
                switch (direcao) {
                    case 0: 
                        setDirecaoAtual("cima");
                        setCoodY(getCoordY() - getVelocidade());
                        break;
                    case 1: 
                        setDirecaoAtual("baixo");
                        setCoodY(getCoordY() + getVelocidade());
                        break;
                    case 2: 
                        setDirecaoAtual("esquerda");
                        setCoordX(getCoordX() - getVelocidade());
                        break;
                    case 3: 
                        setDirecaoAtual("direita");
                        setCoordX(getCoordX() + getVelocidade());
                        break;
                    case 4:
                        setDirecaoAtual("baixo");
                        setCoordX(getCoordX());
                        setCoodY(getCoordY());
                        break;
                }
                timerMovimento = 0;
            }
        }
        return direcaoAtual;
        
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public double getCoeficienteXP() {
        return coeficienteXP;
    }

    public void setCoeficienteXP(double coeficienteXP) {
        this.coeficienteXP = coeficienteXP;
    }

    public int getDificuldadeInimigo() {
        return dificuldadeInimigo;
    }

    public void setDificuldadeInimigo(int dificuldadeInimigo) {
        this.dificuldadeInimigo = dificuldadeInimigo;
    }

    public String getNomeInimigo() {
        return nomeInimigo;
    }

    public void setNomeInimigo(String nomeInimigo) {
        this.nomeInimigo = nomeInimigo;
    }

    public String getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setDirecaoAtual(String direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }


    public Image getSpriteAtual() {
        return getSpriteDirecao(direcaoAtual);
    }

    public ImageIcon getSpriteInimigoCima1() {
        return spriteInimigoCima1;
    }

    public void setSpriteInimigoCima1(ImageIcon spriteInimigoCima1) {
        this.spriteInimigoCima1 = spriteInimigoCima1;
    }

    public ImageIcon getSpriteInimigoCima2() {
        return spriteInimigoCima2;
    }

    public void setSpriteInimigoCima2(ImageIcon spriteInimigoCima2) {
        this.spriteInimigoCima2 = spriteInimigoCima2;
    }

    public ImageIcon getSpriteInimigoBaixo1() {
        return spriteInimigoBaixo1;
    }

    public void setSpriteInimigoBaixo1(ImageIcon spriteInimigoBaixo1) {
        this.spriteInimigoBaixo1 = spriteInimigoBaixo1;
    }

    public ImageIcon getSpriteInimigoBaixo2() {
        return spriteInimigoBaixo2;
    }

    public void setSpriteInimigoBaixo2(ImageIcon spriteInimigoBaixo2) {
        this.spriteInimigoBaixo2 = spriteInimigoBaixo2;
    }

    public ImageIcon getSpriteInimigoDir1() {
        return spriteInimigoDir1;
    }

    public void setSpriteInimigoDir1(ImageIcon spriteInimigoDir1) {
        this.spriteInimigoDir1 = spriteInimigoDir1;
    }

    public ImageIcon getSpriteInimigoDir2() {
        return spriteInimigoDir2;
    }

    public void setSpriteInimigoDir2(ImageIcon spriteInimigoDir2) {
        this.spriteInimigoDir2 = spriteInimigoDir2;
    }

    public ImageIcon getSpriteInimigoEsq1() {
        return spriteInimigoEsq1;
    }

    public void setSpriteInimigoEsq1(ImageIcon spriteInimigoEsq1) {
        this.spriteInimigoEsq1 = spriteInimigoEsq1;
    }

    public ImageIcon getSpriteInimigoEsq2() {
        return spriteInimigoEsq2;
    }

    public void setSpriteInimigoEsq2(ImageIcon spriteInimigoEsq2) {
        this.spriteInimigoEsq2 = spriteInimigoEsq2;
    }
}