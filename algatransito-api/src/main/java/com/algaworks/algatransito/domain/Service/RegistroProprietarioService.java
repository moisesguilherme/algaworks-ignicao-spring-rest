package com.algaworks.algatransito.domain.Service;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component // Injetar no controller
@AllArgsConstructor
@Service // Identifica o componente como servico
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    // AllArgsConstructor
    /*public RegistroProprietarioService(Proprietario proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }*/

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
