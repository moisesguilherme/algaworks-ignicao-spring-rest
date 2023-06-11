package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {


    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        List<Proprietario> proprietarios = new ArrayList<>();
        var proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("João");
        proprietario1.setEmail("joaodascouves@gmail.com");
        proprietario1.setTelefone("3488898303");

        var proprietario2  = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("Maria");
        proprietario2.setEmail("maria@gmail.com");
        proprietario2.setTelefone("343331303");

        return Arrays.asList(proprietario1, proprietario2);
    }

}
