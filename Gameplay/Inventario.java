import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item novoItem) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(novoItem.getNome())) {
                item.setQuantidade(item.getQuantidade() + novoItem.getQuantidade());
                return;
            }
        }
        itens.add(novoItem);
    }

    public void removerItem(String nome, int quantidade) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                int novaQuantidade = item.getQuantidade() - quantidade;
                if (novaQuantidade > 0) {
                    item.setQuantidade(novaQuantidade);
                } else {
                    itens.remove(item);
                }
                return;
            }
        }
    }

    public Item obterItem(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> listarItens() {
        return new ArrayList<>(itens);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Inventário:\n");
        for (Item item : itens) {
            builder.append(item.toString()).append("\n");
        }
        return builder.toString();
    }
}
