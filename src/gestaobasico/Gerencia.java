package gestaobasico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerencia {
	
	private Scanner sc = new Scanner(System.in);

	private List<Pessoa> lstPessoa = new ArrayList<Pessoa>();
	private List<EspacoCafe> lstEspaco = new ArrayList<EspacoCafe>();
	private List<SalaEvento> lstSala = new ArrayList<SalaEvento>();
	
	/**
	 * GETTERS
	 */
	public Integer getSizePessoa() {
		return lstPessoa.size();
	}
	
	public Integer getSizeSala() {
		return lstSala.size();
	}

	public Integer getSizeEspaco() {
		return lstEspaco.size();
	}
	

	public void cadPessoa() {
		Pessoa p = new Pessoa();
		
		System.out.println("\n\n### GESTAOEVENTOS - Cadastrar Pessoa ###");
		System.out.print("\nInforme o Nome da Pessoa: ");
		p.setNome(sc.nextLine());
		System.out.print("Informe o Sobrenome da Pessoa: ");
		p.setSobrenome(sc.nextLine());
		
		p.setIdPessoa(getSizePessoa()+1);
		
		lstPessoa.add(p);
	}
	
	
	public void cadSala() {
		SalaEvento se = new SalaEvento();
		
		System.out.println("\n\n### GESTAOEVENTOS - Cadastrar Sala do Evento ###");
		System.out.print("\nInforme o Nome da Sala: ");
		se.setNome(sc.nextLine());
		System.out.print("Informe a Lotacao da Sala: ");
		se.setLotacao(Integer.parseInt(sc.nextLine()));
		
		se.setIdSalaEvento(getSizeSala()+1);
		
		lstSala.add(se);
	}
	
	
	public void cadEspaco() {
		if(getSizeEspaco() > 0) {
			lstEspaco.clear();
		}
		
		EspacoCafe ec;
		
		System.out.println("\n\n### GESTAOEVENTOS - Cadastrar Espaco de Cafe ###");
		
		System.out.print("\nInforme o Nome do Primeiro Espaco: ");
		ec = new EspacoCafe();
		ec.setNome(sc.nextLine());
		ec.setIdEspacoCafe(getSizeEspaco()+1);
		lstEspaco.add(ec);
		
		System.out.print("Informe o Nome do Segundo Espaco: ");
		ec = new EspacoCafe();
		ec.setNome(sc.nextLine());
		ec.setIdEspacoCafe(getSizeEspaco()+1);
		lstEspaco.add(ec);
	}
	
	public void consPessoa() {
		Integer op = -1;
		
		do {
			System.out.println("\n\n### GESTAOEVENTOS - Consultar Pessoa ###");
			System.out.print("\n");
			for(Pessoa p : lstPessoa) {
			    System.out.println (p.getIdPessoa() + " - " + p.getNome() + " " + p.getSobrenome());
			}
			System.out.print("\nInforme a Pessoa que deseja Consultar (0 para VOLTAR): ");
			op = Integer.parseInt(sc.nextLine());
			System.out.print("\n");
			
			if((op < 0) || (op > getSizePessoa())) {
				System.out.println("Opção Inválida!");
			} else if(op != 0) {
				Pessoa p = buscaPessoa(op);
				
				System.out.println("\n## GESTAOEVENTOS - Resultado da Consulta ##\n");
				System.out.println("Etapa 1:\t" + buscaSala(p.getIdSalaEtapa1()).getNome() +
						"\nEtapa 2:\t" + buscaSala(p.getIdSalaEtapa2()).getNome() + 
						"\nEspaco:\t\t" + buscaEspaco(p.getIdEspaco()).getNome());
				System.out.print("\n");
			}
		} while (op != 0);
	}
	
	public void consSala() {
		Integer op = -1;
		
		do {
			System.out.println("\n\n### GESTAOEVENTOS - Consultar Sala do Evento ###");
			System.out.print("\n");
			for(SalaEvento s : lstSala) {
			    System.out.println (s.getIdSalaEvento() + " - " + s.getNome());
			}
			System.out.print("\nInforme a Sala do Evento que deseja Consultar (0 para VOLTAR): ");
			op = Integer.parseInt(sc.nextLine());
			System.out.print("\n");
			
			if((op < 0) || (op > getSizeSala())) {
				System.out.println("Opção Inválida!");
			} else if(op != 0) {
				SalaEvento s = buscaSala(op);
				List<Pessoa> lst;
				
				System.out.println("\n## GESTAOEVENTOS - Resultado da Consulta ##\n");
				System.out.println("Etapa 1:");
				lst = buscaListaPessoas(s, 1);
				for(Pessoa p : lst) {
				    System.out.println (p.getNome() + " " + p.getSobrenome());
				}
				
				System.out.println("\nEtapa 2:");
				lst = buscaListaPessoas(s, 2);
				for(Pessoa p : lst) {
				    System.out.println (p.getNome() + " " + p.getSobrenome());
				}
				System.out.print("\n");
			}
		} while (op != 0);
	}
	
	public void consEspaco() {
		Integer op = -1;
		
		do {
			System.out.println("\n\n### GESTAOEVENTOS - Consultar Espaco de Cafe ###");
			System.out.print("\n");
			for(EspacoCafe e : lstEspaco) {
			    System.out.println(e.getIdEspacoCafe() + " - " + e.getNome());
			}
			System.out.print("\nInforme a Sala do Evento que deseja Consultar (0 para VOLTAR): ");
			op = Integer.parseInt(sc.nextLine());
			System.out.print("\n");
			
			if((op < 0) || (op > getSizeEspaco())) {
				System.out.println("Opção Inválida!");
			} else if(op != 0) {
				List<Pessoa> lst = buscaListaPessoas(buscaEspaco(op));
				
				System.out.println("\n## GESTAOEVENTOS - Resultado da Consulta ##\n");
				for(Pessoa p : lst) {
				    System.out.println(p.getNome() + " " + p.getSobrenome());
				}
				System.out.print("\n");
			}
		} while (op != 0);
	}
	
	private void listarPessoas() {
		Pessoa p = new Pessoa();
		System.out.print("\n\n");
		for(int i=0; i<getSizePessoa(); i++) {
			p = lstPessoa.get(i);
			System.out.println (p.getNome() + " " + p.getSobrenome() +
					"\nEtapa 1: " + buscaSala(p.getIdSalaEtapa1()).getNome() +
					"\nEtapa 2: " + buscaSala(p.getIdSalaEtapa2()).getNome() + 
					"\nEspaco" + buscaEspaco(p.getIdEspaco()).getNome() + "\n");
		}
	}
	
	private Pessoa buscaPessoa(Integer id) {
		for (int i=0; i<getSizePessoa(); i++) {
		    if(lstPessoa.get(i).getIdPessoa() == id) {
		    	return lstPessoa.get(i);
		    }
		}
		return null;
	}
	
	private SalaEvento buscaSala(Integer id) {
		for (int i=0; i<getSizeSala(); i++) {
		    if(lstSala.get(i).getIdSalaEvento() == id) {
		    	return lstSala.get(i);
		    }
		}
		return null;
	}
	
	private EspacoCafe buscaEspaco(Integer id) {
		for (int i=0; i<getSizeSala(); i++) {
		    if(lstEspaco.get(i).getIdEspacoCafe() == id) {
		    	return lstEspaco.get(i);
		    }
		}
		return null;
	}
	
	private List<Pessoa> buscaListaPessoas(SalaEvento s, Integer etapa) {
		List<Pessoa> lst = new ArrayList<Pessoa>();
		
		for(Pessoa p : lstPessoa) {
			if (etapa == 1) {
				if(p.getIdSalaEtapa1() == s.getIdSalaEvento()) {
					lst.add(p);
				}
			} else {
				if(p.getIdSalaEtapa2() == s.getIdSalaEvento()) {
					lst.add(p);
				}
			}
		}
		
		return lst;
	}
	
	private List<Pessoa> buscaListaPessoas(EspacoCafe e) {
		List<Pessoa> lst = new ArrayList<Pessoa>();
		
		for(Pessoa p : lstPessoa) {
			if(p.getIdEspaco() == e.getIdEspacoCafe()) {
				lst.add(p);
			}
		}
		
		return lst;
	}
	
	public void relaciona() {
		Pessoa p;
		Integer v1, v2, v3, aux;
		boolean trocar = true;
		
		for(int i=0; i<getSizePessoa(); i++) {
			
			p = lstPessoa.get(i);
			v1 = p.getIdPessoa() % getSizeEspaco();
			v2 = p.getIdPessoa() % getSizeSala();
			v3 = (p.getIdPessoa() + 1) % getSizeSala();
			
			if(i % getSizeSala() == 0) {
				if (trocar) {
					trocar = false;
				} else {
					trocar = true;
				}
			}
			
			// relacionando dos espacos de cafe
			if(v1 == 0) {
				aux = (lstEspaco.get(getSizeEspaco() - 1)).getIdEspacoCafe();
				p.setIdEspaco(aux);
			} else {
				aux = (lstEspaco.get(v1 - 1)).getIdEspacoCafe();
				p.setIdEspaco(aux);
			}
			
			// relacionando das salas do evento
			
			// etapa 1
			if (v2 == 0) {
				aux = (lstSala.get(getSizeSala() - 1)).getIdSalaEvento();
				p.setIdSalaEtapa1(aux);
			} else {
				aux = (lstSala.get(v2 - 1)).getIdSalaEvento();
				p.setIdSalaEtapa1(aux);
			}
			
			// etapa 2
			if (trocar) {
				if (v3 == 0) {
					aux = (lstSala.get(getSizeSala() - 1)).getIdSalaEvento();
					p.setIdSalaEtapa2(aux);
				} else {
					aux = (lstSala.get(v3 - 1)).getIdSalaEvento();
					p.setIdSalaEtapa2(aux);
				}
			} else {
				p.setIdSalaEtapa2(p.getIdSalaEtapa1());
			}
			
			
			
		}
	}
	
	public void testar() {
		testarInserir();
		relaciona();
		//consPessoa();
		//consSala();
		//consEspaco();
	}
	
	private void testarInserir() {
		testarInserirPessoa("Rodrigo", "Abcdef");
		testarInserirPessoa("Larissa", "Bcdefg");
		testarInserirPessoa("Andre", "Cdefgh");
		testarInserirPessoa("Marisa", "Defghi");
		testarInserirPessoa("Paulo", "Efghij");
		testarInserirPessoa("Bruna", "Fghijk");
		
		testarInserirSala("Sala 10", 20);
		testarInserirSala("Sala 11", 15);
		testarInserirSala("Sala 12", 18);
		
		testarInserirEspaco("Espaco A");
		testarInserirEspaco("Espaco B");
	}
	
	private void testarInserirPessoa(String nome, String sn) {
		Pessoa p = new Pessoa();
		p.setNome(nome);
		p.setSobrenome(sn);
		p.setIdPessoa(getSizePessoa()+1);
		
		lstPessoa.add(p);
	}

	private void testarInserirSala(String nome, Integer lot) {
		SalaEvento se = new SalaEvento();
		
		se.setNome(nome);
		se.setLotacao(lot);
		se.setIdSalaEvento(getSizeSala()+1);
		
		lstSala.add(se);
	}

	private void testarInserirEspaco(String nome) {
		EspacoCafe ec = new EspacoCafe();
		
		ec.setNome(nome);
		
		ec.setIdEspacoCafe(getSizeEspaco()+1);
		
		lstEspaco.add(ec);
	}
	
	
}
