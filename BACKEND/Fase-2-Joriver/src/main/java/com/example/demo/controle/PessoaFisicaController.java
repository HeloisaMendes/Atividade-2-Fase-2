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
import com.example.demo.modelo.PESSOA_FISICA;


@RestController
@CrossOrigin("*")
@RequestMapping("/pessoaFisica")
public class PessoaFisicaController {
	
	@Autowired
    private PessoaFisicaService pfService;

    // Inserir cliente
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PESSOA_FISICA insertPessoaFisica(@RequestBody PESSOA_FISICA pf) {
        return pfService.save(pf);
    }
    

    // Atualizar cliente
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PESSOA_FISICA updatePessoaFisica(@RequestBody PESSOA_FISICA pf) {
        return pfService.save(pf);
    }

    // Deletar cliente
    @DeleteMapping("/excluir/{id}")
    public void deletePessoa_Fisica(@PathVariable Long id) {
       pfService.delete(id);
    }

    // Pesquisar Todos os clientes
    @GetMapping("/consultaTodos")
    public List<PESSOA_FISICA> getAllPessoaFisica() {
        return (List<PESSOA_FISICA>) pfService.findAll();
    }

    // Pesquisar cliente por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<PESSOA_FISICA> getPessoaFisicaById(@PathVariable Long id) {
        return pfService.findById(id);
    }
    
    // Pesquisar cliente por CPF
    @GetMapping("/consultaPorCPF/{cpf}")
    public Optional<PESSOA_FISICA> getPessoaFisicaByCPF(@PathVariable String cpf) {
        return pfService.findByCpf(cpf);
    }
    
    // Pesquisar cliente por nome
    @GetMapping("/consultaPorNome/{nomeCli}")
    public Optional<PESSOA_FISICA> getPessoaFisicaByNomeCid(@PathVariable String nomeCli) {
        return pfService.findByNomeCli(nomeCli);
    }
}
