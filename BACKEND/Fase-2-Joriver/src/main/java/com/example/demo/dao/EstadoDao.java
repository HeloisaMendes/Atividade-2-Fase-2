package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.ESTADO;


@Repository
public interface EstadoDao extends CrudRepository<ESTADO, Long> {

	Optional<ESTADO> findByUf(String cpf);
    // Métodos adicionais, se necessário, podem ser definidos aqui
}
