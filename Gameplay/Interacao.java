import java.util.ArrayList;
import java.util.List;

public class Interacao {
    private List<Inimigo> inimigos;

    public Interacao() {
        
        inimigos = new ArrayList<>();
    }

    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.add(inimigo);
    }

    public void removerInimigo(Inimigo inimigo) {
        inimigos.remove(inimigo);
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }
 
}
