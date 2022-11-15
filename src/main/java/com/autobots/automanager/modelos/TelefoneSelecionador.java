package com.autobots.automanager.modelos;

import java.util.List;

import com.autobots.automanager.entidades.Telefone;

public class TelefoneSelecionador {
	
	public Telefone selecionar(List<Telefone> telefones, long id) {
		Telefone selecionado = null;
		for (Telefone telefone : telefones) {
			if (telefone.getId() == id) {
				selecionado = telefone;
			}
		}
		return selecionado;
	}
	
}