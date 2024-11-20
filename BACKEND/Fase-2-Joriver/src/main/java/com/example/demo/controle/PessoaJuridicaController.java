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

import com.example.demo.modelo.CLIENTE;
import com.example.demo.service.PessoaFisicaService;
import com.example.demo.service.PessoaJuridicaService;
import com.example.demo.modelo.PESSOA_JURIDICA;


@RestController
@CrossOrigin("*")
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController {
	
	@Autowired
    private PessoaJuridicaService pjService;

    // Inserir cliente
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PESSOA_JURIDICA insertPessoaJuridica(@RequestBody PESSOA_JURIDICA pj) {
        return pjService.save(pj);
    }
    

    // Atualizar cliente
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PESSOA_JURIDICA updatePessoaJuridica(@RequestBody PESSOA_JURIDICA pj) {
        return pjService.save(pj);
    }

    // Deletar cliente
    @DeleteMapping("/excluir/{id}")
    public void deletePessoaJuridica(@PathVariable Long id) {
    	pjService.delete(id);
    }

    // Pesquisar Todos os clientes
    @GetMapping("/consultaTodos")
    public List<PESSOA_JURIDICA> getAllPessoaJuridica() {
        return (List<PESSOA_JURIDICA>) pjService.findAll();
    }

    // Pesquisar cliente por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<PESSOA_JURIDICA> getPessoaJuridicaById(@PathVariable Long id) {
        return pjService.findById(id);
    }
    
    // Pesquisar cliente por CNPJ
    @GetMapping("/consultaPorCNPJ/{cnpj}")
    public Optional<PESSOA_JURIDICA> getPessoaJuridicaByCNPJ(@PathVariable String cnpj) {
        return pjService.findByCnpj(cnpj);
    }
    
    // Pesquisar cliente por nome
    @GetMapping("/consultaPorNome/{nomeCli}")
    public Optional<PESSOA_JURIDICA> getPessoaJuridicaByNomeCid(@PathVariable String nomeCli) {
        return pjService.findByNomeCli(nomeCli);
    }
}
