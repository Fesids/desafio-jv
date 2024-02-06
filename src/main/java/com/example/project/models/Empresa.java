package com.example.project.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Insira um nome válido para a empresa")
    private String nome;

    @Column(name = "cnpj")
    @NotBlank(message = "O cnpj é um campo obrigatório")
    @CNPJ
    private String cnpj;

    @Column(name = "email")
    private String email;

    private String telefone;

    @Column
    private Integer taxa;

    @Column
    private BigDecimal saldo;

    @Nullable
    @OneToMany(mappedBy = "empresa")
    private Set<Cliente> clientes;

}
