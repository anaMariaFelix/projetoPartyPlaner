package relatorios;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.OrcamentoOuContrato;
import model.Pacote;
import model.Pessoa;
import util.Constantes;

public class GeradorDeRelatorio {
	public static <PdfPTable> void gerarRelatorioOrcamento(OrcamentoOuContrato orcamentoOuContrato, boolean todos,
			boolean nome, boolean email, boolean data, boolean tamanho, boolean valor, boolean fornecedoresPacotes){

		boolean verificaSeEntrou = false;

		Document doc = new Document(PageSize.A4);
		
		FileOutputStream pdf = null;

		try {
			pdf = new FileOutputStream(Constantes.NOME_PDF);
			PdfWriter.getInstance(doc, pdf);

			doc.open();

			Paragraph p = new Paragraph("RELATÓRIO");
			p.setAlignment(p.ALIGN_CENTER);
			doc.add(p);

			p = new Paragraph(" ");
			doc.add(p);

			p = new Paragraph(" ");
			doc.add(p);

			p = new Paragraph(" ");
			doc.add(p);

			if (todos) {
				verificaSeEntrou = true;
				p = new Paragraph("•Evento:" + orcamentoOuContrato.getNomeDoEvento());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);
				p = new Paragraph("•Cliente: " + orcamentoOuContrato.getClienteAssociado().getNome());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);
				p = new Paragraph("•Email do Cliente: " + orcamentoOuContrato.getClienteAssociado().getEmail());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);
				p = new Paragraph("•Data do Evento: " + orcamentoOuContrato.getDataEHoraDoEvento());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);
				p = new Paragraph("•Local do evento: " + orcamentoOuContrato.getLocalDoEvento());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);
				p = new Paragraph("•Tamanho do evento: " + orcamentoOuContrato.getTamanho());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);
				p = new Paragraph("•Valor do evento: " + orcamentoOuContrato.getValor());
				doc.add(p);
				p = new Paragraph(" ");
				doc.add(p);

				if (!orcamentoOuContrato.getFornecedores().isEmpty()) {
					p = new Paragraph("•Fornecedores Do Evento: ");
					doc.add(p);
					for (Pessoa fornecedor : orcamentoOuContrato.getFornecedores()) {
						p = new Paragraph("-" + fornecedor.getNome());
						doc.add(p);
					}
				} else {
					p = new Paragraph("•Pacotes Do Evento: ");
					doc.add(p);
					for (Pacote pacotes : orcamentoOuContrato.getPacotesDeFornecedores()) {
						int cont = 0;
						p = new Paragraph("Nome do pacote: " + pacotes.getNomeDoPacote());
						doc.add(p);
						p = new Paragraph(" ");
						doc.add(p);
						if (cont == 0) {
							p = new Paragraph("•Fornecedores do Pacote: ");
							doc.add(p);
						}
						cont++;
						for (Pessoa fornecedores : pacotes.getTodosFornecedore()) {
							p = new Paragraph("-" + fornecedores.getNome());
							doc.add(p);
						}
					}

				}
			} else {
				if (nome) {
					p = new Paragraph("•Evento:" + orcamentoOuContrato.getNomeDoEvento());
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);
					verificaSeEntrou = true;
				}

				if (email) {
					p = new Paragraph("•Email do Cliente: " + orcamentoOuContrato.getClienteAssociado().getEmail());
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);
					verificaSeEntrou = true;
				}

				if (data) {
					p = new Paragraph("•Data do Evento: " + orcamentoOuContrato.getDataEHoraDoEvento());
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);
					verificaSeEntrou = true;
				}

				if (tamanho) {
					p = new Paragraph("•Tamanho do evento: " + orcamentoOuContrato.getTamanho());
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);
					verificaSeEntrou = true;
				}

				if (valor) {
					p = new Paragraph("•Valor do evento: " + orcamentoOuContrato.getValor());
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);
					verificaSeEntrou = true;
				}

				if (fornecedoresPacotes) {
					verificaSeEntrou = true;
					if (!orcamentoOuContrato.getFornecedores().isEmpty()) {
						p = new Paragraph("•Fornecedores Do Evento: ");
						doc.add(p);
						for (Pessoa fornecedor : orcamentoOuContrato.getFornecedores()) {
							p = new Paragraph("-" + fornecedor.getNome());
							doc.add(p);
						}
					} else {
						p = new Paragraph("•Pacotes Do Evento: ");
						doc.add(p);
						for (Pacote pacotes : orcamentoOuContrato.getPacotesDeFornecedores()) {
							int cont = 0;
							p = new Paragraph("Nome do pacote: " + pacotes.getNomeDoPacote());
							doc.add(p);
							if (cont == 0) {
								p = new Paragraph("Fornecedores do Pacote: ");
								doc.add(p);
							}
							cont++;
							for (Pessoa fornecedores : pacotes.getTodosFornecedore()) {
								p = new Paragraph("-" + fornecedores.getNome());
								doc.add(p);
							}
						}

					}

				}
				if (!verificaSeEntrou) {
					p = new Paragraph("Não consta");
					doc.add(p);
				}
			}

			doc.close();

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

}
