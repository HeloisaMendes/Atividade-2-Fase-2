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
public class PessoaFisicaService {
		
	@Autowired
	private PessoaFisicaDao pfDao;
    

    // Criar ou atualizar uma pessoa física
    public PESSOA_FISICA save(PESSOA_FISICA pf) {
    	return pfDao.save(pf); 
    }
	
    // Obter um cliente por ID
    public Optional<PESSOA_FISICA> findById(Long id) {
        return pfDao.findById(id);
    }
    
    // Obter um cliente por CPF
    public Optional<PESSOA_FISICA> findByCpf(String cpf) {
        return pfDao.findByCpf(cpf);
    }
    
    // Obter um cliente por nome
    public Optional<PESSOA_FISICA> findByNomeCli(String nomeCli) {
        return pfDao.findByNomeCli(nomeCli);
    }
	
    // Obter todos os clientes
    public List<PESSOA_FISICA> findAll() {
        return (List<PESSOA_FISICA>) pfDao.findAll();
    }

    // Deletar um cliente
    public void delete(Long id) {
    	pfDao.deleteById(id);
    } 
}
