package servicos;

import java.util.List;

import auxiliar.Dados;
import banco.Conta;

public class ContaServico {
	
	private static List<Conta> contas = Dados.obterContas();
	
	public static Conta buscaContaPeloCpf(String cpf) {

		for (Conta conta : contas) {
			if (cpf.equals(conta.getCpf())) {
				return conta;
			}
		}
		
		return null;
	}
	
	public static Conta buscaContaPelaAgenciaEConta(String agencia, String conta) {
		
		for (Conta contaBanco : contas) {
			if (agencia.equals(contaBanco.getAgencia()) && conta.equals(contaBanco.getConta())) {
				return contaBanco;
			}
		}
		
		return null;
	}
}
