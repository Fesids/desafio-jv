package com.example.project.controllers.cliente;


import com.example.project.DTO.ClienteDTO;
import com.example.project.models.Cliente;
import com.example.project.service.cliente.ClienteService;
import com.example.project.service.cliente.ClienteServiceImpl;
import com.example.project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;


    @PostMapping("")
    public ResponseEntity<?> createCliente(@RequestBody ClienteDTO clienteDTO){

        try{
            URI uri = Utils.toUri("/new");
            Cliente cliente = clienteService.createClient(clienteDTO);

            return ResponseEntity.created(uri).body(cliente);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Falha ao tentar criar novo cliente");
        }

    }

}
