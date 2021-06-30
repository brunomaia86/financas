package com.brunomaia.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunomaia.financeiro.api.modelo.MovimentacaoFinanceira;

public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Long> {

}
