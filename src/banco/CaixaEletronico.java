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
					System.out.println("\nSeja bem vindo(a) " + cliente.getNome() + "\n");
					System.out.println(contaCliente.getAgencia() + " - " + contaCliente.getBanco() + " - "
							+ contaCliente.getConta() + " - " + contaCliente.getCpf() + " - " + contaCliente.getValor()
							+ "\n");
					System.out.println(
							"Escolha uma op��o: \n 1 - Saque \n 2 - Deposito \n 3 - Transfer�ncia \nDigite o numero da op��o: ");
					int opcao = entrada.nextInt();
					entrada.nextLine();

					if (opcao == 1) {
						System.out.println("Digite o valor a ser sacado: ");
						String valorsacado = entrada.next().replace(",", ".");

						double valorSacado = Double.parseDouble(valorsacado);
						if (valorSacado <= contaCliente.getValor()) {
							double saqueConta = contaCliente.getValor() - valorSacado;

							System.out
									.println("Voc� sacou: R$ " + valorSacado + "\nSeu saldo atual �: R$ " + saqueConta);
						} else {
							System.out.println("N�o existe saldo suficiente");
						}

					} else if (opcao == 2) {
						System.out.println("Seu saldo atual �: R$ " + contaCliente.getValor());
						System.out.println("Digite o valor a ser depositado");
						String valordeposito = entrada.next().replace(",", ".");

						double valorDeposito = Double.parseDouble(valordeposito);
						double somaValorDepsito = valorDeposito + contaCliente.getValor();

						System.out.println("\nSeu saldo ap�s o deposito �: R$ " + somaValorDepsito);
					} else if (opcao == 3) {
						System.out.println("Digite a ag�ncia: ");
						String buscaAgencia = entrada.nextLine();

						System.out.println("Digite a conta: ");
						String buscaConta = entrada.nextLine();

						Conta contaSerTransferida = null;
						for (Conta conta : contas) {
							if (buscaAgencia.equals(conta.getAgencia()) && buscaConta.equals(conta.getConta())) {
								if (!contaCliente.equals(conta))
									contaSerTransferida = conta;
							}
						}

						if (contaSerTransferida == null) {
							System.out.println("Conta n�o encontrada ou inexistente");
						} else {

							Cliente clienteTrasferido = null;
							for (Cliente cli : clientesDoBanco) {
								if (cli.getCpf().equals(contaSerTransferida.getCpf())) {
									clienteTrasferido = cli;
								}
							}

							if (clienteTrasferido == null) {
								System.out.println("N�o foi possivel encontrar essa cliente");

							} else {

								System.out.println("Voc� deseja transferir para: " + clienteTrasferido.getNome());
								System.out.println("Ag�ncia: " + contaSerTransferida.getAgencia() + " - Conta: "
										+ contaSerTransferida.getConta());

								System.out.println("\nSeu saldo atual �: R$ " + contaCliente.getValor());
								System.out.println("\nDigite o valor a ser trasferido: ");
								String valorTransferencia = entrada.next().replace(",", ".");

								double valorTrasferido = Double.parseDouble(valorTransferencia);

								if (valorTrasferido <= contaCliente.getValor()) {
									double valorPosTransferencia = contaCliente.getValor() - valorTrasferido;
									contaCliente.setValor(valorPosTransferencia);
									System.out
											.println("Seu saldo ap�s a tranfer�ncia �: R$ " + contaCliente.getValor());
								} else {
									System.out.println("Seu saldo � insuficiente para realizar essa opera��o");
								}
							}

						}
					}

				} else
					System.out.println("Conta ou ag�ncia inv�lida");
			}

		}

		entrada.close();

	}

}
