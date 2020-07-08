package com.algaworks.osworksapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworksapi.domain.exception.NegocioException;
import com.algaworks.osworksapi.domain.model.Cliente;
import com.algaworks.osworksapi.domain.model.OrdemServico;
import com.algaworks.osworksapi.domain.model.StatusOrdemServico;
import com.algaworks.osworksapi.domain.repository.ClienteRepository;
import com.algaworks.osworksapi.domain.repository.OrdemServicoRepository;


@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRpository;
	
	public OrdemServico criar (OrdemServico ordemServico) {
		
		Cliente cliente = clienteRpository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
}
