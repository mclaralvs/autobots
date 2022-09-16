package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
public class DocumentoControle {

	// primeiro vem o tipo da variável
	@Autowired
	private DocumentoRepositorio repositorio;
	
	@GetMapping("/documentos")
	public List<Documento> docs() {
		return repositorio.findAll();
	}

}