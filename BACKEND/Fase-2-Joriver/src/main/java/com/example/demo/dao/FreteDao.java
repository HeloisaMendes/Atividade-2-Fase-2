package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.FRETE;


@Repository
public interface FreteDao extends CrudRepository<FRETE, Long> {

	
}
