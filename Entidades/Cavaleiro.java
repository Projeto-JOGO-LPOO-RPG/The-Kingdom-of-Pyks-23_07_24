public class Cavaleiro extends Jogador1 {
    
    public Cavaleiro(String nomeJogador, int nivel, int pontosNivel) {
        super(nomeJogador, nivel, pontosNivel, "Cavaleiro", 10, 10, 9,
         7, 150, 150, 3, 1, true);
        setSpriteDirecao();
    }

    public void atacarEspecial() {
        this.setPontosAtaque(2 * getPontosAtaque());
    }
    
    public void setSpriteDirecao() {
        loadSpriteDirecao(
            "Sprites/Cavaleiro/CavaleiroCima1.png",
            "Sprites/Cavaleiro/CavaleiroCima2.png",
            "Sprites/Cavaleiro/CavaleiroBaixo1.png",
            "Sprites/Cavaleiro/CavaleiroBaixo2.png",
            "Sprites/Cavaleiro/CavaleiroDir1.png",
            "Sprites/Cavaleiro/CavaleiroDir2.png",
            "Sprites/Cavaleiro/CavaleiroEsq1.png",
            "Sprites/Cavaleiro/CavaleiroEsq2.png"
        );
    }
}

