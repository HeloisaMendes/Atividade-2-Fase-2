package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClienteDao;
import com.example.demo.dao.PessoaFisicaDao;
import com.example.demo.modelo.CLIENTE;
import com.example.demo.modelo.PESSOA_FISICA;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
    private ClienteDao clienteDao;
	
	@Autowired
	private PessoaFisicaDao pfDao;
    

    // Criar ou atualizar um cliente
    public CLIENTE save(CLIENTE cliente) {
    	clienteDao.save(cliente);
    	
    	// Agora associa o cliente à pessoa física e salva na tabela Pessoa_Fisica
    	pfDao.setId(cliente.getId());
    	return pfDao.save(PESSOA_FISICA); 
    }

    // Obter um cliente por ID
    public Optional<CLIENTE> findById(Long id) {
        return clienteDao.findById(id);
    }
    
    // Obter um cliente por nome
    public Optional<CLIENTE> findByNomeCli(String nome) {
        return clienteDao.findByNomeCli(nome);
    }

    // Obter todos os clientes
    public List<CLIENTE> findAll() {
        return (List<CLIENTE>) clienteDao.findAll();
    }

    // Deletar uma cidade
    public void delete(Long id) {
    	clienteDao.deleteById(id);
    } 
}
