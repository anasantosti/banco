package auxiliar;

import java.util.ArrayList;
import java.util.List;

import banco.Cliente;
import banco.Conta;

public class Dados {
	
	public static List<Conta> obterContas() {
		List<Conta> contas = new ArrayList<>();
		
		contas.add(new Conta("0046", "Santander", "1234-5", 260.00, "456.789.567-90"));
		contas.add(new Conta("0001", "Nubank", "1567-0", 300.00, "465.876.321-80"));
		
		return contas;
	}
	
	public static List<Cliente> obterCliente(){
		List<Cliente> clientes = new ArrayList<>();
		
		clientes.add(new Cliente("Carlos", "456.789.567-90", "14403-165", 53, "Jardim"));
		clientes.add(new Cliente("Ana Paula", "465.876.321-80", "17893-178", 56, "Europa"));
		
		return clientes;
	}

}
