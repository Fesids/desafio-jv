package com.example.project.controllers.transacao;


import com.example.project.DTO.EmailDetails;
import com.example.project.DTO.TransacaoDTO;
import com.example.project.configuration.email.EmailService;
import com.example.project.models.Empresa;
import com.example.project.models.Transacao;
import com.example.project.service.transacoes.TransacaoService;
import com.example.project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/transacoes")
public class TransacaoController {


    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendMessage(@RequestBody EmailDetails emailDetails) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailDetails.fromEmail());
        simpleMailMessage.setTo(emailDetails.toEmail());
        simpleMailMessage.setSubject(emailDetails.subject());
        simpleMailMessage.setText(emailDetails.body());
        emailService.sendMessage(simpleMailMessage);

        return "Email sent successfully";
    }



    @PostMapping("")
    public ResponseEntity<?> createTransacao(@RequestBody TransacaoDTO body){

        try{
            URI uri = Utils.toUri("/new");
            Transacao transacao = transacaoService.createTransacao(body);

            return ResponseEntity.created(uri).body(transacao);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Falha ao tentar criar uma nova transacao");
        }

    }





}
