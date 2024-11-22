package com.example.demo.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.CIDADE;
import com.example.demo.modelo.FUNCIONARIO;
import com.example.demo.service.FuncionarioService;


@RestController
@CrossOrigin("*")
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
    private FuncionarioService funcionarioService;

    // Inserir funcionário
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FUNCIONARIO insertFuncionario(@RequestBody FUNCIONARIO f) {
        return funcionarioService.save(f);
    }

    // Atualizar funcionário
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FUNCIONARIO updateFuncionario(@RequestBody FUNCIONARIO f) {
        return funcionarioService.save(f);
    }

    // Deletar funcionário
    @DeleteMapping("/excluir/{id}")
    public void deleteFuncionario(@PathVariable Long id) {
    	funcionarioService.delete(id);
    }

    // Pesquisar Todos os funcionário
    @GetMapping("/consultaTodos")
    public List<FUNCIONARIO> getAllFuncionario() {
        return (List<FUNCIONARIO>) funcionarioService.findAll();
    }

    // Pesquisar funcionário por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<FUNCIONARIO> getFuncionarioById(@PathVariable Long id) {
        return funcionarioService.findById(id);
    }
    
    // Pesquisar funcionário por nome
    @GetMapping("/consultaPorNome/{nomeCid}")
    public Optional<FUNCIONARIO> getFuncionarioByNomeFunc(@PathVariable String nomeFunc) {
        return funcionarioService.findByNomeFunc(nomeFunc);
    }
    
    // Pesquisar funcionário pelo id da cidade
    @GetMapping("/cidade/{cidadeId}")
    public ResponseEntity<List<FUNCIONARIO>> pesquisarPorCidadeId(@PathVariable Long cidadeId) {
        List<FUNCIONARIO> funcionarios = funcionarioService.pesquisarPorCidadeId(cidadeId);
        return ResponseEntity.ok(funcionarios);
    }
    
    
    // Pesquisar funcionário pelo nome
    @GetMapping("/cidade/{cidadeId}/nome/{nomeFunc}")
    public Optional<FUNCIONARIO> buscarFuncionario(@PathVariable Long cidadeId, @PathVariable String nomeFunc) {
        return funcionarioService.buscarFuncionarioPorNomeECidade(nomeFunc, cidadeId);
    }
}
