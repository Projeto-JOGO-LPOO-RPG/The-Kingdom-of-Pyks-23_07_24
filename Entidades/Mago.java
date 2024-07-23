public class Mago extends Jogador1 {
    public Mago(String nomeJogador, int nivel, int pontosNivel) {
        super(nomeJogador, nivel, pontosNivel, "Mago", 8, 7, 
        5, 12, 150, 150, 5, 2, true);
    }

    public void atacarEspecial() {
        this.setPontosAtaque(2 * getPontosAtaque());
    }

    public void setSpriteDirecao() {
        loadSpriteDirecao(
            "Sprites/Mago/MagoCima1.png",
            "Sprites/Mago/MagoCima2.png",
            "Sprites/Mago/MagoBaixo1.png",
            "Sprites/Mago/MagoBaixo2.png",
            "Sprites/Mago/MagoDir1.png",
            "Sprites/Mago/MagoDir2.png",
            "Sprites/Mago/MagoEsq1.png",
            "Sprites/Mago/MagoEsq2.png"
        );
    }
}
