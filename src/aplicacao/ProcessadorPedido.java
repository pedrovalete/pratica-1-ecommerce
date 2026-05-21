package aplicacao;

import dominio.Pedido;
import infra.BancoDeDados;
import infra.Logger;
import servico.CalculadoraDesconto;
import servico.CalculadoraFrete;
import servico.EstoqueService;
import servico.INotificador;
import servico.IProcessadorPagamento;
import servico.RelatorioService;

public class ProcessadorPedido {

    private final CalculadoraDesconto calculadoraDesconto;
    private final CalculadoraFrete calculadoraFrete;
    private final EstoqueService estoqueService;
    private final IProcessadorPagamento processadorPagamento;
    private final INotificador notificador;
    private final RelatorioService relatorio;
    private final BancoDeDados banco;
    private final Logger logger;

    public ProcessadorPedido(CalculadoraDesconto calculadoraDesconto, CalculadoraFrete calculadoraFrete,
            EstoqueService estoqueService, IProcessadorPagamento processadorPagamento,
            INotificador notificador, RelatorioService relatorio,
            BancoDeDados banco, Logger logger) {
        this.calculadoraDesconto = calculadoraDesconto;
        this.calculadoraFrete = calculadoraFrete;
        this.estoqueService = estoqueService;
        this.processadorPagamento = processadorPagamento;
        this.notificador = notificador;
        this.relatorio = relatorio;
        this.banco = banco;
        this.logger = logger;
    }

    public void finalizar(Pedido pedido) {
        double subtotal = pedido.calcularSubtotal();
        double totalComDesconto = calculadoraDesconto.aplicar(subtotal);
        pedido.setTotal(totalComDesconto);
        double frete = calculadoraFrete.calcular(pedido, totalComDesconto);
        pedido.setFrete(frete);

        estoqueService.atualizar(pedido.getItens());
        processadorPagamento.processar(pedido);
        notificador.notificar(pedido.getCliente());
        relatorio.gerarResumo(pedido);
        banco.salvar(pedido);
        logger.registrar("Pedido salvo: " + pedido.getCliente().getNome());

        pedido.setStatus("FINALIZADO");
    }
}
