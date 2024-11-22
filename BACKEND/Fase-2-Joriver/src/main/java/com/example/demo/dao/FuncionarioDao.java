package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.modelo.CIDADE;
import com.example.demo.modelo.FUNCIONARIO;

public interface FuncionarioDao extends CrudRepository<FUNCIONARIO, Long>{
	
	Optional<FUNCIONARIO> findByNomeFunc(String nomeFunc);
	
	//Pesquisa cidade pelo estado	
	Optional<FUNCIONARIO> findByNomeFuncIgnoreCaseAndCidade_Id(String nomeFunc, Long cidadeId);
	
    List<FUNCIONARIO> findByCidadeId(Long idCidade); // Método para buscar funcionários pelo ID da cidade
    
}
