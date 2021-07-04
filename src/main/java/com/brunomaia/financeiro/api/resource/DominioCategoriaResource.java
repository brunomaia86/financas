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
import com.brunomaia.financeiro.api.modelo.DominioCategoria;
import com.brunomaia.financeiro.api.repository.DominioCategoriaRepository;

@RestController
@RequestMapping("/dominios_categoria")
public class DominioCategoriaResource {

	@Autowired
	private DominioCategoriaRepository dominioCategoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<DominioCategoria> listar() {
		return dominioCategoriaRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DominioCategoria> criar(@Valid @RequestBody DominioCategoria dominioCategoria, HttpServletResponse response) {
		DominioCategoria dominioCategoriaSalvo = dominioCategoriaRepository.save(dominioCategoria);

		publisher.publishEvent(new RecursoCriadoEvent(dominioCategoriaSalvo, response, dominioCategoriaSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(dominioCategoriaSalvo);
	}

	@GetMapping("/{codigo}")
	public DominioCategoria buscarPeloCodigo(@PathVariable Long codigo) {
		return dominioCategoriaRepository.findById(codigo).orElse(null);
	}

//	@GetMapping("/{codigo}")
//	public ResponseEntity<DominioOrigem> buscarPeloCodigo(@PathVariable Integer codigo) {
//		Optional<DominioOrigem> categoria = this.dominioOrigemRepository.findById(codigo);
//		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
//	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		dominioCategoriaRepository.deleteById(codigo);
	}
	
	@PutMapping("/codigo")
	public ResponseEntity<DominioCategoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody DominioCategoria dominioParam){
		DominioCategoria dominio = this.dominioCategoriaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
//		if(dominioOrigem == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
		
		BeanUtils.copyProperties(dominioParam, dominio, "codigo");
		
		this.dominioCategoriaRepository.save(dominio);
		return ResponseEntity.ok(dominio);
	}
	
}
