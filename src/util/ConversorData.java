package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ConversorData {

	public static LocalDateTime quebraDataEConverteEmLocalDateTime(String data) {
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

	public static boolean verificarSeDataEValida(String data) {
		try {
			LocalDate dataNew = LocalDate.parse(data,
					DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

			if (dataNew.isBefore(LocalDate.now())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verificaSeStringContemApenasNumeros(String tamanho) {
		try {
			float tamanhoF = Float.parseFloat(tamanho);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean verificarSeHoraEValida(String hora) {
		String[] vetor;
		vetor = hora.split(":");
		int[] vetorInt = new int[2];

		if (VerificaFormatoDaHora(hora)) {
			for (int i = 0; i < 2; i++) {
				vetorInt[i] = Integer.parseInt(vetor[i]);
			}

			if (vetorInt[0] >= 0 && vetorInt[0] <= 24) {
				if (vetorInt[1] >= 0 && vetorInt[1] <= 60) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean VerificaFormatoDaHora(String hora) {
		if (hora.charAt(2) == ':') {
			return true;
		}

		return false;
	}
	
}
