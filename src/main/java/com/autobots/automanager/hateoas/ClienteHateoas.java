package com.autobots.automanager.hateoas;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ClienteControle;
import com.autobots.automanager.entidades.Cliente;

@Component
public class ClienteHateoas implements Hateoas<Cliente> {

	@Override
	public void AdicionarLink(List<Cliente> link) {
		for (Cliente cliente : link ) {
			long Id = cliente.getId();
			Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterCliente(Id)
		).withSelfRel();
			cliente.add(linkProprio);
		}	
	}

	@Override
	public void AdicionarLink(Cliente link) {
		Link linkTodos = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterClientes()).withRel("clientes");
		link.add(linkTodos);
	}
}
