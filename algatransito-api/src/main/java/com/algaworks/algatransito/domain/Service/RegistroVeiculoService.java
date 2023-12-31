package com.algaworks.algatransito.domain.Service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;


    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {

        // Evitar q passe um veículo existente
        if (novoVeiculo.getId() != null ) {
            throw new NegocioException("Veiculo a ser cadastrado não deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();


        if(placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa");
        }


        /*
        Mudou para classe registroVeiculoService
        Proprietario proprietario = proprietarioRepository.findById(novoVeiculo.getId())
                        .orElseThrow(() -> new NegocioException("Proprietário não encotrado"));
        */
        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        // resolve o problema do null nas propriedades
        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }




}
