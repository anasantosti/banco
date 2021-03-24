package auxiliar;

import java.util.ArrayList;
import java.util.List;

import banco.Cliente;
import banco.Conta;

public class Dados {
	
	public static List<Conta> obterContas() {
		List<Conta> contas = new ArrayList<>();
		
		contas.add(new Conta("0046", "Santander", "1234-5", 260.00, "439.379.318-80"));
		contas.add(new Conta("0001", "Nubank", "1567-0", 300.00, "437.098.318-57"));
		
		return contas;
	}
	
	public static List<Cliente> obterCliente(){
		List<Cliente> clientes = new ArrayList<>();
		
		clientes.add(new Cliente("Carlos", "439.379.318-80", "16403-135", 53, "Junqueira"));
		clientes.add(new Cliente("Ana Paula", "437.098.318-57", "16403-135", 53, "Junqueira"));
		
		return clientes;
	}

}
