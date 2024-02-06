package com.example.project.service.transacoes;

import com.example.project.DTO.TransacaoDTO;
import com.example.project.configuration.email.EmailService;
import com.example.project.models.Cliente;
import com.example.project.models.Empresa;
import com.example.project.models.Transacao;
import com.example.project.repository.ClienteRepository;
import com.example.project.repository.TransacaoRepository;
import com.example.project.service.cliente.ClienteService;
import com.example.project.service.empresa.EmpresaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransacaoServiceImpl implements TransacaoService{

    @Autowired
    private TransacaoRepository transacaoRepository;


    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;


    @Override
    public Transacao createTransacao(TransacaoDTO body) {

        Transacao transacao = Transacao.builder()
                .cpf(body.cpf())
                .cnpj(body.cnpj())
                .valorTransicionado(body.valorTransicionado())
                .tipoTransacao(body.tipoTransacao())
                .build();

        Empresa empresa = empresaService.retrieveEmpresaByCNPJ(body.cnpj());
        Cliente cliente = clienteService.retrieveClienteByCPF(body.cpf());

        if(!cliente.getEmpresa().getCnpj().equals(empresa.getCnpj())){
            throw new RuntimeException("O cliente de CPF "+ cliente.getCpf()+ " não pertence ao quadro de clientes da empresa de CNPJ "+empresa.getCnpj());
        }

        try{


            empresaService.updateSaldo(body.cnpj(), body.valorTransicionado(), body.tipoTransacao());


            SimpleMailMessage emailCliente = new SimpleMailMessage();
            // O ENDEREÇO DE ENVIO E O DE RECEBIMENTO DEVEM ESTAR HABILITADOS NO SES
            emailCliente.setFrom(empresa.getEmail());
            emailCliente.setTo(cliente.getEmail());
            emailCliente.setSubject("Transção Concluída com sucesso!!!");
            emailCliente.setText("Sua operação de "+ body.tipoTransacao()+" no valor de "+body.valorTransicionado()+ " para o CNPJ "+ empresa.getCnpj()+ " foi efetuada com sucesso!!!");
            emailService.sendMessage(emailCliente);
            return transacaoRepository.save(transacao);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Falha ao completar a transação. Tente novamente.");
        }
    }
}
