package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.CLIENTE;


@Repository
public interface ClienteDao extends CrudRepository<CLIENTE, Long> {

	Optional<CLIENTE> findByNomeCli(String nome);
    
}
