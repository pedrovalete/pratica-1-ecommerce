package servico;

import dominio.Cliente;

public class NotificadorEmail implements INotificador {

    @Override
    public void notificar(Cliente cliente) {
        System.out.println("Email enviado para " + cliente.getEmail());
    }
}
