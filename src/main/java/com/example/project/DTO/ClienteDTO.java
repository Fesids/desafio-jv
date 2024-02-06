package com.example.project.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteDTO(

        @NotBlank
        @CPF
        String cpf,

        String nome,

        String email,

        String telefone,

        Long empresa


) {

    @Builder
    public ClienteDTO{

    }
}
