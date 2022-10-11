package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
	
	@Autowired
	private EnderecoRepositorio repositorioEndereco;

	@Autowired
	private ClienteRepositorio repositorio;
	
	@GetMapping("/enderecos")
	public List<Endereco> obterEndereco() {
		return repositorioEndereco.findAll();
	}
	
	@GetMapping("/enderecos/{id}")
	public Endereco enderecosCliente(@PathVariable long id) {
		return repositorioEndereco.findById(id).get();
	}
	
	@PutMapping("/cadastrar/{id}") // só pra atualizacao
	public void cadastrarEndereco(@PathVariable long id, @RequestBody Endereco atualizacao) {
		Endereco alvo = repositorioEndereco.getById(atualizacao.getId());
		
		EnderecoAtualizador atualizador = new EnderecoAtualizador();
		
		atualizador.atualizar(alvo, atualizacao);
		
		repositorioEndereco.save(alvo);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluirEndereco(@PathVariable long id, @RequestBody Cliente exclusao) {
		Cliente alvo = repositorio.getById(id); 
		
		repositorioEndereco.delete(alvo.getEndereco());
		
		alvo.setEndereco(null);
		
		repositorio.save(alvo); // atualiza no banco
	}
}
