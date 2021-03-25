package auxiliar;

import java.util.ArrayList;
import java.util.List;

import banco.Cliente;
import banco.Conta;

public class Dados {
	
	public static List<Conta> obterContas() {
		List<Conta> contas = new ArrayList<>();
		
		contas.add(new Conta("0046", "Santander", "1234-5", 260.00, "888.888.888-88"));
		contas.add(new Conta("0001", "Nubank", "1567-0", 300.00, "555.555.555-55"));
		
		return contas;
	}
	
	public static List<Cliente> obterCliente(){
		List<Cliente> clientes = new ArrayList<>();
		
		clientes.add(new Cliente("Carlos", "888.888.888-88", "14403-165", 53, "Jardim"));
		clientes.add(new Cliente("Ana Paula", "555.555.555-55", "17893-178", 56, "Europa"));
		
		return clientes;
	}

}
