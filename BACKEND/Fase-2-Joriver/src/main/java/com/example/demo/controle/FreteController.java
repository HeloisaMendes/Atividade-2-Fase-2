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

import com.example.demo.modelo.FRETE;
import com.example.demo.service.FreteService;


@RestController
@CrossOrigin("*")
@RequestMapping("/frete")
public class FreteController {
	
	@Autowired
    private FreteService freteService;

    // Inserir cidade
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FRETE insertFrete(@RequestBody FRETE f) {
        return freteService.save(f);
    }

    // Atualizar cidade
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FRETE updateFrete(@RequestBody FRETE f) {
        return freteService.save(f);
    }

    // Deletar cidade
    @DeleteMapping("/excluir/{id}")
    public void deleteFrete(@PathVariable Long id) {
    	freteService.delete(id);
    }

    // Pesquisar Todas as cidades
    @GetMapping("/consultaTodos")
    public List<FRETE> getAllFrete() {
        return (List<FRETE>) freteService.findAll();
    }

    // Pesquisar cidade por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<FRETE> getFreteById(@PathVariable Long id) {
        return freteService.findById(id);
    }
}
