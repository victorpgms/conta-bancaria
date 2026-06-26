package conta_bancaria;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {

	private static final Scanner teclado = new Scanner(System.in);
	private static final ContaController contaController = new ContaController();

	public static void main(String[] args) {

		int op = 0;

		// Criar dados de teste
		criarContasTeste();

		do {
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND);
			System.out.println("                                     ");
			System.out.println("        BANCO DO BRAZIL COM Z        ");
			System.out.println("                                     ");
			System.out.println("*************************************");
			System.out.println("                                     ");
			System.out.println("1 - Criar conta                      ");
			System.out.println("2 - Listar Todas as Contas           ");
			System.out.println("3 - Buscar Contas por Número         ");
			System.out.println("4 - Atualizar dados da Conta         ");
			System.out.println("5 - Apagar Conta                     ");
			System.out.println("6 - Sacar                            ");
			System.out.println("7 - Depositar                        ");
			System.out.println("8 - Transferir valores entre Contas  ");
			System.out.println("9 - Pesquisa por titular             ");
			System.out.println("0 - Sair                             ");
			System.out.println("                                     ");
			System.out.println("*************************************");
			System.out.println("                                     ");
			System.out.println("Entre com a opção desejada:          " + Cores.TEXT_RESET);

			try {

				op = teclado.nextInt();
				teclado.nextLine();
			} catch (InputMismatchException e) {
				op = -1;
				System.out.println("\n digite um número inteiro entre 0 e 8");
				teclado.nextLine();
			}

			switch (op) {
			case 1: {
				System.out.println("*************************************");
				System.out.println("CRIAR CONTA");
				System.out.println("*************************************");
				cadastrarConta();
				keyPress();

				break;
			}

			case 2: {
				System.out.println("*************************************");
				System.out.println("LISTAR TODAS AS CONTAS");
				System.out.println("*************************************");
				listarContas();
				keyPress();

				break;
			}

			case 3: {
				System.out.println("*************************************");
				System.out.println("BUSCAR CONTA POR NÚMERO");
				System.out.println("*************************************");
				procurarContaPorNumero();
				keyPress();

				break;
			}

			case 4: {
				System.out.println("*************************************");
				System.out.println("ATUALIZAR DADOS DA CONTA");
				System.out.println("*************************************");
				atualizarConta();
				keyPress();

				break;
			}

			case 5: {
				System.out.println("*************************************");
				System.out.println("APAGAR CONTA");
				System.out.println("*************************************");
				deletarConta();
				keyPress();

				break;
			}

			case 6: {
				System.out.println("*************************************");
				System.out.println("SACAR");
				System.out.println("*************************************");
				sacar();
				keyPress();

				break;
			}

			case 7: {
				System.out.println("*************************************");
				System.out.println("DEPOSITAR");
				System.out.println("*************************************");
				depositar();
				keyPress();

				break;
			}

			case 8: {
				System.out.println("*************************************");
				System.out.println("TRANSFERIR VALORES ENTRE CONTAS");
				System.out.println("*************************************");
				transferir();
				keyPress();

				break;
			}
			
			case 9: {
				System.out.println("*************************************");
				System.out.println("CONSULTA POR NOME DO TITULAR");
				System.out.println("*************************************");
				listarPorTitular();
				keyPress();

				break;
			}

			case 0: {
				System.out.println("*************************************");
				System.out.println("SAIR");
				System.out.println("*************************************");
				keyPress();

				break;
			}

			default:
				System.out.println("*************************************");
				System.out.println("Comando inválido");
				System.out.println("*************************************");
				break;
			}

		} while (op != 0);

	}

	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
		teclado.nextLine();
	}

	public static void criarContasTeste() {
		contaController.cadastrar(
				new ContaCorrente(contaController.gerarNumero(), 456, 1, "Thuany Silva", 2500.00f, 200.00f));

		contaController.cadastrar(
				new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Marcia Condarco", 1200.00f, 10));

	}

	public static void listarContas() {
		contaController.listarTodas();
	}

	public static void cadastrarConta() {

		System.out.println("Digite o número da agência: ");
		int agencia = teclado.nextInt();

		System.out.println("Digite o nome do titular da conta: ");
		teclado.skip("\\R");
		String titular = teclado.nextLine();

		System.out.println("Digite o tipo da conta (1 - CC | 2 - CP): ");
		int tipo = teclado.nextInt();
		

		System.out.println("Digite o saldo da conta: ");
		float saldo = teclado.nextFloat();

		switch (tipo) {
		case 1 -> {
			System.out.println("Digite o limite da conta: ");
			float limite = teclado.nextFloat();
			teclado.nextLine();

			contaController
					.cadastrar(new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));

		}
		case 2 -> {
			System.out.println("Digite o dia do aniversário da conta: ");
			int aniversario = teclado.nextInt();
			teclado.nextLine();

			contaController.cadastrar(
					new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular, saldo, aniversario));

		}

		default -> System.out.println(Cores.TEXT_RED + "Tipo de conta inválida" + Cores.TEXT_RESET);

		}

	}

	public static void procurarContaPorNumero() {
		System.out.println("Digite o número da conta: ");
		int numero = teclado.nextInt();
		teclado.nextLine();

		contaController.procurarPorNumero(numero);
	}

	public static void deletarConta() {
		System.out.println("Digite o número da conta: ");
		int numero = teclado.nextInt();
		teclado.nextLine();

		Optional<Conta> conta = contaController.buscarNaCollection(numero);

		if (conta.isPresent()) {
			// confirmar exclusão
			System.out.printf("\nTem certeza que você deseja excluir a conta numero %d? (S ou N)", numero);
			String confirmacao = teclado.nextLine();

			if (confirmacao.equalsIgnoreCase("s")) {
				contaController.deletar(numero);
			} else {
				System.out.println("\nOperação cancelada!");
			}
		} else {
			System.out.println("\nA conta não foi encontrada!");
		}

	}

	public static void atualizarConta() {
		System.out.println("Digite o número da conta: ");
		int numero = teclado.nextInt();
		teclado.nextLine();

		Optional<Conta> conta = contaController.buscarNaCollection(numero);

		if (conta.isPresent()) {

			// Obtem os dados atualis da conta
			int agencia = conta.get().getAgencia();
			String titular = conta.get().getTitular();
			float saldo = conta.get().getSaldo();
			int tipo = conta.get().getTipo();

			// Atualiza a agencia ou mantem o valor atual
			System.out.printf(
					"\nAgência atual: %d\n"
							+ "Digite o número da nova agência (Pressionae ENTER para manter o valor atual): \n",
					agencia);
			String entrada = teclado.nextLine();

			agencia = entrada.isEmpty() ? agencia : Integer.parseInt(entrada);

			// Atualiza o titular ou mantem o valor atual
			System.out.printf(
					"\nTitular atual: %s\n"
							+ "Digite o nome do novo titular (Pressionae ENTER para manter o valor atual): \n",
					titular);
			entrada = teclado.nextLine();

			titular = entrada.isEmpty() ? titular : entrada;

			// Atualiza o saldo ou mantem o valor atual
			System.out.printf(
					"\nSaldo atual: %.2f\n" + "Digite o novo saldo (Pressionae ENTER para manter o valor atual): \n",
					saldo);
			entrada = teclado.nextLine();

			saldo = (int) (entrada.isEmpty() ? saldo : Float.parseFloat(entrada.replace(",", ".")));

			switch (tipo) {
			case 1 -> {
				ContaCorrente contaCorrente = (ContaCorrente) conta.get();
				float limite = contaCorrente.getLimite();

				// Atualiza o limite ou mantem o valor atual
				System.out.printf("\nLimite atual: %.2f\n"
						+ "Digite o novo Limite (Pressionae ENTER para manter o valor atual): \n", limite);
				entrada = teclado.nextLine();

				limite = (int) (entrada.isEmpty() ? limite : Float.parseFloat(entrada.replace(",", ".")));

				contaController.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
			}

			case 2 -> {

				ContaPoupanca contaPoupanca = (ContaPoupanca) conta.get();
				int aniversario = contaPoupanca.getAniversario();

				// Atualiza o aniiversario ou mantem o valor atual
				System.out.printf("\nDia do aniversário atual: %d\n"
						+ "Digite o novo saldo (Pressionae ENTER para manter o valor atual): \n", aniversario);
				entrada = teclado.nextLine();

				aniversario = (int) (entrada.isEmpty() ? aniversario : Integer.parseInt(entrada));

				contaController.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, aniversario));
			}

			}

		} else

		{
			System.out.printf("\nA conta número %d não encontrada!", numero);
		}
	}

	public static void sacar() {

		System.out.println("Digite o número da conta: ");
		int numero = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Digite o valor do saque: ");
		float valor = teclado.nextFloat();

		contaController.sacar(numero, valor);
	}

	public static void depositar() {

		System.out.println("Digite o número da conta: ");
		int numero = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Digite o valor do depósito: ");
		float valor = teclado.nextFloat();

		contaController.depositar(numero, valor);
	}

	public static void transferir() {

		System.out.println("Digite o número da conta de origem: ");
		int numeroOrigem = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Digite o número da conta de destino: ");
		int numeroDestino = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Digite o valor da transferência: ");
		float valor = teclado.nextFloat();

		contaController.transferir(numeroOrigem, numeroDestino, valor);
	}

	public static void listarPorTitular() {
		System.out.println("Digite o nome do titular da conta: ");
		//teclado.skip("\\R");
		String titular = teclado.nextLine();

		contaController.listarPorTitular(titular);

	}

}
