package servico;

import dominio.Pedido;

public interface IProcessadorPagamento {
    void processar(Pedido pedido);
}
