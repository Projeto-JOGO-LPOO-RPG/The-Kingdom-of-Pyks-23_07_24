public class FinalBoss extends Inimigo {

    public FinalBoss(String nomeEntidade, int dificuldadeInimigo) {
        super(3, nomeEntidade, 100, 50, 70, 0,
         70000, 15, 20, 1, true);
    }

    @Override
    public void setSpriteDirecao() {
        loadSpriteDirecao("Sprites/FinalBoss/FinalBossCima1.png", "Sprites/FinalBoss/FinalBossCima2.png",
                          "Sprites/FinalBoss/FinalBossBaixo1.png", "Sprites/FinalBoss/FinalBossBaixo2.png",
                          "Sprites/FinalBoss/FinalBossDir1.png", "Sprites/FinalBoss/FinalBossDir2.png",
                          "Sprites/FinalBoss/FinalBossEsq1.png", "Sprites/FinalBoss/FinalBossEsq2.png");
    }
}