package conta_bancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
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
				keyPress();

				break;
			}

			case 4: {
				System.out.println("*************************************");
				System.out.println("ATUALIZAR DADOS DA CONTA");
				System.out.println("*************************************");
				keyPress();

				break;
			}

			case 5: {
				System.out.println("*************************************");
				System.out.println("APAGAR CONTA");
				System.out.println("*************************************");
				keyPress();

				break;
			}

			case 6: {
				System.out.println("*************************************");
				System.out.println("SACAR");
				System.out.println("*************************************");
				keyPress();

				break;
			}

			case 7: {
				System.out.println("*************************************");
				System.out.println("DEPOSITAR");
				System.out.println("*************************************");
				keyPress();

				break;
			}

			case 8: {
				System.out.println("*************************************");
				System.out.println("TRANSFERIR VALORES ENTRE CONTAS");
				System.out.println("*************************************");
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
				new ContaCorrente(contaController.gerarNumero(), 456, 1, "Thuany Silva", 1000000.00f, 100000.00f));

		contaController.cadastrar(
				new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Marcia Condarco", 1000000.00f, 10));

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

}
