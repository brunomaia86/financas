package com.brunomaia.financeiro.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunomaia.financeiro.api.repository.ItemDominioRepository;

@RestController
@RequestMapping("/dominios")
public class ItemDominioResource {

	@Autowired
	private ItemDominioRepository itemDominioRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
}
