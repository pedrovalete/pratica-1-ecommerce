package dominio;

public class ItemPedido {

    private String nomeProduto;
    private double preco;
    private int quantidade;

    public ItemPedido(String nomeProduto, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return preco * quantidade;
    }

    public String getNomeProduto() { return nomeProduto; }
    public double getPreco()       { return preco; }
    public int getQuantidade()     { return quantidade; }
}
