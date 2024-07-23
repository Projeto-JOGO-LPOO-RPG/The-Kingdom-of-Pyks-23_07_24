import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Combate {
    private Jogador1 jogador1;
    private List<Inimigo> inimigos;
    private Inimigo inimigoAtual;

    public Combate(Jogador1 jogador1, List<Inimigo> inimigos) {
        this.jogador1 = jogador1;
        this.inimigos = inimigos;
    }

    public void verificarCombate() {
        for (Iterator<Inimigo> iterator = inimigos.iterator(); iterator.hasNext();) {
            Inimigo inimigo = iterator.next();
            if (jogador1.isColidindoCom(inimigo)) {
                inimigoAtual = inimigo;
                combater(inimigoAtual);

                if (!jogador1.isVivoAinda()) {
                    System.out.println("Game Over! Você foi derrotado.");
                    return; 
                }

                if (!inimigo.isVivoAinda()) {
                    System.out.println("Inimigo derrotado!");
                    iterator.remove(); 
                    inimigoAtual = null;
                }
                break;
            }
        }
    }

    public void combater(Inimigo inimigo) {
        System.out.println("Você encontrou um inimigo, é um " + inimigo.getNomeEntidade() + ", terá de enfrentá-lo!");

        while (jogador1.isVivoAinda() && inimigo.isVivoAinda()) {
            System.out.println("O que você fará? 1.Atacar 2.Inventário 3.Fugir");
            int acao = jogador1.escolherAcao();
            int danoDeferidoJog = 0;
            int danoRecebidoIn = 0;
            int danoDeferidoIn = 0;
            int danoRecebidoJog = 0;

            switch (acao) {
                case 1:
                    System.out.println("1. Luta    2. Magia");
                    acao = jogador1.escolherAcao();
                    if (acao == 1) { 
                        danoDeferidoJog = jogador1.atacarFisico();
                        danoRecebidoIn = inimigo.defender(danoDeferidoJog);
                        inimigo.setPontosVida(inimigo.getPontosVida() - danoRecebidoIn);

                        
                        Random random = new Random();
                        int inimigoAtaqueIndex = random.nextInt(2) + 1;
                        switch (inimigoAtaqueIndex) {
                            case 1:
                                danoDeferidoIn = inimigo.atacarFisico();
                                break;
                            case 2:
                                danoDeferidoIn = inimigo.atacarMagia();
                                break;
                        }

                        danoRecebidoJog = jogador1.defender(danoDeferidoIn);
                        jogador1.setPontosVida(jogador1.getPontosVida() - danoRecebidoJog);
                    } else if (acao == 2) { 
                        danoDeferidoJog = jogador1.atacarMagia();
                        danoRecebidoIn = inimigo.defender(danoDeferidoJog);
                        inimigo.setPontosVida(inimigo.getPontosVida() - danoRecebidoIn);

                        danoDeferidoIn = inimigo.atacarMagia();
                        danoRecebidoJog = jogador1.defender(danoDeferidoIn);
                        jogador1.setPontosVida(jogador1.getPontosVida() - danoRecebidoJog);
                    }
                    break;
                case 2:
                    jogador1.acessarInventario();
                    break;
                case 3:
                    System.out.println("Você fugiu da batalha!");
                    reposicionarInimigo(inimigo);
                    return; 
            }

            clearScreen();

            System.out.println(jogador1.getNomeEntidade() + " " + jogador1.getNomeJogador());
            System.out.println("Você Atacou com " + danoDeferidoJog + " de dano");
            System.out.println(inimigo.getNomeEntidade() + " Atacou com " + danoDeferidoIn + " de dano");

            System.out.println("Lhe resta: " + jogador1.getPontosVida() + " de vida");
            System.out.println("Inimigo com : " + inimigo.getPontosVida() + " de vida");

            if (jogador1.getPontosVida() <= 0) {
                System.out.println("Você foi derrotado!");
                return; 
            }
            if (inimigo.getPontosVida() <= 0) {
                System.out.println("Inimigo derrotado!");
                inimigo.setVivo(false);
                int xpGanho = inimigo.gerarXP();
                jogador1.subirNivel(xpGanho);
                inimigoAtual = null;
                return; 
            }
        }
    }

    private void reposicionarInimigo(Inimigo inimigo) {
        Random random = new Random();
        inimigo.setCoordX(random.nextInt(100)); 
        inimigo.setCoodY(random.nextInt(100)); 
        System.out.println(inimigo.getNomeEntidade() + " foi reposicionado!");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Jogador1 getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador1 jogador1) {
        this.jogador1 = jogador1;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }
}