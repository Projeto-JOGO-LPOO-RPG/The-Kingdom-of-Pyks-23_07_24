public class Dickie extends Inimigo {

    public Dickie(String nomeEntidade, int dificuldadeInimigo) {
        super(dificuldadeInimigo, nomeEntidade, 20, 10, 6, 0,
         100, 100, 9, 1, true);
    }

    @Override
    public void setSpriteDirecao() {
        loadSpriteDirecao("Sprites/Dickie/DickieCima1.png", "Sprites/Dickie/DickieCima2.png",
                          "Sprites/Dickie/DickieBaixo1.png", "Sprites/Dickie/DickieBaixo2.png",
                          "Sprites/Dickie/DickieDir1.png", "Sprites/Dickie/DickieDir2.png",
                          "Sprites/Dickie/DickieEsq1.png", "Sprites/Dickie/DickieEsq2.png");
    }
}
