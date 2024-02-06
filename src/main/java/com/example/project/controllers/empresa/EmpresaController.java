package com.example.project.controllers.empresa;


import com.example.project.DTO.EmpresaDTO;
import com.example.project.models.Cliente;
import com.example.project.models.Empresa;
import com.example.project.service.empresa.EmpresaService;
import com.example.project.utils.Utils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<?> createEmpresa(@RequestBody EmpresaDTO empresaDTO){
        try{
            URI uri = Utils.toUri("/new");
            Empresa empresa = empresaService.createEmpresa(empresaDTO);

            return ResponseEntity.created(uri).body(empresa);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Falha ao tentar criar uma nova empresa");
        }

    }
}
