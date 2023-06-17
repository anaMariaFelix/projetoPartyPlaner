package baseDedados;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
/**
 * Class Persistencia é responsavel por salvar um objeto que guarda todas as informações do sistema em um arquivo xml 
 * e recupera um xml existente e o converte para um objeto
 * 
 * @author 
 * ana maria, andrey e ismael
 *
 */

public class Persistencia {
	
	private static Persistencia instance;
	
	private XStream xstream = new XStream(new DomDriver("UTF-8"));
	private File arquivo;
	
	private Persistencia() {
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.addPermission(AnyTypePermission.ANY);
	}
	
	public static Persistencia getInstance() {
		if (instance == null) {
			instance = new Persistencia();
		}
		return instance;
	}
	
/**
 * metodo que dado um nome de um arquivo verifica se ele existe, 
 * caso não exista ele cria um arquivo com o nome recebido como parametro e salva a central de informações nesse arquivo
 * @param centralDeinformacoes
 * @param nomeDoArquivo
 */
	public void salvarCentral(CentralDeInformacoes centralDeinformacoes,String nomeDoArquivo) {
		arquivo = new File(nomeDoArquivo+".xml");

		try {
			if(!arquivo.exists()) {
				arquivo.createNewFile();
			}
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        Writer writer = new OutputStreamWriter(outputStream,     StandardCharsets.UTF_8);
	        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
	        
	        xstream.toXML(centralDeinformacoes, writer);
	        String xml = outputStream.toString("UTF-8");
	        
	        OutputStream os = new FileOutputStream(arquivo);
	        PrintWriter gravar = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
			gravar.print(xml);
			gravar.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * metodo tenta recuperar um arquivo e caso ele exista ele da um cast para uma central de informção, 
	 * caso esse arquivo não exista ele retorna null
	 * @param nome faz refencia ao arquivo que eu quero recuperar 
	 * @return uma central de informações que exite com o nome que foi recebido, 
	 */
	public CentralDeInformacoes recupearCentral(String nome) {
		arquivo = new File(nome+".xml");
		try {
			if(arquivo.exists()) {
				FileInputStream converte = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xstream.fromXML(converte);
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	

	
}
