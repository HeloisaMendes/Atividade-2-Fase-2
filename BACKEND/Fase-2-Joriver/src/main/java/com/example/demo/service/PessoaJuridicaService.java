package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClienteDao;
import com.example.demo.dao.PessoaFisicaDao;
import com.example.demo.dao.PessoaJuridicaDao;
import com.example.demo.modelo.CLIENTE;
import com.example.demo.modelo.PESSOA_JURIDICA;


import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService {
		
	@Autowired
	private PessoaJuridicaDao pjDao;
    

    // Criar ou atualizar uma pessoa jurídica
    public PESSOA_JURIDICA save(PESSOA_JURIDICA pj) {
    	return pjDao.save(pj); 
    }
	
    // Obter um cliente por ID
    public Optional<PESSOA_JURIDICA> findById(Long id) {
        return pjDao.findById(id);
    }
    
    // Obter um cliente por CNPJ
    public Optional<PESSOA_JURIDICA> findByCnpj(String cnpj) {
        return pjDao.findByCnpj(cnpj);
    }
    
    // Obter um cliente por nome
    public Optional<PESSOA_JURIDICA> findByNomeCli(String nomeCli) {
        return pjDao.findByNomeCli(nomeCli);
    }
	
    // Obter todos os clientes
    public List<PESSOA_JURIDICA> findAll() {
        return (List<PESSOA_JURIDICA>) pjDao.findAll();
    }

    // Deletar um cliente
    public void delete(Long id) {
    	pjDao.deleteById(id);
    } 
}
