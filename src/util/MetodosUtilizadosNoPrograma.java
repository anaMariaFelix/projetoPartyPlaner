package util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import baseDedados.CentralDeInformacoes;
import model.Pessoa;
import model.Evento;

public class MetodosUtilizadosNoPrograma {
	
	private	static Scanner entrada = new Scanner(System.in);

//	public void menuInicial() {
//		System.out.print("------------------------------------------------------"
//				+ "\n                    Menu inicial"
//				+ "\n------------------------------------------------------"
//				+ "\n[1] Novo Cliente"
//				+ "\n[2] Listar todos os clientes"
//				+ "\n[3] Exibir informações de um cliente especifico"
//				+ "\n[4] Novo Evento"
//				+ "\n[5] Listar todos os evento"
//				+ "\n[6] Listar os evento de um(a) cliente"
//				+ "\n[7] Gerar relatorio de programação do mês"
//				+ "\n[8] Contratar evento"
//				+ "\n[S] Sair"
//				+ "\n"
//				+ "\n-Escolha uma opção:");
//
//	}
//
//	public Pessoa criaCliente() {
//		System.out.println("Dados do cliente: \n");
//		System.out.print("Nome: ");
//		String nome = entrada.nextLine();
//		
//		System.out.println("Dados do cliente: \n");
//		System.out.print("Nome: ");
//		String sobrenome = entrada.nextLine();
//
//		
//		System.out.print("Email: ");
//		String email = entrada.nextLine();
//
//		Pessoa cliente = new Pessoa(nome,sobrenome,null, email);
//
//		return cliente;
//			
//	}
//	
//	public void listaClientes(CentralDeInformacoes centralDeInformacoes) {
//		
//		ArrayList<Pessoa> lista = new ArrayList();
//		lista = centralDeInformacoes.getTodosOsClientes();
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getNome());
//		}
//	}
//	
//	
//	public void exibeInformacoesDeUmClienteEspecifico(Pessoa cliente) {
//		System.out.println("Nome: "+cliente.getNome());
////		System.out.println("CPF: "+cliente.getCPF());
////		System.out.println("Sexo: "+cliente.getSexo());
//		System.out.println("Email: "+cliente.getEmail());
//		System.out.println();
//		
//	}	
//	
//	
//	public Pessoa pegarCliente(String email, CentralDeInformacoes centralDeInformacoes) {
//		for(Pessoa c: centralDeInformacoes.getTodosOsClientes()) {
//			if(c.getEmail().equals(email)){
//				return c;
//			}
//		}
//		return null;
//		
//	}
//	
//	public boolean criarNovoEvento(Pessoa cliente, CentralDeInformacoes centralDeInformacoes) {
//		System.out.print("-Nome do evento: ");
//		String nomeEvento = entrada.nextLine();
//		
//		System.out.print("-Data Do Evento:"
//				+ "\nex: dia/mes/ano ");
//		String dataDoEvento = entrada.nextLine();
//		
//		if(verificarSeDataEValida(dataDoEvento)) {
//			System.out.print("-Hora Do Evento: HH:mm ");
//			String hora = entrada.nextLine();
//			
//			if(verificarSeHoraEValida(hora)) {
//				DateTimeFormatter forma = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//				LocalDateTime dateTime = LocalDateTime.parse(dataDoEvento +" "+ hora, forma );
//				
//				System.out.println("-Local Do Evento: ");
//				String localDoEvento = entrada.nextLine();
//				
//				Evento novoEvento = new Evento(nomeEvento,dateTime, localDoEvento, cliente,false);
//				
//				if(centralDeInformacoes.adicionarEvento(novoEvento)) {
//					System.out.println("-Evento cadatrado");
//					return true;
//				}else {
//					System.out.println("-Não foi possivel cadatrado o Evento ");
//				}
//				
//			}else {
//				System.out.println();
//				System.out.println("ERRO:\n-Hora invalida.");
//			}
//			
//					
//		}else {
//			System.out.println();
//			System.out.println("ERRO:\n-Data invalida.");
//		}
//		return false;
//	
//	}
//	
//	public boolean verificarSeDataEValida(String data) {
//		try {
//			DateTimeFormatter forma = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			LocalDate dataNew = LocalDate.parse(data,forma );
//			if(dataNew.isBefore(LocalDate.now())) {
//				return false;
//			}
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//	
//	public boolean verificarFormatoDaData(String data) {
//		if(data.charAt(2) == '/') {
//			if(data.charAt(5) == '/') {
//				return true;
//			}
//		}
//
//		
//		return false;
//	}
//	
//	public boolean verificarSeHoraEValida(String hora) {
//		String[] vetor;
//		vetor = hora.split(":");
//		int[] vetorInt = new int[2];
//		
//		if(VerificaFormatoDaHora(hora)) {
//			for(int i = 0;i < 2;i++) {
//				vetorInt[i] = Integer.parseInt(vetor[i]);
//			}
//			
//			if(vetorInt[0] >= 0 && vetorInt[0] <= 24) {
//				if(vetorInt[1] >= 0 && vetorInt[1] <= 60) {
//					return true;
//				}
//			}
//		}
//		
//		
//		return false;
//	} 
//	
//	public boolean VerificaFormatoDaHora(String hora) {
//		if(hora.charAt(2) == ':') {
//			return true;
//		}
//		
//		return false;
//	}
//	
//	public void listarTodosOsEvento(CentralDeInformacoes eventos) {
//		ArrayList<Evento> listDeEventos = eventos.getTodosEvento();
//		if(listDeEventos.isEmpty() == false) {
//			System.out.println("Eventos cadastrados: ");
//		
//			for(int i = 0;i < listDeEventos.size();i++) {
//				System.out.println("Evento: "+listDeEventos.get(i).getNome());
//			}
//		}else {
//			System.out.println("-Não existem eventos cadastrados");
//		}
//		
//	}
//	
//	
//	public void ListarEventoDeUmCliente(CentralDeInformacoes centralDeInformacoes) {
//		System.out.print("Informe email do cliente Associado: ");
//		String emeilDoClienteAssociado = entrada.nextLine();
//		
//		ArrayList<Evento> listDeEventos = centralDeInformacoes.getTodosEvento();
//		
//		if(centralDeInformacoes.existeCliente(emeilDoClienteAssociado)) {
//			System.out.println("Eventos do cliente: ");
//			
//			int quantidadeDeEventosDoCliente = 0;
//			for(int i = 0;i < listDeEventos.size();i++) {
//				if(listDeEventos.get(i).getClienteAssociado().getEmail().equals(emeilDoClienteAssociado)) {
//					System.out.println("-"+listDeEventos.get(i).getNome()+" Data: "+ listDeEventos.get(i).getDataHora());
//					quantidadeDeEventosDoCliente ++;
//				}
//			}
//			if(quantidadeDeEventosDoCliente == 0) {
//				System.out.println("-O cliente não possui nenhum evento cadastrado");
//			}
//			
//		}else {
//			System.out.println("-Cliente não associado.");
//		}
//		
//		
//	}
//	
//	public static char pegarCharUsuario(String respostaUsario,String nomeVariavel) {
//		if(respostaUsario.equalsIgnoreCase("F") || respostaUsario.equalsIgnoreCase("M")) {
//				return respostaUsario.charAt(0);
//				
//		}
//		System.out.print("\nErro:\n-informe novamente o "+nomeVariavel+": ");
//		respostaUsario = entrada.nextLine().toUpperCase().trim();
//		
//		return pegarCharUsuario(respostaUsario,nomeVariavel);
//		
//	}
//	
//	public static String pegarCPFUsuario() {
//		System.out.print("CPF: ");
//		String cpf = entrada.nextLine();
//		while(ValidadorCPF.isCPF(cpf) == false){
//			System.out.print("\nErro:\n-informe novamente o CPF: ");
//			cpf = entrada.nextLine();
//			
//		}
//		return cpf;
//	}
//
//	public static String converteDataLocalDateParaUmaDataString(LocalDateTime data) {
//	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
//	    return data.format(formatador);
//	}
//	
//	public ArrayList<Evento> listarEventosNContratados(CentralDeInformacoes arrayEvento){
//		ArrayList<Evento> eventos = new ArrayList();
//		
//		for(Evento evento: arrayEvento.getTodosEvento()) {
//			if(evento.getFoiContradoOuNao() == false) {
//				eventos.add(evento);
//			}
//		}
//		return eventos;
//	}
//	
//	public void printaEventosNContratados(ArrayList<Evento> eventos) {
//		int cont = 0;
//		for(Evento evento: eventos) {
//			System.out.println("["+cont++ +"]"+evento.getNome());
//		}
//	}
	

}
