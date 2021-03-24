package banco;

import java.util.List;
import java.util.Scanner;

import auxiliar.Dados;

public class CaixaEletronico {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite seu CPF: ");
		String cpf = entrada.nextLine();
		Conta contaCliente = null;

		List<Conta> contas = Dados.obterContas();
		boolean existeConta = false;

		for (Conta conta : contas) {
			if (cpf.equals(conta.getCpf())) {
				existeConta = true;
				contaCliente = conta;
				System.out.println(conta.getAgencia() + " - " + conta.getBanco() + " - " + conta.getConta() + " - "
						+ conta.getCpf() + " - " + conta.getValor());
			}
		}

		if (!existeConta)
			System.out.println("N�o existe conta cadastrada para este CPF!");
		else {
			List<Cliente> clientesDoBanco = Dados.obterCliente();

			boolean existeCliente = false;
			Cliente cliente = null;

			for (Cliente cli : clientesDoBanco) {
				if (cpf.equals(cli.getCpf())) {
					cliente = cli;
					existeCliente = true;
				}
			}

			if (!existeCliente) {
				System.out.println("Cliente n�o encontrado para este CPF");
			} else {

				System.out.println("Digite sua ag�ncia: ");
				String agenciaClienteDigitada = entrada.nextLine();

				System.out.println("Digite sua conta: ");
				String contaClienteDigitada = entrada.nextLine();

				if (contaClienteDigitada.equals(contaCliente.getConta())
						&& agenciaClienteDigitada.equals(contaCliente.getAgencia())) {
					System.out.println("Seja bem vindo(a) " + cliente.getNome() + "\n");
					System.out.println(contaCliente.getAgencia() + " - " + contaCliente.getBanco() + " - "
							+ contaCliente.getConta() + " - " + contaCliente.getCpf() + " - " + contaCliente.getValor()
							+ "\n");
					System.out.println(
							"Escolha uma op��o: \n 1 - Saque \n 2 - Deposito \n 3 - Transfer�ncia \nDigite o numero da op��o: ");
					int opcao = entrada.nextInt();

					if (opcao == 1) {
						System.out.println("Digite o valor a ser sacado: ");
						double valorSacado = entrada.nextDouble();

						if (valorSacado <= contaCliente.getValor()) {
							double saqueConta = contaCliente.getValor() - valorSacado;

							System.out.println("Voc� sacou: R$ " + valorSacado + "\nSeu saldo atual �: R$ " + saqueConta);
						} else {
							System.out.println("N�o existe saldo suficiente");
						}

					} else if (opcao == 2) {
						System.out.println("Seu saldo atual �: R$ " + contaCliente.getValor());
						System.out.println("Digite o valor a ser depositado");
						double valorDeposito = entrada.nextDouble();

						double somaValorDepsito = valorDeposito + contaCliente.getValor();

						System.out.println("Seu saldo ap�s o deposito �: R$ " + somaValorDepsito);
					}

				} else
					System.out.println("Conta ou ag�ncia inv�lida");
			}

		}

		entrada.close();

	}

}
