package com.example.project.DTO;

import com.example.project.enums.ETipoTransacao;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TransacaoDTO(

        String cnpj,
        String cpf,

        BigDecimal valorTransicionado,

        ETipoTransacao tipoTransacao
) {
}
