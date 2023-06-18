package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import baseDedados.CentralDeInformacoes;
import model.Reuniao;

public class ReuniaoController {

	private static ReuniaoController instance;
	
	static ArrayList<Reuniao> listaTeste = new ArrayList();
	
	private ReuniaoController() {
		
	}
	
	public ReuniaoController getInstance() {
		if (instance == null) {
			instance = new ReuniaoController();
		}
		return instance;
	}
	
	
	
	public static boolean adicionarReuniao(Reuniao reuniao) {
		if (!existeReuniao(reuniao.getData())) {
			listaTeste.add(reuniao);
			return true;
		}
		return false;
	}
	
	public static boolean existeReuniao(LocalDateTime data) {
		for (Reuniao reunioes: listaTeste) {
			if (reunioes.getData().equals(data) || data.getHour() < reunioes.getData().getHour()-30) {
				return true;
			}
		}
		
		
		return false;
	}
	
	public static ArrayList<Reuniao> obterTodasReunioes(){
		return CentralDeInformacoes.getInstance().getTodasAsReunioes();
	}
	
	public static void main(String[] args) {
		
		ReuniaoController instance = new ReuniaoController();
		
		Reuniao r = new Reuniao();
		
		r.setData(quebraDataEConverteEmLocalDateTime("12/12/2000 12:30"));
		
		adicionarReuniao(r);
		Reuniao r2 = new Reuniao();
		r2.setData(quebraDataEConverteEmLocalDateTime("12/12/2000 12:30"));
		
		if (adicionarReuniao(r2)) {
			JOptionPane.showMessageDialog(null,"DEU CERTO");
		}else {
			JOptionPane.showMessageDialog(null, "Deu errado");
		}
		
		
		
		
		
		
		
	}
	public static LocalDateTime quebraDataEConverteEmLocalDateTime(String data) {
		String dataSemEspaco = data.replaceAll(" ", "");
		char [] novaData = new char[10];
		char[] hora = new char[5];
		int cont = 0;
		for (int i = 0; i < dataSemEspaco.length(); i++) {
			if (i < 10) {
				novaData[i] = dataSemEspaco.charAt(i);
			} else {
				hora[cont] = dataSemEspaco.charAt(i);
				cont++;
			}

		}
		String dataFinal = String.valueOf(novaData);
		String horaFinal = String.valueOf(hora);

		DateTimeFormatter forma = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dataFinal + " " + horaFinal, forma);
		return dateTime;
	}
	
	
}
