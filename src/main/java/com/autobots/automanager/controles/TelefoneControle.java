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
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
	
	@Autowired
	private TelefoneRepositorio repositorioTelefone;

	@Autowired
	private ClienteRepositorio repositorio;
	
	@GetMapping("/telefones")
	public List<Telefone> obterTelefone() {
		return repositorioTelefone.findAll();
	}
	
	@GetMapping("/telefones/{id}")
	public List<Telefone> telefonesCliente(@PathVariable Long id) {
		Cliente alvo = repositorio.getById(id);
		return alvo.getTelefones();
	}

	@PutMapping("/cadastrar/{id}") // só pra atualizacao
	public void cadastrarTelefone(@PathVariable long id, @RequestBody Cliente atualizacao) {
		Cliente alvo = repositorio.getById(id);
		alvo.getTelefones().addAll(atualizacao.getTelefones());
		repositorio.save(alvo);
	}
	
	/*@PutMapping("/atualizar")
	public void atualizarTelefone(@RequestBody Cliente atualizacao) {
		Cliente alvo = repositorio.getById(atualizacao.getId());
		
		List<Telefone> telefones =  alvo.getTelefones();
		
		for (Telefone telefone: telefones) {
			if (telefone.getId() ==  )
			TelefoneAtualizador atualizador = new TelefoneAtualizador();
			atualizador.atualizar(telefone, atualizarTelefone(atualizacao));
		}		
		repositorio.save(alvo);
	}*/
	
	@DeleteMapping("/excluir/{id}")
	public void excluirDocumento(@PathVariable long id, @RequestBody Cliente exclusao) {
		Cliente alvo = repositorio.getById(id); // informar o id por json
		
		List<Telefone> telefones =  alvo.getTelefones(); //pega todos os documentos e salva numa variável
		
		for (Telefone telefone: telefones) { // vai percorrer pela variável inteira
			if (telefone.getId() == exclusao.getTelefones().get(0).getId()) {
				alvo.getTelefones().remove(telefone);
				break;
			}
		}
		repositorio.save(alvo); // atualiza no banco
	}
}
