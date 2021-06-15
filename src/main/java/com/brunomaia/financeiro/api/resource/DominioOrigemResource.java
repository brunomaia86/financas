package com.brunomaia.financeiro.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunomaia.financeiro.api.event.RecursoCriadoEvent;
import com.brunomaia.financeiro.api.modelo.DominioOrigem;
import com.brunomaia.financeiro.api.repository.DominioOrigemRepository;

@RestController
@RequestMapping("/dominios_origem")
public class DominioOrigemResource {

	@Autowired
	private DominioOrigemRepository dominioOrigemRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<DominioOrigem> listar() {
		return dominioOrigemRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DominioOrigem> criar(@Valid @RequestBody DominioOrigem dominioOrigem, HttpServletResponse response) {
		DominioOrigem dominioOrigemSalvo = dominioOrigemRepository.save(dominioOrigem);

		publisher.publishEvent(new RecursoCriadoEvent(dominioOrigemSalvo, response, dominioOrigemSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(dominioOrigemSalvo);
	}

	@GetMapping("/{codigo}")
	public DominioOrigem buscarPeloCodigo(@PathVariable Long codigo) {
		return dominioOrigemRepository.findById(codigo).orElse(null);
	}

//	@GetMapping("/{codigo}")
//	public ResponseEntity<DominioOrigem> buscarPeloCodigo(@PathVariable Integer codigo) {
//		Optional<DominioOrigem> categoria = this.dominioOrigemRepository.findById(codigo);
//		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
//	}

}
