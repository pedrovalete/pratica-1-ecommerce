package servico;

import dominio.ItemPedido;
import java.util.List;

public class EstoqueService {

    public void atualizar(List<ItemPedido> itens) {
        for (ItemPedido item : itens) {
            System.out.println("Atualizando estoque de: " + item.getNomeProduto());
        }
    }
}
