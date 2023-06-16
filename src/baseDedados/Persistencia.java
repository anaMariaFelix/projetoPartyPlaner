package baseDedados;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	public void salvarCentral(CentralDeInformacoes centralDeinformacoes,String nomeDoArquivo) {
		arquivo = new File(nomeDoArquivo+".xml");

		try {
			if(!arquivo.exists()) {
				arquivo.createNewFile();
			}
			//https://cursos.alura.com.br/forum/topico-duvida-sobre-encode-com-xstream-e-o-springboot-119178
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        Writer writer = new OutputStreamWriter(outputStream,     StandardCharsets.UTF_8);
	        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
	        xstream.toXML(centralDeinformacoes, writer);
	        String xml = outputStream.toString("UTF-8");
			PrintWriter gravar = new PrintWriter(arquivo);
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
