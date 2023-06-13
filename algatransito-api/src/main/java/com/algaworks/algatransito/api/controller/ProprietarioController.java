package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    @GetMapping()
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){
        //estilo funcional
         return proprietarioRepository.findById(proprietarioId)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());

        // Lambda pode ser substituido por mehtod reference
        // .map(proprietario -> ResponseEntity.ok(proprietario))
        // fica: ResponseEntity::ok
        // .map(ResponseEntity::ok)

        /*
        Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);

        if(proprietario.isPresent()) {
            return ResponseEntity.ok(proprietario.get());
        }

        return ResponseEntity.notFound().build();*/
    }

}
