package servico;

import dominio.Pedido;

public class ProcessadorPagamentoCartao implements IProcessadorPagamento {

    @Override
    public void processar(Pedido pedido) {
        System.out.println("Pagamento cartão OK");
    }
}
