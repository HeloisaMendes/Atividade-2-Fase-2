package com.example.demo.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.demo.service.CidadeService;


@RestController
@CrossOrigin("*")
@RequestMapping("/cidade")
public class CidadeController {
	
	@Autowired
    private CidadeService cidadeService;

    // Inserir cidade
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CIDADE insertCidade(@RequestBody CIDADE cidade) {
        return cidadeService.save(cidade);
    }

    // Atualizar cidade
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CIDADE updateCidade(@RequestBody CIDADE cidade) {
        return cidadeService.save(cidade);
    }

    // Deletar cidade
    @DeleteMapping("/excluir/{id}")
    public void deleteCidade(@PathVariable Long id) {
        cidadeService.delete(id);
    }

    // Pesquisar Todas as cidades
    @GetMapping("/consultaTodos")
    public List<CIDADE> getAllCidade() {
        return (List<CIDADE>) cidadeService.findAll();
    }

    // Pesquisar cidade por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<CIDADE> getCidadeById(@PathVariable Long id) {
        return cidadeService.findById(id);
    }
    
    // Pesquisar cidade por nome
    @GetMapping("/consultaPorNome/{nomeCid}")
    public Optional<CIDADE> getCidadeByNomeCid(@PathVariable String nomeCid) {
        return cidadeService.findByNomeCid(nomeCid);
    }
    
    // Pesquisar cidade pelo estado
    @GetMapping("/consultaPorEstado/{idEstado}")
    public List<CIDADE> getCidadesByEstado(@PathVariable Long idEstado) {
        return cidadeService.findByEstadoId(idEstado);
    }
    
    @GetMapping("/estado/{idEstado}/cidade/{nomeCid}") 
    public Optional<CIDADE> buscarCidade(@PathVariable Long idEstado, @PathVariable String nomeCid) {
    	return cidadeService.buscarCidade(idEstado, nomeCid);
    }
}
