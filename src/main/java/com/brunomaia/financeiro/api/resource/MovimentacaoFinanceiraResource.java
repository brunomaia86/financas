package com.brunomaia.financeiro.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunomaia.financeiro.api.event.RecursoCriadoEvent;
import com.brunomaia.financeiro.api.modelo.MovimentacaoFinanceira;
import com.brunomaia.financeiro.api.repository.MovimentacaoFinanceiraRepository;

@RestController
@RequestMapping("/movimentacao_financeira")
public class MovimentacaoFinanceiraResource {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;

	@GetMapping
	public List<MovimentacaoFinanceira> listar() {
		return movimentacaoFinanceiraRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MovimentacaoFinanceira> criar(@Valid @RequestBody MovimentacaoFinanceira movimentacaoFinanceira, HttpServletResponse response) {
		MovimentacaoFinanceira movimentacaoFinanceiraSalva = movimentacaoFinanceiraRepository.save(movimentacaoFinanceira);

		publisher.publishEvent(new RecursoCriadoEvent(movimentacaoFinanceiraSalva, response, movimentacaoFinanceiraSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoFinanceiraSalva);
	}
	
	@GetMapping("/{codigo}")
	public MovimentacaoFinanceira buscarPeloCodigo(@PathVariable Long codigo) {
		return movimentacaoFinanceiraRepository.findById(codigo).orElse(null);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		movimentacaoFinanceiraRepository.deleteById(codigo);
	}
	
	@PutMapping("/codigo")
	public ResponseEntity<MovimentacaoFinanceira> atualizar(@PathVariable Long codigo, @Valid @RequestBody MovimentacaoFinanceira movimentacaoParam){
		MovimentacaoFinanceira movimentacao = this.movimentacaoFinanceiraRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
//		if(dominioOrigem == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
		
		BeanUtils.copyProperties(movimentacaoParam, movimentacao, "codigo");
		
		this.movimentacaoFinanceiraRepository.save(movimentacao);
		return ResponseEntity.ok(movimentacao);
	}
	
}
