package com.example.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    @NotBlank(message = "O cpf é um campo obrigatório")
    @CPF
    private String cpf;

    private String nome;

    private String email;

    private String telefone;

    @ManyToOne
    @JoinColumn(name = "empresa_id",  nullable = false)
    private Empresa empresa;



}
