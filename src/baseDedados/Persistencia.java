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
	 * Para gravar o arquivo no UTF-8 foi consultado os seguintes sites
	 * https://cursos.alura.com.br/forum/topico-duvida-sobre-encode-com-xstream-e-o-springboot-119178
	 * https://stackoverflow.com/questions/21096367/how-to-make-the-printwriter-to-write-utf-8
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
