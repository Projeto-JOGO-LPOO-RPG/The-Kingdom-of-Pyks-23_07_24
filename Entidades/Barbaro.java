public class Barbaro extends Jogador1 {
    public Barbaro(String nomeJogador, int nivel, int pontosNivel) {
        super(nomeJogador, nivel, pontosNivel, "Bárbaro", 12, 8, 11,
         5, 150, 150, 4, 1, true);
    }

    public void atacarEspecial() {
        this.setPontosAtaque(2 * getPontosAtaque());
    }

    public void setSpriteDirecao() {
        loadSpriteDirecao(
            "Sprites/Barbaro/BarbaroCima1.png",
            "Sprites/Barbaro/BarbaroCima2.png",
            "Sprites/Barbaro/BarbaroBaixo1.png",
            "Sprites/Barbaro/BarbaroBaixo2.png",
            "Sprites/Barbaro/BarbaroDir1.png",
            "Sprites/Barbaro/BarbaroDir2.png",
            "Sprites/Barbaro/BarbaroEsq1.png",
            "Sprites/Barbaro/BarbaroEsq2.png"
        );
    }
}