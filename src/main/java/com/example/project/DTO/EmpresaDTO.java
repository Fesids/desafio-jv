package com.example.project.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record EmpresaDTO(
        @NotBlank
        @CNPJ
        String cnpj,

        String nome,

        Integer taxa,

        String email,

        String telefone,

        BigDecimal saldo
) {
}
