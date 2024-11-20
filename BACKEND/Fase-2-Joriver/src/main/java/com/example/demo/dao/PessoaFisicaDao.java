package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.PESSOA_FISICA;


@Repository
public interface PessoaFisicaDao extends CrudRepository<PESSOA_FISICA, Long> {

	Optional<PESSOA_FISICA> findByNomeCli(String nomeCli);

	Optional<PESSOA_FISICA> findByCpf(String cpf);

}
