package dominio;

import infra.BancoDeDados;
import servico.CalculadoraDesconto;
import servico.CalculadoraFrete;
import servico.RelatorioService;

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


        public void atualizarEstoque () {
            for (ItemPedido item : itens) {
                System.out.println("Atualizando estoque de: " + item.getNomeProduto());
            }
        }

        public void processarPagamento (String tipo){
            if (tipo.equals("cartao")) {
                System.out.println("Pagamento cartão OK");
            } else if (tipo.equals("boleto")) {
                System.out.println("Boleto gerado");
            } else if (tipo.equals("pix")) {
                System.out.println("PIX enviado");
            }
        }

        public void enviarNotificacao () {
            System.out.println("Email enviado para " + cliente.getEmail());
        }

        public void gerarRelatorio () {
            System.out.println("Relatorio do pedido:");
            for (ItemPedido item : itens) {
                System.out.println(item.getNomeProduto());
            }
            System.out.println("Total: " + total);
        }

        public void salvarNoBanco () {
            BancoDeDados.salvarPedido(this);
            BancoDeDados.salvarLog("Pedido salvo: " + cliente.getNome());
        }

        public void finalizar () {
            calcularTotal();
            total = new CalculadoraDesconto().aplicar(total);
            frete = new CalculadoraFrete().calcular(this, total);
            atualizarEstoque();
            processarPagamento("cartao");
            enviarNotificacao();
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