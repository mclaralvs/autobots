package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
public class TelefoneControle {

	@Autowired
	private TelefoneRepositorio repositorio;
	
	@GetMapping("/telefone")
	public List<Telefone> docs() {
		return repositorio.findAll();
	}
	
}