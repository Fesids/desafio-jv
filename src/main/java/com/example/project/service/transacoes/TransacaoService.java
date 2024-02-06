package com.example.project.service.transacoes;

import com.example.project.DTO.TransacaoDTO;
import com.example.project.models.Transacao;
import com.example.project.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface TransacaoService {

    Transacao createTransacao(TransacaoDTO body);



}
