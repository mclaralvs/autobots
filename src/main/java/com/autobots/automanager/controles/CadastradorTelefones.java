package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.ClienteAtualizador;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
public class CadastradorTelefones {

	@Autowired
	private ClienteRepositorio repositorio;

	@PutMapping("/inserir-telefone") // só pra atualizacao
	public void cadastrarTelefone(@RequestBody Cliente atualizacao) {
		Cliente alvo = repositorio.getById(atualizacao.getId());
		alvo.getTelefones().addAll(atualizacao.getTelefones());
		repositorio.save(alvo);
	}
	
	@PutMapping("/atualizar-telefone")
	public void atualizarTelefone(@RequestBody Cliente atualizacao) {
		Cliente alvo = repositorio.getById(atualizacao.getId());
		
		List<Telefone> telefones =  alvo.getTelefones();
		
		for (Telefone telefone: telefones) {
			if (telefone.getId() == )
			TelefoneAtualizador atualizador = new TelefoneAtualizador();
			atualizador.atualizar(telefone, atualizarTelefone(atualizacao));
		}
		
		
		repositorio.save(alvo);
	}
	
	@DeleteMapping("/excluir-telefone")
	public void excluirDocumento(@RequestBody Cliente exclusao) {
		Cliente alvo = repositorio.getById(exclusao.getId()); // informar o id por json
		
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