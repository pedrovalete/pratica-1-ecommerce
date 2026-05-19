package dominio;

import infra.BancoDeDados;
import servico.RelatorioService;

import java.util.*;

public class Pedido {

    private List<String> produtos = new ArrayList<>();
    private List<Double> precos = new ArrayList<>();
    private List<Integer> quantidades = new ArrayList<>();

    private String clienteNome;
    private String clienteEmail;
    private String clienteEndereco;

    private double total;
    private double frete;
    private String status;

    private RelatorioService relatorioService = new RelatorioService();

    //getters
    public List<String> getProdutos() {
        return produtos;
    }

    public List<Double> getPrecos() {
        return precos;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public List<Integer> getQuantidades() {
        return quantidades;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public String getClienteEndereco() {
        return clienteEndereco;
    }

    public double getTotal() {
        return total;
    }

    public double getFrete() {
        return frete;
    }

    public String getStatus() {
        return status;
    }

    //setters
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public void setClienteEndereco(String clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    public void adicionarItem(String nome, double preco, int qtd) {
        produtos.add(nome);
        precos.add(preco);
        quantidades.add(qtd);
    }

    public void calcularTotal() {
        total = 0;
        for (int i = 0; i < precos.size(); i++) {
            total += precos.get(i) * quantidades.get(i);
        }
    }

    public void calcularFrete() {
        if (clienteEndereco.contains("SC")) {
            frete = total * 0.05;
        } else {
            frete = total * 0.15;
        }
    }

    public void aplicarDesconto() {
        if (total > 500) {
            total *= 0.85;
        } else if (total > 200) {
            total *= 0.9;
        }
    }

    public void atualizarEstoque() {
        for (String p : produtos) {
            System.out.println("Atualizando estoque de: " + p);
        }
    }

    public void processarPagamento(String tipo) {
        if (tipo.equals("cartao")) {
            System.out.println("Pagamento cartão OK");
        } else if (tipo.equals("boleto")) {
            System.out.println("Boleto gerado");
        } else if (tipo.equals("pix")) {
            System.out.println("PIX enviado");
        }
    }

    public void enviarNotificacao() {
        System.out.println("Email enviado para " + clienteEmail);
    }

    public void gerarRelatorio() {
        System.out.println("Relatorio do pedido:");
        for (String p : produtos) {
            System.out.println(p);
        }
        System.out.println("Total: " + total);
    }

    public void salvarNoBanco() {
        BancoDeDados.salvarPedido(this);
        BancoDeDados.salvarLog("dominio.Pedido salvo: " + clienteNome);
    }

    public void finalizar() {
        calcularTotal();
        aplicarDesconto();
        calcularFrete();
        atualizarEstoque();
        processarPagamento("cartao");
        enviarNotificacao();
        gerarRelatorio();
        salvarNoBanco();
        status = "FINALIZADO";
    }
}