package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Evento;

public class EventoController {

//	public boolean adicionarEvento(Evento evento) {
//		LocalDateTime dataHoraAtual = LocalDateTime.now();
//		if(!todosEvento.contains(evento) && evento.getDataHora().isAfter(dataHoraAtual)) {
//			todosEvento.add(evento);
//			return true;
//			
//		}
//		return false;
//	}
//	
//	
//	public Evento recuperarEventoPeloId(long id) {
//		for(Evento e: todosEvento) {
//			if(e.getId() == id) {
//				return e;
//			}
//		}
//		
//		return null;
//	}
//
//	
//	public ArrayList recuperarEventoDeUmCliente(String nomeDoCliente) {
//
//		if(!existeCliente(nomeDoCliente)) {
//			return null;
//		}else {
//			ArrayList<Evento> eventosDoCliente = new ArrayList();
//			for(Evento eventos: todosEvento) {
//				if(eventos.getClienteAssociado().getNome().equals(nomeDoCliente)) {
//					eventosDoCliente.add(eventos);
//				}
//			}
//			return eventosDoCliente;
//		}
//	
//	}
//	
//	public void popularTodosServicos(String[] servicos) {
//		for (String servico: servicos) {
//			todosServicos.add(servico);
//		}
//
//	}
}
