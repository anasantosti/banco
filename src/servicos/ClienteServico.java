package servicos;

import java.util.List;

import auxiliar.Dados;
import banco.Cliente;

public class ClienteServico {

	private static List<Cliente> clientes = Dados.obterCliente();

	public static Cliente buscaClientePeloCpf(String cpf) {
		for (Cliente cliente : clientes) {
			if (cpf.equals(cliente.getCpf())) {
				return cliente;
			}	
		}
		
		return null;
	}
}
