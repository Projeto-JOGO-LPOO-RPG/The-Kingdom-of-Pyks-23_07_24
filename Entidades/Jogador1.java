import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;

public abstract class Jogador1 extends Entidade {
    private String nomeJogador;
    private int nivel;
    private int pontosNivel;
    private int acao;
    private String direcaoAtual = "baixo";
    private int contadorSprite = 0;

    private ImageIcon spriteJogadorCima1;
    private ImageIcon spriteJogadorCima2;
    private ImageIcon spriteJogadorBaixo1;
    private ImageIcon spriteJogadorBaixo2;
    private ImageIcon spriteJogadorDir1;
    private ImageIcon spriteJogadorDir2;
    private ImageIcon spriteJogadorEsq1;
    private ImageIcon spriteJogadorEsq2;

    private Scanner scanner = new Scanner(System.in);

    public Jogador1(String nomeJogador, int nivel, int pontosNivel, String nomeEntidade, int pontosVida, int pontosDefesa, int pontosAtaque, int pontosMagia,
                   int coordX, int coordY, int velocidade, int numSprite, boolean isVivo) {
        super(nomeEntidade, pontosVida, pontosDefesa, pontosAtaque, pontosMagia, coordX, coordY, velocidade, numSprite, isVivo);
        this.nomeJogador = nomeJogador;
        this.nivel = nivel;
        this.pontosNivel = pontosNivel;
    }
    
    public void modificarVida(int pontos) {
        int vidaAtual = getPontosVida();
        setPontosVida(vidaAtual + pontos);
    }

    public String obterNome() {
        System.out.println("Insira o nome do Jogador: ");
        this.nomeJogador = scanner.nextLine();
        return this.nomeJogador;
    }

