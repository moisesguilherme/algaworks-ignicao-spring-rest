package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoModel {

    //Apenas para transferência de dados
    //Não tem regra de negócio
    //Para serelizar em JSON

    private Long id;
    // Não precisa ser igual a entidade, esse é o objetivo de ter um Representation model
    private String nomeProprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

}
