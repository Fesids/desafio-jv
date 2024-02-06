package com.example.project.service.empresa;

import com.example.project.DTO.EmpresaDTO;
import com.example.project.enums.ETipoTransacao;
import com.example.project.models.Empresa;

import java.math.BigDecimal;

public interface EmpresaService {

    Empresa createEmpresa(EmpresaDTO empresaDTO);

    Empresa retrieveEmpresaById(Long id);

    Empresa retrieveEmpresaByCNPJ(String cnpj);

    Empresa updateSaldo(String cnpj, BigDecimal valor, ETipoTransacao tipoTransacao);


}
