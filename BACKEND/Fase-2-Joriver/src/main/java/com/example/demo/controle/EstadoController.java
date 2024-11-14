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

import com.example.demo.modelo.ESTADO;
import com.example.demo.service.EstadoService;


@RestController
@CrossOrigin("*")
@RequestMapping("/estado")
public class EstadoController {
	
	@Autowired
    private EstadoService estadoService;

    // Inserir estado
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ESTADO insertEstado(@RequestBody ESTADO estado) {
        return estadoService.save(estado);
    }

    // Atualizar estado
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ESTADO updateEstado(@RequestBody ESTADO estado) {
        return estadoService.save(estado);
    }

    // Deletar estado
    @DeleteMapping("/excluir/{id}")
    public void deleteEstado(@PathVariable Long id) {
        estadoService.delete(id);
    }

    // Pesquisar Todos os estados
    @GetMapping("/consultaTodos")
    public List<ESTADO> getAllEstado() {
        return (List<ESTADO>) estadoService.findAll();
    }

    // Pesquisar estado por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<ESTADO> getEstadoById(@PathVariable Long id) {
        return estadoService.findById(id);
    }
    
    // Pesquisar estado por uf
    @GetMapping("/consultaPorUf/{uf}")
    public Optional<ESTADO> getEstadoByUf(@PathVariable String uf) {
        return estadoService.findByUf(uf);
    }
}
