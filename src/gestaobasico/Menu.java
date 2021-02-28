package gestaobasico;

import java.util.Scanner;

public class Menu {

	static Scanner sc = new Scanner(System.in);
	static Gerencia g = new Gerencia();
	
	public static void main(String[] args) {
		menuPrincipal();
		System.out.println("Programa encerrado.");
	}
	
	private static void menuPrincipal() { // Menu principal
		int opcao = -1;
		do {
			System.out.println("\n\n### GESTAOEVENTOS - Menu Principal ###");
			System.out.println("\n                  =========================");
			System.out.println("                  |     1 - Cadastrar     |");
			System.out.println("                  |     2 - Consultar     |");
			System.out.println("                  |     0 - Sair          |");
			System.out.println("                  =========================\n");

			opcao = Integer.parseInt(sc.nextLine());
			switch (opcao) {
			case 1:
				menuCadastro();
				break;
			case 2:
				if ((g.getSizePessoa() == 0) || (g.getSizeSala() == 0) ||(g.getSizeEspaco() == 0)) {
					System.out.println("\n\n### GESTAOEVENTOS - ERRO ###");
					System.out.println("Faltam dados a serem cadastrados!");
				} else {
					// relaciona toda vez que consultar para contabilizar possiveis novas salas
					g.relaciona();
					menuConsultar();
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 0);
	}
	
	private static void menuCadastro() { // Menu de cadastro
		int opcao = 0;
		do {
			System.out.println("\n\n### GESTAOEVENTOS - Menu de Cadastro ###");
			System.out.println("\n                  ==============================");
			System.out.println("                  |     1 - Pessoa             |");
			System.out.println("                  |     2 - Sala do Evento     |");
			System.out.println("                  |     3 - Espaco de Cafe     |");
			System.out.println("                  |     0 - Voltar             |");
			System.out.println("                  ==============================\n");

			opcao = Integer.parseInt(sc.nextLine());
			switch (opcao) {
			case 1:
				g.cadPessoa();
				break;
			case 2:
				g.cadSala();
				break;
			case 3:
				g.cadEspaco();
			case 0:
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 0);
	}
	
	private static void menuConsultar() { // Menu de consulta
		int opcao = 0;
		do {
			System.out.println("\n\n### GESTAOEVENTOS - Menu de Consulta ###");
			System.out.println("\n                  ==============================");
			System.out.println("                  |     1 - Pessoa             |");
			System.out.println("                  |     2 - Sala do Evento     |");
			System.out.println("                  |     3 - Espaco de Cafe     |");
			System.out.println("                  |     0 - Voltar             |");
			System.out.println("                  ==============================\n");

			opcao = Integer.parseInt(sc.nextLine());
			System.out.print("\n");
			switch (opcao) {
			case 1:
				g.consPessoa();
				break;
			case 2:
				g.consSala();
				break;
			case 3:
				g.consEspaco();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 0);
	}
}
