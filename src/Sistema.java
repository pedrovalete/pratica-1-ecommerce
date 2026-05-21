import dominio.*;
import infra.*;
import servico.*;
import aplicacao.ProcessadorPedido;

public class Sistema {

    public static void main(String[] args) {

        Cliente cliente = new Cliente("Maria", "maria@email.com", "SC");
        Pedido p = new Pedido(cliente);
        p.adicionarItem(new ItemPedido("Notebook", 3000, 1));
        p.adicionarItem(new ItemPedido("Mouse", 100, 2));

        Logger logger = new Logger();
        RelatorioService relatorio = new RelatorioService();

        ProcessadorPedido processador = new ProcessadorPedido(
                new CalculadoraDesconto(),
                new CalculadoraFrete(),
                new EstoqueService(),
                new ProcessadorPagamentoCartao(),
                new NotificadorEmail(),
                relatorio,
                new BancoDeDados(),
                logger
        );

        processador.finalizar(p);
        logger.registrar("Sistema finalizado");
        relatorio.gerarDetalhado(p);

        System.out.println("Frete: " + p.getFrete());
        System.out.println("Status: " + p.getStatus());
    }
}