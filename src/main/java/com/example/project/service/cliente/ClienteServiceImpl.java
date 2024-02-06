package com.example.project.service.cliente;

import com.example.project.DTO.ClienteDTO;
import com.example.project.models.Cliente;
import com.example.project.models.Empresa;
import com.example.project.repository.ClienteRepository;
import com.example.project.service.empresa.EmpresaService;
import com.example.project.service.empresa.EmpresaServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService{


    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private EmpresaService empresaService;


    @Override
    public Cliente createClient(ClienteDTO clienteDTO) {

        Empresa empresa = empresaService.retrieveEmpresaById(clienteDTO.empresa());

        var novoCliente = Cliente.builder()
                .cpf(clienteDTO.cpf())
                .nome(clienteDTO.nome())
                .empresa(empresa)
                .email(clienteDTO.email())
                .telefone(clienteDTO.telefone())
                .build();

        return clienteRepository.save(novoCliente);
    }

    @Override
    public Cliente retrieveClienteByCPF(String cpf) {
        return clienteRepository.findClienteByCPF(cpf).get();    }
}
