package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.PESSOA_JURIDICA;


@Repository
public interface PessoaJuridicaDao extends CrudRepository<PESSOA_JURIDICA, Long> {

	Optional<PESSOA_JURIDICA> findByNomeCli(String nomeCli);

	Optional<PESSOA_JURIDICA> findByCnpj(String cnpj);

}
