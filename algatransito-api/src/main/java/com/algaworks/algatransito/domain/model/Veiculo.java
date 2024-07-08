package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid //Validação em cascata para classe proprietário
    @ManyToOne
    private Proprietario proprietario;

    private String marca;

    private String modelo;

    private String placa;


    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    // Associação bidirecional - "veiculo" é a propriedade que está em Atuação
    @OneToMany(mappedBy = "veiculo")
    private List<Autuacao> autuacaoes = new ArrayList<>();

    //Classe anêmica ou modelo Anemico, quando usa apenas para transporte de dados
    //não são ricas, não tem nda de negócio
    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacaoes().add(autuacao);
        return autuacao;
    }
}
