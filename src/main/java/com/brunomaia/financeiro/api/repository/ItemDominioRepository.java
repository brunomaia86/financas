package com.brunomaia.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunomaia.financeiro.api.modelo.ItemDominio;

public interface ItemDominioRepository extends JpaRepository<ItemDominio, Long> {

}
