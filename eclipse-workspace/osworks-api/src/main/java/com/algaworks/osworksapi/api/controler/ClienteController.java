package com.algaworks.osworksapi.api.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public ResponseEntity<Cliente> buscar(@PathVariable long clienteId) {
		Optional<Cliente> cliente = clientRepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar (@RequestBody Cliente cliente) {
		return clientRepository.save(cliente);
	}
	
	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		
		if(!clientRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clientRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
}
