package com.algaworks.algatransito.domain.repository;

import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    // Spring DATA JPA retornar sem precisar da implementacão - pelo nome do método
    // find | By | propriedade
    //List<Proprietario> findByNome(String nome);
    List<Proprietario> findByNomeContaining(String nome);
    Optional<Proprietario> findByEmail(String email);
}

