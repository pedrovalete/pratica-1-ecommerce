package servico;

import dominio.ItemPedido;
import dominio.Pedido;

public class RelatorioService {

    public void gerarDetalhado(Pedido p) {
        System.out.println("Cliente: " + p.getCliente().getNome());
        System.out.println("Total com frete: " + (p.getTotal() + p.getFrete()));

        if (p.getTotal() > 1000) {
            System.out.println("Cliente VIP");
        }
    }

    public void gerarResumo(Pedido p) {
        System.out.println("Relatorio do pedido:");
        for (ItemPedido item : p.getItens()) {
            System.out.println(item.getNomeProduto());
        }
        System.out.println("Total: " + p.getTotal());
    }
}