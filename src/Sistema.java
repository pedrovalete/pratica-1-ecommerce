import dominio.Pedido;
import infra.BancoDeDados;
import servico.RelatorioService;

public class Sistema {

    public static void main(String[] args) {

        Pedido p = new Pedido();

        p.setClienteNome("Maria");
        p.setClienteEmail("maria@email.com");
        p.setClienteEndereco("SC");

        p.adicionarItem("Notebook", 3000, 1);
        p.adicionarItem("Mouse", 100, 2);

        p.finalizar();

        BancoDeDados.salvarLog("Sistema finalizado");

        RelatorioService r = new RelatorioService();
        r.gerar(p);

        System.out.println("Frete: " + p.getFrete());
        System.out.println("Status: " + p.getStatus());
    }
}