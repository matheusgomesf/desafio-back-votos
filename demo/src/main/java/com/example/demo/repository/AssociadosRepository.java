package com.example.demo.repository;

import com.example.demo.model.Associados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadosRepository extends JpaRepository<Associados, Long> {

    Optional<Associados> findByCpf(String cpf);
}
