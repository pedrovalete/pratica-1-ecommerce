package servico;

import dominio.Pedido;

public class RelatorioService {

    public void gerar(Pedido p) {
        System.out.println("Cliente: " + p.getCliente().getNome());
        System.out.println("Total com frete: " + (p.getTotal() + p.getFrete()));

        if (p.getTotal() > 1000) {
            System.out.println("Cliente VIP");
        }
    }
}