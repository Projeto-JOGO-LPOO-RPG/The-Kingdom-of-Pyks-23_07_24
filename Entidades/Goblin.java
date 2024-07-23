public class Goblin extends Inimigo {

    public Goblin(String nomeEntidade, int dificuldadeInimigo) {
        super(dificuldadeInimigo, nomeEntidade, 7, 11, 5, 0,
         150, 200, 15, 1, true);
    }

    @Override
    public void setSpriteDirecao() {
        loadSpriteDirecao("Sprites/Goblin/GoblinCima1.png", "Sprites/Goblin/GoblinCima2.png",
                          "Sprites/Goblin/GoblinBaixo1.png", "Sprites/Goblin/GoblinBaixo2.png",
                          "Sprites/Goblin/GoblinDir1.png", "Sprites/Goblin/GoblinDir2.png",
                          "Sprites/Goblin/GoblinEsq1.png", "Sprites/Goblin/GoblinEsq2.png");
    }
}
