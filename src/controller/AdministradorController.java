package controller;

import baseDedados.CentralDeInformacoes;
import model.Pessoa;

public class AdministradorController {

	private static AdministradorController instance;

	private AdministradorController() {

	}

	public static AdministradorController getInstance() {
		if (instance == null) {
			instance = new AdministradorController();
		}
		return instance;
	}
	
	
	public Pessoa obterAdministrador() {
		return CentralDeInformacoes.getInstance().getAdministrador();
	}
	
	
}
