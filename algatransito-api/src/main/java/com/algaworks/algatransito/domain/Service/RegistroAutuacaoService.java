package com.algaworks.algatransito.domain.Service;

import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

    //Injeção é feita pelo construtor com @AllArgsConstructor
    private RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

        // Melhor colocar na propria classe domain model Autuacao
        // Criar métodos de negócios - adicionarAutuacao
        //novaAutuacao.setVeiculo(veiculo);
        //novaAutuacao.setDataOcorrencia();

        // Não precisa do save -> pois fez o add não entidade veículo
        // E carregaos aqui a instância de veiculo
        // O Jakarta persistence vai gerenciar essa instância,
        // Ou seja ele vai ficar no estado de management
        // Qualquer mudança vai ser automaticamente sincronizado
        return veiculo.adicionarAutuacao(novaAutuacao);
    }

}
