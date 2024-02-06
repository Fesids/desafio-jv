package com.example.project.service.cliente;

import com.example.project.DTO.ClienteDTO;
import com.example.project.models.Cliente;

public interface ClienteService {
    Cliente createClient(ClienteDTO clienteDTO);

    Cliente retrieveClienteByCPF(String cpf);
}
