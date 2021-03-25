package banco;

import java.util.Scanner;

import servicos.ClienteServico;
import servicos.ContaServico;
import utils.Dialog;

public class CaixaEletronico {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		try {

			String cpf = Dialog.obterDado("Digite seu CPF: ");

			Conta contaCliente = ContaServico.buscaContaPeloCpf(cpf);

			if (contaCliente == null)
				Dialog.mostrarMensagemErro("N�o existe conta cadastrada para este CPF!");

			else {
				Cliente cliente = ClienteServico.buscaClientePeloCpf(cpf);

				if (cliente == null) {
					Dialog.mostrarMensagemErro("Cliente n�o encontrado para este CPF");

				} else {
					String agenciaClienteDigitada = Dialog.obterDado("Digite sua ag�ncia: ");
					String contaClienteDigitada = Dialog.obterDado("Digite sua conta: ");

					if (contaClienteDigitada.equals(contaCliente.getConta())
							&& agenciaClienteDigitada.equals(contaCliente.getAgencia())) {
						Dialog.mostrarMensagemInfo("Seja bem vindo(a) " + cliente.getNome() + "\n\nSuas informa��es: \n"
								+ contaCliente.toString());

						int opcao = -1;
						do {
							String opcao1 = Dialog.obterDado(
									"Escolha uma op��o: \n 1 - Saque \n 2 - Deposito \n 3 - Transfer�ncia \nDigite o numero da op��o: ");

							try {
								opcao = Integer.parseInt(opcao1);
							} catch (Exception e) {
								Dialog.mostrarMensagemErro(
										"Valor informado n�o � um valor num�rico, favor informar um n�mero");
							}
						} while (opcao == -1);

						if (opcao == 1) {
							String valorSacado1 = Dialog.obterDado("Digite o valor a ser sacado: ").replace(",", ".");

							double valorSacado = Double.parseDouble(valorSacado1);
							if (valorSacado <= contaCliente.getValor()) {
								double saqueConta = contaCliente.getValor() - valorSacado;

								Dialog.mostrarMensagemInfo(
										"Voc� sacou: R$ " + valorSacado + "\nSeu saldo atual �: R$ " + saqueConta);

							} else {
								Dialog.mostrarMensagemErro("N�o existe saldo suficiente");

							}

						} else if (opcao == 2) {
							Dialog.mostrarMensagemInfo("Seu saldo atual �: R$ " + contaCliente.getValor());
							String valordeposito = Dialog.obterDado("Digite o valor a ser depositado").replace(",",
									".");

							double valorDeposito = Double.parseDouble(valordeposito);
							double somaValorDepsito = valorDeposito + contaCliente.getValor();

							Dialog.mostrarMensagemInfo("Seu saldo ap�s o deposito �: R$ " + somaValorDepsito);
						} else if (opcao == 3) {
							String buscaAgencia = Dialog.obterDado("Digite a ag�ncia: ");
							String buscaConta = Dialog.obterDado("Digite a conta: ");

							Conta contaSerTransferida = ContaServico.buscaContaPelaAgenciaEConta(buscaAgencia,
									buscaConta);

							if (contaSerTransferida == null || contaCliente.equals(contaSerTransferida)) {
								Dialog.mostrarMensagemErro("Conta n�o encontrada ou inexistente");

							} else {
								Cliente clienteTrasferido = ClienteServico
										.buscaClientePeloCpf(contaSerTransferida.getCpf());

								if (clienteTrasferido == null) {
									Dialog.mostrarMensagemErro("N�o foi possivel encontrar essa cliente");

								} else {
									Dialog.mostrarMensagemInfo(
											"Voc� deseja transferir para: " + clienteTrasferido.getNome()
													+ "\nAg�ncia: " + contaSerTransferida.getAgencia() + " - Conta: "
													+ contaSerTransferida.getConta());
									Dialog.mostrarMensagemInfo("Seu saldo atual �: R$ " + contaCliente.getValor());
									String valorTransferencia = Dialog.obterDado("Digite o valor a ser trasferido: ")
											.replace(",", ".");

									double valorTrasferido = Double.parseDouble(valorTransferencia);

									if (valorTrasferido <= contaCliente.getValor()) {
										double valorPosTransferencia = contaCliente.getValor() - valorTrasferido;
										contaCliente.setValor(valorPosTransferencia);
										Dialog.mostrarMensagemInfo(
												"Seu saldo ap�s a tranfer�ncia �: R$ " + contaCliente.getValor());

									} else {
										Dialog.mostrarMensagemErro(
												"Seu saldo � insuficiente para realizar essa opera��o");
									}
								}

							}
						} else {
							Dialog.mostrarMensagemErro("Op��o Inv�lida");
						}

					} else
						Dialog.mostrarMensagemErro("Conta ou ag�ncia inv�lida");
				}

			}

		} catch (Exception e) {
			if(e.getMessage().equals("apertouCancelar"))
				Dialog.mostrarMensagemInfo("Seu procedimento foi cancelado com sucesso!\nTenha um �timo dia.");
		}

		entrada.close();

	}

}
