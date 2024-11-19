package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.CIDADE;


@Repository
public interface CidadeDao extends CrudRepository<CIDADE, Long> {

	Optional<CIDADE> findByNomeCid(String nome);
    
	//Pesquisa cidade pelo estado	
	Optional<CIDADE> findByEstadoIdAndNomeCid(Long idEstado, String nomeCid);
}
