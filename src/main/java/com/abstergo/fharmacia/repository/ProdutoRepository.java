package com.abstergo.fharmacia.repository;

import com.abstergo.fharmacia.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
