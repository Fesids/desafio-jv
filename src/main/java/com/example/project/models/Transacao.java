package com.example.project.models;

import com.example.project.enums.ETipoTransacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo CNPJ é obrigatório")
    @CNPJ
    private String cnpj;

    @NotBlank(message = "O campo CPF é obrigatório")
    private String cpf;

    private BigDecimal valorTransicionado;

    private ETipoTransacao tipoTransacao;


}
