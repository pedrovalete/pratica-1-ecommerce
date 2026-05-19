package servico;

import dominio.Pedido;

public class CalculadoraFrete {

    public double calcular(Pedido pedido, double subtotal) {
        if (pedido.getCliente().getEndereco().contains("SC")) {
            return subtotal * 0.05;
        }
        return subtotal * 0.15;
    }
}