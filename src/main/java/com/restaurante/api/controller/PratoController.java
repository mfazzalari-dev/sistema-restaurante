package com.restaurante.api.controller;

import com.restaurante.api.model.Prato;
import com.restaurante.api.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
public class PratoController {

    @Autowired
    private PratoRepository pratoRepository;

    // Rota para listar todos os pratos (GET http://localhost:8080/api/cardapio)
    @GetMapping
    public List<Prato> listarCardapio() {
        return pratoRepository.findAll();
    }

    // Rota para cadastrar um novo prato (POST http://localhost:8080/api/cardapio)
    @PostMapping
    public Prato cadastrarPrato(@RequestBody Prato prato) {
        return pratoRepository.save(prato);
    }
}