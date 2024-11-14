package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EstadoDao;
import com.example.demo.modelo.ESTADO;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
	
	@Autowired
    private EstadoDao estadoDao;
    

    // Criar ou atualizar um estado
    public ESTADO save(ESTADO estado) {
        return estadoDao.save(estado);
    }

    // Obter um estado por ID
    public Optional<ESTADO> findById(Long id) {
        return estadoDao.findById(id);
    }
    
    // Obter um estado por uf
    public Optional<ESTADO> findByUf(String uf) {
        return estadoDao.findByUf(uf);
    }

    // Obter todos os estados
    public List<ESTADO> findAll() {
        return (List<ESTADO>) estadoDao.findAll();
    }

    // Deletar um estado
    public void delete(Long id) {
        estadoDao.deleteById(id);
    }
    
}
