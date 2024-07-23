public abstract class Item {
    private String nome;
    private String descricao;
    private int pontosVidaRecupera;
    private int pontosAtaqueExtra;
    private int pontosDefesaExtra;
    private int pontosMagiaExtra;
    private int quantidade;

    public Item(String nome, String descricao, int quantidade, int pontosVidaRecupera, int pontosAtaqueExtra, int pontosDefesaExtra, int pontosMagiaExtra) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.pontosVidaRecupera = pontosVidaRecupera;
        this.pontosAtaqueExtra = pontosAtaqueExtra;
        this.pontosDefesaExtra = pontosDefesaExtra;
        this.pontosMagiaExtra = pontosMagiaExtra;
    }

    public abstract void usar(Jogador1 jogador);



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public int getPontosVidaRecupera() {
        return pontosVidaRecupera;
    }
    public void setPontosVidaRecupera(int pontosVidaRecupera) {
        this.pontosVidaRecupera = pontosVidaRecupera;
    }



    public int getPontosAtaqueExtra() {
        return pontosAtaqueExtra;
    }
    public void setPontosAtaqueExtra(int pontosAtaqueExtra) {
        this.pontosAtaqueExtra = pontosAtaqueExtra;
    }



    public int getPontosDefesaExtra() {
        return pontosDefesaExtra;
    }
    public void setPontosDefesaExtra(int pontosDefesaExtra) {
        this.pontosDefesaExtra = pontosDefesaExtra;
    }



    public int getPontosMagiaExtra() {
        return pontosMagiaExtra;
    }
    public void setPontosMagiaExtra(int pontosMagiaExtra) {
        this.pontosMagiaExtra = pontosMagiaExtra;
    }



    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;;
    }



    @Override
    public String toString() {
        return nome + " (Quantidade: " + quantidade + ")";
    }
}

    

 
