public class Pocao extends Item {
    public Pocao(String nome, String descricao, int quantidade, int pontosVidaRecupera) {
        super("Poção", "Uma misteriosa substancia que lhe dá vitalidade em batalha", 1, 4, 
        0, 0, 0);
    }

    @Override
    public void usar(Jogador1 jogador) {
        jogador.recuperarVida(getPontosVidaRecupera());
        setQuantidade(getQuantidade() - 1);
        if (getQuantidade() <= 0) {
            System.out.println(getNome() + " não tem!");
        }
    }
}
