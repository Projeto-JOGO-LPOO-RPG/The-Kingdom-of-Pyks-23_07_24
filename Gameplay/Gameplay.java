public class Gameplay {
    private Jogador1 jogador1;
    private Interacao interacao;
    private Combate combate;

    public Gameplay() {
        interacao = new Interacao();
        inicializarJogador();


        combate = new Combate(jogador1, interacao.getInimigos());

        jogador1.verificarColisao(interacao.getInimigos(), combate);
    }

    public void inicializarJogador() {
        jogador1 = Jogador1.escolherPersonagem("Nome do Jogador", 1, 0);
        jogador1.obterNome();
    }

    public void atualizarInimigosMov() {

        for (Inimigo inimigo : interacao.getInimigos()) {
            if (inimigo.isVivo()) {
                inimigo.movimentarInimigo(); 
            }
        }

        jogador1.verificarColisao(interacao.getInimigos(), combate);
        combate.verificarCombate();
    }

    public Jogador1 getJogador1() {
        return jogador1;
    }
}
