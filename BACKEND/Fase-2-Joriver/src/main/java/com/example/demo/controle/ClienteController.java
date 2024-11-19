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
import com.example.demo.service.ClienteService;


@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
    private ClienteService clienteService;

    // Inserir cliente
    @PostMapping(path = "/inserir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CLIENTE insertCliente(@RequestBody CLIENTE cliente) {
        return clienteService.save(cliente);
    }

    // Atualizar cliente
    @PutMapping(path = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CLIENTE updateCliente(@RequestBody CLIENTE cliente) {
        return clienteService.save(cliente);
    }

    // Deletar cidade
    @DeleteMapping("/excluir/{id}")
    public void deleteCidade(@PathVariable Long id) {
        clienteService.delete(id);
    }

    // Pesquisar Todos os clientes
    @GetMapping("/consultaTodos")
    public List<CLIENTE> getAllCliente() {
        return (List<CLIENTE>) clienteService.findAll();
    }

    // Pesquisar cliente por id
    @GetMapping("/consultaPorId/{id}")
    public Optional<CLIENTE> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id);
    }
    
    // Pesquisar cliente por nome
    @GetMapping("/consultaPorNome/{nomeCli}")
    public Optional<CLIENTE> getClienteByNomeCid(@PathVariable String nomeCli) {
        return clienteService.findByNomeCli(nomeCli);
    }
}
