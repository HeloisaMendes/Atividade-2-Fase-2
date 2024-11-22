package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FreteDao;
import com.example.demo.modelo.FRETE;

import java.util.List;
import java.util.Optional;

@Service
public class FreteService {
	
	@Autowired
    private FreteDao freteDao;
    

    // Criar ou atualizar uma cidade
    public FRETE save(FRETE f) {
        return freteDao.save(f);
    }

    // Obter uma cidade por ID
    public Optional<FRETE> findById(Long id) {
        return freteDao.findById(id);
    }

    // Obter todas as cidades
    public List<FRETE> findAll() {
        return (List<FRETE>) freteDao.findAll();
    }

    // Deletar uma cidade
    public void delete(Long id) {
    	freteDao.deleteById(id);
    }

}
