package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
public class CadastradorDocumentos {

	@Autowired
	private ClienteRepositorio repositorio;
	
	@PutMapping("/inserir-documento") // só pra atualizacao
	public void cadastrarDocumento(@RequestBody Cliente atualizacao) {
		Cliente alvo = repositorio.getById(atualizacao.getId());
		alvo.getDocumentos().addAll(atualizacao.getDocumentos());
		repositorio.save(alvo);
	}
	
	@DeleteMapping("/excluir-documento")
	public void excluirDocumento(@RequestBody Cliente exclusao) {
		Cliente alvo = repositorio.getById(exclusao.getId()); // informar o id por json
		
		List<Documento> documentos =  alvo.getDocumentos(); //pega todos os documentos e salva numa variável
		
		for (Documento documento : documentos) { // vai percorrer pela variável inteira
			if (documento.getId() == exclusao.getDocumentos().get(0).getId()) {
				alvo.getDocumentos().remove(documento);
				break;
			}
		}
		repositorio.save(alvo); // atualiza no banco
	}
	
}