    public static Jogador1 escolherPersonagem(String nomeJogador, int nivel, int pontosNivel) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha seu personagem: 1. Cavaleiro 2. Bárbaro 3. Mago");
        int personagemIndex = scanner.nextInt();
        switch (personagemIndex) {
            case 1:
                return new Cavaleiro(nomeJogador, nivel, pontosNivel);   
            case 2:
                return new Barbaro(nomeJogador, nivel, pontosNivel);
            case 3:
                return new Mago(nomeJogador, nivel, pontosNivel);
                
            default:
            
                System.out.println("Escolha inválida");
                scanner.close();
                return null;
        }
        
    }

    public int escolherAcao() {
        this.acao = scanner.nextInt();
        return this.acao;
    }

    public void acessarInventario() {
        System.out.println("Você acessou o inventário, mas não tem nada!");
    }

    public void verificarColisao(List<Inimigo> inimigos, Combate combate) {
        for (Inimigo inimigo : inimigos) {
            if (isColidindoCom(inimigo)) {
                combate.combater(inimigo); 
                break; 
            }
        }
    }

    public int modificarVidaJogador(int pontosVida) {
        if (pontosVida < 0) {
            pontosVida = 0;
        }
        setPontosVida(pontosVida);
        return pontosVida;
    }

    public void subirNivel(int xpGanho) {
        pontosNivel += xpGanho;

        if (pontosNivel < 200) {
            nivel = 1;
        } else if (pontosNivel >= 200 && pontosNivel < 400) {
            nivel = 2;
        } else if (pontosNivel >= 400 && pontosNivel < 700) {
            nivel = 3;
        } else if (pontosNivel >= 700 && pontosNivel < 1200) {
            nivel = 4;
        }

        setPontosAtaque(getPontosAtaque() + 5);
        setPontosDefesa(getPontosDefesa() + 5);

        System.out.println("Subiu para o nível " + nivel);
        System.out.println("Ataque: " + getPontosAtaque());
        System.out.println("Defesa: " + getPontosDefesa());
    }

    public abstract void setSpriteDirecao();

    protected void loadSpriteDirecao(String pathCima1, String pathCima2, String pathBaixo1, String pathBaixo2, 
                                     String pathDir1, String pathDir2, String pathEsq1, String pathEsq2) {
        try {
            spriteJogadorCima1 = loadSprite(pathCima1);
            spriteJogadorCima2 = loadSprite(pathCima2);
            spriteJogadorBaixo1 = loadSprite(pathBaixo1);
            spriteJogadorBaixo2 = loadSprite(pathBaixo2);
            spriteJogadorDir1 = loadSprite(pathDir1);
            spriteJogadorDir2 = loadSprite(pathDir2);
            spriteJogadorEsq1 = loadSprite(pathEsq1);
            spriteJogadorEsq2 = loadSprite(pathEsq2);
        } catch (Exception e) {
            System.err.println("Erro ao carregar sprites do jogador: " + e.getMessage());
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
                return spriteJogadorCima1.getImage();
            case "baixo":
                return spriteJogadorBaixo1.getImage();
            case "esquerda":
                return spriteJogadorEsq1.getImage();
            case "direita":
                return spriteJogadorDir1.getImage();
            default:
                return spriteJogadorBaixo1.getImage();
        }
    }


    public void getInventario(){

    }

    public void recuperarVida(int pontosCura){
        setPontosVida(getPontosVida() + pontosCura);
        if(getPontosVida() > getPontosVida());

    }
    public void causarDano(){
        
    }


















    

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPontosNivel() {
        return pontosNivel;
    }

    public void setPontosNivel(int pontosNivel) {
        this.pontosNivel = pontosNivel;
    }

    public int getContadorSprite() {
        return contadorSprite;
    }

    public void setContadorSprite(int contadorSprite) {
        this.contadorSprite = contadorSprite;
    }

    public String getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setDirecaoAtual(String direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }

    public ImageIcon getSpriteJogadorCima1() {
        return spriteJogadorCima1;
    }

    public void setSpriteJogadorCima1(ImageIcon spriteJogadorCima1) {
        this.spriteJogadorCima1 = spriteJogadorCima1;
    }

    public ImageIcon getSpriteJogadorCima2() {
        return spriteJogadorCima2;
    }

    public void setSpriteJogadorCima2(ImageIcon spriteJogadorCima2) {
        this.spriteJogadorCima2 = spriteJogadorCima2;
    }

    public ImageIcon getSpriteJogadorBaixo1() {
        return spriteJogadorBaixo1;
    }

    public void setSpriteJogadorBaixo1(ImageIcon spriteJogadorBaixo1) {
        this.spriteJogadorBaixo1 = spriteJogadorBaixo1;
    }

    public ImageIcon getSpriteJogadorBaixo2() {
        return spriteJogadorBaixo2;
    }

    public void setSpriteJogadorBaixo2(ImageIcon spriteJogadorBaixo2) {
        this.spriteJogadorBaixo2 = spriteJogadorBaixo2;
    }

    public ImageIcon getSpriteJogadorDir1() {
        return spriteJogadorDir1;
    }

    public void setSpriteJogadorDir1(ImageIcon spriteJogadorDir1) {
        this.spriteJogadorDir1 = spriteJogadorDir1;
    }

    public ImageIcon getSpriteJogadorDir2() {
        return spriteJogadorDir2;
    }

    public void setSpriteJogadorDir2(ImageIcon spriteJogadorDir2) {
        this.spriteJogadorDir2 = spriteJogadorDir2;
    }

    public ImageIcon getSpriteJogadorEsq1() {
        return spriteJogadorEsq1;
    }

    public void setSpriteJogadorEsq1(ImageIcon spriteJogadorEsq1) {
        this.spriteJogadorEsq1 = spriteJogadorEsq1;
    }

    public ImageIcon getSpriteJogadorEsq2() {
        return spriteJogadorEsq2;
    }

    public void setSpriteJogadorEsq2(ImageIcon spriteJogadorEsq2) {
        this.spriteJogadorEsq2 = spriteJogadorEsq2;
    }
}
