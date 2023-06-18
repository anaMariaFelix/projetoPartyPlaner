package controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Timer;

import baseDedados.CentralDeInformacoes;
import baseDedados.Persistencia;
import model.Reuniao;
import util.Constantes;

public class ReuniaoController {

	private static ReuniaoController instance;

	private ReuniaoController() {

	}

	public static ReuniaoController getInstance() {
		if (instance == null) {
			instance = new ReuniaoController();
		}
		return instance;
	}

	public boolean adicionarReuniao(Reuniao reuniao) {
		if (!existeReuniao(reuniao.getDataHora())) {
			CentralDeInformacoes.getInstance().getTodasAsReunioes().add(reuniao);
			Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
			return true;
		}
		return false;
	}

	public boolean existeReuniao(String data) {
		LocalDateTime dataComparar = quebraDataEConverteEmLocalDateTime(data);
		
		
		for (Reuniao reunioes : CentralDeInformacoes.getInstance().getTodasAsReunioes()) {
			LocalDateTime dataReuniao = quebraDataEConverteEmLocalDateTime(reunioes.getDataHora());
			long intervalo = Math.abs(dataReuniao.until(dataComparar, ChronoUnit.MINUTES));
			if (dataComparar.equals(dataReuniao)) {
				return true;
			}
			if (intervalo < 30) {
				return true;
			}		
		}

		return false;

	}

	public ArrayList<Reuniao> obterTodasReunioes() {
		return CentralDeInformacoes.getInstance().getTodasAsReunioes();
	}

	public LocalDateTime quebraDataEConverteEmLocalDateTime(String data) {
		String dataSemEspaco = data.replaceAll(" ", "");
		char[] novaData = new char[10];
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
	
	public void atualizarCentral() {
		Persistencia.getInstance().salvarCentral(CentralDeInformacoes.getInstance(), Constantes.NOME_ARQUIVO);
	}

}
