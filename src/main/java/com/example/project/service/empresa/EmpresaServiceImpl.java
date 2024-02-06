package com.example.project.service.empresa;

import com.example.project.DTO.EmpresaDTO;
import com.example.project.enums.ETipoTransacao;
import com.example.project.models.Empresa;
import com.example.project.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class EmpresaServiceImpl implements EmpresaService{

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa createEmpresa(EmpresaDTO empresaDTO) {
        var empresa = Empresa.builder().nome(empresaDTO.nome())
                .cnpj(empresaDTO.cnpj())
                .saldo(empresaDTO.saldo())
                .telefone(empresaDTO.telefone())
                .email(empresaDTO.email())
                .taxa(empresaDTO.taxa())
                .build();

        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa retrieveEmpresaById(Long id) {

        var empresa = empresaRepository.findById(id);
        return empresa.get();
    }

    @Override
    public Empresa retrieveEmpresaByCNPJ(String cnpj) {
        return empresaRepository.findClienteByCNPJ(cnpj).get();
    }

    @Override
    public Empresa updateSaldo(String cnpj, BigDecimal valor,
                               ETipoTransacao tipoTransacao) {
        Empresa empresa = empresaRepository.findClienteByCNPJ(cnpj).get();
        BigDecimal novoSaldo = new BigDecimal(0);



        switch (tipoTransacao){
            case DEPOSITO -> {
                novoSaldo = empresa.getSaldo().add(valor);
            }

            case SAQUE -> {
                if(empresa.getSaldo().compareTo(valor) < 0){
                    throw new RuntimeException("O valor sacado execede o saldo geral disponibilizado pela empresa");
                }
                novoSaldo = empresa.getSaldo().subtract(valor);
            }


        }

        empresa.setSaldo(novoSaldo);
        empresaRepository.save(empresa);
        return null;
    }
}
