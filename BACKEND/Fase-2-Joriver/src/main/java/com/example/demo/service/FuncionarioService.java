package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FuncionarioDao;
import com.example.demo.modelo.CIDADE;
import com.example.demo.modelo.FUNCIONARIO;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
	
	@Autowired
    private FuncionarioDao funcionarioDao;
    

    // Criar ou atualizar um funcionario
    public FUNCIONARIO save(FUNCIONARIO f) {
        return funcionarioDao.save(f);
    }

    // Obter um funcionário por ID
    public Optional<FUNCIONARIO> findById(Long id) {
        return funcionarioDao.findById(id);
    }
    
    // Obter um funcionario por nome
    public Optional<FUNCIONARIO> findByNomeFunc(String nomeFunc) {
        return funcionarioDao.findByNomeFunc(nomeFunc);
    }

    // Obter todos os funcionários
    public List<FUNCIONARIO> findAll() {
        return (List<FUNCIONARIO>) funcionarioDao.findAll();
    }

    // Deletar uma cidade
    public void delete(Long id) {
    	funcionarioDao.deleteById(id);
    }
    
    //Pesquisa funcionário pela cidade
    public List<FUNCIONARIO> pesquisarPorCidadeId(Long idCidade) {
        return funcionarioDao.findByCidadeId(idCidade);
    }
    
    // Método para buscar funcionário pelo nome e id da cidade
    public Optional<FUNCIONARIO> buscarFuncionarioPorNomeECidade(String nomeFunc, Long cidadeId) {
        return funcionarioDao.findByNomeFuncIgnoreCaseAndCidade_Id(nomeFunc, cidadeId);
    }
}
