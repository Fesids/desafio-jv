package com.example.project.repository;

import com.example.project.models.Cliente;
import com.example.project.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query(value = "SELECT * FROM empresas e where e.cnpj = :cnpj", nativeQuery = true)
    Optional<Empresa> findClienteByCNPJ(@Param("cnpj") String cnpj);
}
