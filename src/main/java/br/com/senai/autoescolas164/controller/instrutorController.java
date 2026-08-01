package br.com.senai.autoescolas164.controller;

import br.com.senai.autoescolas164.instrutor.DadosInstrutor;
import br.com.senai.autoescolas164.instrutor.Instrutor;
import br.com.senai.autoescolas164.instrutor.InstrutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrutores")
public class instrutorController {
    @Autowired
    private InstrutorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarInstrutor(@RequestBody @Valid DadosInstrutor dados) {
        Instrutor instrutor = new Instrutor(dados);
        repository.save(instrutor);
    }
}
