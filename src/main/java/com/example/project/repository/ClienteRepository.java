package com.example.project.repository;


import com.example.project.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM clientes c where c.cpf = :cpf", nativeQuery = true)
    Optional<Cliente> findClienteByCPF(@Param("cpf") String cpf);
}
