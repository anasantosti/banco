package banco;

import java.util.Scanner;

import servicos.ClienteServico;
import servicos.ContaServico;

public class CaixaEletronico {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite seu CPF: ");
		String cpf = entrada.nextLine();

		Conta contaCliente = ContaServico.buscaContaPeloCpf(cpf);

		if (contaCliente == null)
			System.out.println("Não existe conta cadastrada para este CPF!");

		else {

			Cliente cliente = ClienteServico.buscaClientePeloCpf(cpf);

			if (cliente == null) {
				System.out.println("Cliente não encontrado para este CPF");
			} else {

				System.out.println("Digite sua agência: ");
				String agenciaClienteDigitada = entrada.nextLine();

				System.out.println("Digite sua conta: ");
				String contaClienteDigitada = entrada.nextLine();

				if (contaClienteDigitada.equals(contaCliente.getConta())
						&& agenciaClienteDigitada.equals(contaCliente.getAgencia())) {
					System.out.println("\nSeja bem vindo(a) " + cliente.getNome() + "\n");
					System.out.println(contaCliente + "\n");
					
					System.out.println(
							"Escolha uma opção: \n 1 - Saque \n 2 - Deposito \n 3 - Transferência \nDigite o numero da opção: ");
					int opcao = entrada.nextInt();
					entrada.nextLine();

					if (opcao == 1) {
						System.out.println("Digite o valor a ser sacado: ");
						String valorsacado = entrada.next().replace(",", ".");

						double valorSacado = Double.parseDouble(valorsacado);
						if (valorSacado <= contaCliente.getValor()) {
							double saqueConta = contaCliente.getValor() - valorSacado;

							System.out.println("Você sacou: R$ " + valorSacado + "\nSeu saldo atual é: R$ " + saqueConta);
						} else {
							System.out.println("Não existe saldo suficiente");
						}

					} else if (opcao == 2) {
						System.out.println("Seu saldo atual é: R$ " + contaCliente.getValor());
						System.out.println("Digite o valor a ser depositado");
						String valordeposito = entrada.next().replace(",", ".");

						double valorDeposito = Double.parseDouble(valordeposito);
						double somaValorDepsito = valorDeposito + contaCliente.getValor();

						System.out.println("\nSeu saldo após o deposito é: R$ " + somaValorDepsito);
					} else if (opcao == 3) {
						System.out.println("Digite a agência: ");
						String buscaAgencia = entrada.nextLine();

						System.out.println("Digite a conta: ");
						String buscaConta = entrada.nextLine();

						Conta contaSerTransferida = ContaServico.buscaContaPelaAgenciaEConta(buscaAgencia, buscaConta);

						if (contaSerTransferida == null || contaCliente.equals(contaSerTransferida)) {
							System.out.println("Conta não encontrada ou inexistente");
						} else {

							Cliente clienteTrasferido = ClienteServico.buscaClientePeloCpf(contaSerTransferida.getCpf());

							if (clienteTrasferido == null) {
								System.out.println("Não foi possivel encontrar essa cliente");

							} else {

								System.out.println("Você deseja transferir para: " + clienteTrasferido.getNome());
								System.out.println("Agência: " + contaSerTransferida.getAgencia() + " - Conta: "
										+ contaSerTransferida.getConta());

								System.out.println("\nSeu saldo atual é: R$ " + contaCliente.getValor());
								System.out.println("\nDigite o valor a ser trasferido: ");
								String valorTransferencia = entrada.next().replace(",", ".");

								double valorTrasferido = Double.parseDouble(valorTransferencia);

								if (valorTrasferido <= contaCliente.getValor()) {
									double valorPosTransferencia = contaCliente.getValor() - valorTrasferido;
									contaCliente.setValor(valorPosTransferencia);
									System.out
											.println("Seu saldo após a tranferência é: R$ " + contaCliente.getValor());
								} else {
									System.out.println("Seu saldo é insuficiente para realizar essa operação");
								}
							}

						} 
					} else {
						System.out.println("Opção Inválida");
					}
					

				} else
					System.out.println("Conta ou agência inválida");
			}

		}

		entrada.close();

	}

}
