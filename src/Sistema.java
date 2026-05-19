import dominio.Pedido;
import infra.BancoDeDados;
import servico.RelatorioService;
import dominio.Cliente;
import dominio.ItemPedido;

public class Sistema {

    public static void main(String[] args) {

        Cliente cliente = new Cliente("Maria", "maria@email.com", "SC");
        Pedido p = new Pedido(cliente);
        p.adicionarItem(new ItemPedido("Notebook", 3000, 1));
        p.adicionarItem(new ItemPedido("Mouse", 100, 2));

        p.finalizar();

        BancoDeDados.salvarLog("Sistema finalizado");

        RelatorioService r = new RelatorioService();
        r.gerar(p);

        System.out.println("Frete: " + p.getFrete());
        System.out.println("Status: " + p.getStatus());
    }
}