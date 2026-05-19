package servico;

public class CalculadoraDesconto {

    public double aplicar(double subtotal) {
        if (subtotal > 500) {
            return subtotal * 0.85;
        } else if (subtotal > 200) {
            return subtotal * 0.90;
        }
        return subtotal;
    }
}