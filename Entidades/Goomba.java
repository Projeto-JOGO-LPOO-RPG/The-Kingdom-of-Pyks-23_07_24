public class Goomba extends Inimigo {

    public Goomba(String nomeEntidade, int dificuldadeInimigo) {
        super(dificuldadeInimigo, nomeEntidade, 10, 5, 3, 0,
         100, 100, 20, 1, true);
    }

    @Override
    public void setSpriteDirecao() {
        loadSpriteDirecao("Sprites/Goomba/GoombaCima1.png", "Sprites/Goomba/GoombaCima2.png",
                          "Sprites/Goomba/GoombaBaixo1.png", "Sprites/Goomba/GoombaBaixo2.png",
                          "Sprites/Goomba/GoombaDir1.png", "Sprites/Goomba/GoombaDir2.png",
                          "Sprites/Goomba/GoombaEsq1.png", "Sprites/Goomba/GoombaEsq2.png");
    }
}
