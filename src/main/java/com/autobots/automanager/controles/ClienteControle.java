package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.ClienteAtualizador;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {
	
	@Autowired
	private ClienteRepositorio repositorio;
	
	@Autowired
	private ClienteSelecionador selecionador;
	
	@GetMapping("/clientes")
	public List<Cliente> obterClientes() {
		List<Cliente> clientes = repositorio.findAll();
		return clientes;
	}

	@GetMapping("/cliente/{id}")
	public Cliente obterCliente(@PathVariable long id) {
		List<Cliente> clientes = repositorio.findAll();
		return selecionador.selecionar(clientes, id);
	}

	@PostMapping("/cadastro")
	public void cadastrarCliente(@RequestBody Cliente cliente) {
		repositorio.save(cliente);
	}

	@PutMapping("/atualizar/{id}")
	public void atualizarCliente(@PathVariable long id, @RequestBody Cliente atualizacao) {
		Cliente cliente = repositorio.getById(id);
		ClienteAtualizador atualizador = new ClienteAtualizador();
		atualizador.atualizar(cliente, atualizacao);
		repositorio.save(cliente);
	}

	@DeleteMapping("/excluir/{id}")
	public void excluirCliente(@PathVariable long id) {
		Cliente cliente = repositorio.getById(id);
		repositorio.delete(cliente);
	}
	
}