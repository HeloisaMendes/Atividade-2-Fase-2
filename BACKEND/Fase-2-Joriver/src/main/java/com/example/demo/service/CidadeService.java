package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CidadeDao;
import com.example.demo.modelo.CIDADE;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
	
	@Autowired
    private CidadeDao cidadeDao;
    

    // Criar ou atualizar uma cidade
    public CIDADE save(CIDADE cidade) {
        return cidadeDao.save(cidade);
    }

    // Obter uma cidade por ID
    public Optional<CIDADE> findById(Long id) {
        return cidadeDao.findById(id);
    }
    
    // Obter uma cidade por nome
    public Optional<CIDADE> findByNomeCid(String cidade) {
        return cidadeDao.findByNomeCid(cidade);
    }

    // Obter todas as cidades
    public List<CIDADE> findAll() {
        return (List<CIDADE>) cidadeDao.findAll();
    }

    // Deletar uma cidade
    public void delete(Long id) {
        cidadeDao.deleteById(id);
    }
    
    //Pesquisa cidade pelo estado
    public List<CIDADE> findByEstadoId(Long idEstado) {
        return cidadeDao.findByEstadoId(idEstado);
    }
    
    public Optional<CIDADE> buscarCidade (Long idEstado, String nomeCid){
    	return cidadeDao.findByEstadoIdAndNomeCid(idEstado, nomeCid);
    }
    
}
