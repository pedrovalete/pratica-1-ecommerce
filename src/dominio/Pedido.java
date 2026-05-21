package dominio;

import servico.*;

import java.util.*;

public class Pedido {

    private Cliente cliente;
    private List<ItemPedido> itens;

    private double total;
    private double frete;
    private String status;


    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    //getters
    public double getTotal() {
        return total;
    }

    public double getFrete() {
        return frete;
    }

    public String getStatus() {
        return status;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : itens) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}