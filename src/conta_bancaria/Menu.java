package conta_bancaria;

import java.util.Scanner;

import conta_bancaria.model.Conta;
import conta_bancaria.util.*;

public class Menu {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		int op = 0;
		
		Conta c1 = new Conta(1, 123, 1, "Isabella", 200000.00f);
		Conta c2 = new Conta(2, 123, 1, "Victor", 200000.00f);
		
		c1.visualizar();
		
		c1.setSaldo(300.00f);
		
		c1.visualizar();
		
		System.out.println("Sacar R$ 1000 da conta c1 " + (c1.sacar(1000.00f) ? 
				"Saque efetuado com sucesso" : "Saldo insuficiente!"));
		
		c2.depositar(50000.00f);
		c2.visualizar();

		System.out.println(c1.getTitular());
		
		
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
			System.out.println(  "Entre com a opção desejada:          " + Cores.TEXT_RESET);
			op = teclado.nextInt();
			System.out.println("                                     ");
			
			switch (op) {
			case 1: {
				System.out.println("*************************************");
				System.out.println("CRIAR CONTA");
				System.out.println("*************************************");
				
				break;
			}
			
			case 2: {
				System.out.println("*************************************");
				System.out.println("LISTAR TODAS AS CONTAS");
				System.out.println("*************************************");

				
				break;
			}
			
			case 3: {
				System.out.println("*************************************");
				System.out.println("BUSCAR CONTA POR NÚMERO");
				System.out.println("*************************************");
				
				break;
			}
			
			case 4: {
				System.out.println("*************************************");
				System.out.println("ATUALIZAR DADOS DA CONTA");
				System.out.println("*************************************");
				
				break;
			}
			
			case 5: {
				System.out.println("*************************************");
				System.out.println("APAGAR CONTA");
				System.out.println("*************************************");
				
				break;
			}
			
			case 6: {
				System.out.println("*************************************");
				System.out.println("SACAR");
				System.out.println("*************************************");
				
				break;
			}
			
			case 7: {
				System.out.println("*************************************");
				System.out.println("DEPOSITAR");
				System.out.println("*************************************");
				
				break;
			}
			
			case 8: {
				System.out.println("*************************************");
				System.out.println("TRANSFERIR VALORES ENTRE CONTAS");
				System.out.println("*************************************");
				
				break;
			}
			
			case 0: {
				System.out.println("*************************************");
				System.out.println("SAIR");
				System.out.println("*************************************");
				
				break;
			}

			default:
				System.out.println("*************************************");
				System.out.println("Comando inválido");
				System.out.println("*************************************");
				break;
			}
			
		}while (op != 0);

	}

}
