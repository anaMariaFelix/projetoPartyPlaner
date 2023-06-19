package relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.Constantes;

public class GeradorDePlanilha {

	private static final String fileName = Constantes.NOME_PLANILHA;

	public static void criarPlanilha(OrcamentoOuContrato orcamentoOuContrato) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetUsers = workbook.createSheet("Usuarios");

		int numLinha = 0;
		int cellnum = 0;
		if (!orcamentoOuContrato.getFornecedores().isEmpty()) {
			for (Pessoa fornecedor : orcamentoOuContrato.getFornecedores()) {
				

				Row row = sheetUsers.createRow(numLinha++);
				cellnum = 0;
				Cell cellId = row.createCell(cellnum++);
				cellId.setCellValue(fornecedor.getNome());
				Cell cellNome = row.createCell(cellnum++);
				cellNome.setCellValue(Integer.parseInt(orcamentoOuContrato.getValor())/orcamentoOuContrato.getFornecedores().size());
			}
		}else {
			Row row = null;
			for (Pacote pacote : orcamentoOuContrato.getPacotesDeFornecedores()) {
				row = sheetUsers.createRow(numLinha++);
				cellnum = 0;
				Cell cellId = row.createCell(cellnum++);
				cellId.setCellValue(pacote.getNomeDoPacote());
				for (Pessoa pessoa: pacote.getTodosFornecedore()) {
					Cell cellNome = row.createCell(cellnum++);
					cellNome.setCellValue(pessoa.getNome());
				}
				
				
			}
			row = sheetUsers.createRow(numLinha++);
			cellnum = 0;
			Cell cellTitulo = row.createCell(cellnum++);
			cellTitulo.setCellValue("Valor Final: ");
			Cell cellValor = row.createCell(cellnum++);
			cellValor.setCellValue(orcamentoOuContrato.getValor());
			
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(GeradorDePlanilha.fileName));
			workbook.write(out);
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
