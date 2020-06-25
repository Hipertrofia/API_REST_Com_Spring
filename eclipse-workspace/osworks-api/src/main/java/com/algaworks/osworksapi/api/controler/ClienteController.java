package com.algaworks.osworksapi.api.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworksapi.domain.model.Cliente;
import com.algaworks.osworksapi.domain.repository.ClienteRepository;

@RestController
public class ClienteController {

	
	@Autowired
	private ClienteRepository clientRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/clientes/{clienteId}")
	public Cliente buscar(@PathVariable long clienteId) {
		Optional<Cliente> cliente = clientRepository.findById(clienteId);
		
		return cliente.orElse(null); 
	}

}
