package dominio;

import infra.BancoDeDados;
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

    private RelatorioService relatorioService = new RelatorioService();

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

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public void calcularTotal() {
        total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
    }

        public void gerarRelatorio () {
            System.out.println("Relatorio do pedido:");
            for (ItemPedido item : itens) {
                System.out.println(item.getNomeProduto());
            }
            System.out.println("Total: " + total);
        }

        public void salvarNoBanco () {
            BancoDeDados().salvar(this);
            newLogger().registrar("Pedido salvo" + cliente.getNome());
        }

        public void finalizar () {
            calcularTotal();
            total = new CalculadoraDesconto().aplicar(total);
            frete = new CalculadoraFrete().calcular(this, total);
            new EstoqueService().atualizar(itens);
            new ProcessadorPagamentoCartao().processar(this);
            new NotificadorEmail().notificar(cliente);
            gerarRelatorio();
            salvarNoBanco();
            status = "FINALIZADO";
        }

    public Cliente getCliente(){
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }
    }