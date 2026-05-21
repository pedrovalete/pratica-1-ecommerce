import dominio.*;
import infra.*;
import servico.*;

public class Sistema {

    public static void main(String[] args) {

        Cliente cliente = new Cliente("Maria", "maria@email.com", "SC");
        Pedido p = new Pedido(cliente);
        p.adicionarItem(new ItemPedido("Notebook", 3000, 1));
        p.adicionarItem(new ItemPedido("Mouse", 100, 2));

        p.finalizar();

        new Logger().registrar("Sistema finalizado");

        RelatorioService r = new RelatorioService();
        r.gerarDetalhado(p);

        System.out.println("Frete: " + p.getFrete());
        System.out.println("Status: " + p.getStatus());
    }
}