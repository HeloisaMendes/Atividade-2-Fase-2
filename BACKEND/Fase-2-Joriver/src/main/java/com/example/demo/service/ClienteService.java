package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClienteDao;
import com.example.demo.modelo.CLIENTE;
import com.example.demo.modelo.PESSOA_FISICA;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
    private ClienteDao clienteDao;
	

    // Criar ou atualizar um cliente
    public CLIENTE save(CLIENTE cliente) {
    	return clienteDao.save(cliente);
    }

    // Obter um cliente por ID
    public Optional<CLIENTE> findById(Long id) {
        return clienteDao.findById(id);
    }
    
    // Obter um cliente por nome
    /*public Optional<CLIENTE> findByNomeCli(String nome) {
        return clienteDao.findByNomeCli(nome);
    }
    */

    // Obter todos os clientes
    public List<CLIENTE> findAll() {
        return (List<CLIENTE>) clienteDao.findAll();
    }

    // Deletar uma cidade
    public void delete(Long id) {
    	clienteDao.deleteById(id);
    } 
}
