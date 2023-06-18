package teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.Pessoa;

public class CreateExcel {

	private static final String fileName = "Planilha.xls";

	public static void main(String[] args) throws IOException {

		List<Pessoa> lisUsers = new ArrayList<Pessoa>();

		lisUsers.add(new Pessoa("Andrey", "Santana", "98724282525", "eaaaao@gmail.com"));
		lisUsers.add(new Pessoa("Eduardo", "Santana", "9876525", "eduardo@gmail.com"));

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetUsers = workbook.createSheet("Usuarios");

		int numLinhas = 0;

		for (Pessoa user : lisUsers) {

			Row row = sheetUsers.createRow(numLinhas++);
			int cellnum = 0;
			Cell cellId = row.createCell(cellnum++);
			cellId.setCellValue(user.getNome());
			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(user.getEmail());
			Cell cellEmail = row.createCell(cellnum++);
			cellEmail.setCellValue(user.getSobrenome());
			Cell cellSenha = row.createCell(cellnum++);
			cellSenha.setCellValue(user.getTelefone());
			Cell cellTelefone = row.createCell(cellnum++);

		}
		try {
			FileOutputStream out = new FileOutputStream(new File(CreateExcel.fileName));

			workbook.write(out);
			out.close();
			System.out.println("Arquivo Excel criado com sucesso!");

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		} catch (IOException e) {
			System.out.println("Erro na edição do arquivo!");
		}

	}

}