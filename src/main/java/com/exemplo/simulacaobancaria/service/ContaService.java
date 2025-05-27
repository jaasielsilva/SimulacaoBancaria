package com.exemplo.simulacaobancaria.service;

import com.exemplo.simulacaobancaria.model.Conta;
import com.exemplo.simulacaobancaria.model.Transacao;
import com.exemplo.simulacaobancaria.repository.ContaRepository;
import com.exemplo.simulacaobancaria.repository.TransacaoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public ContaService(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public double getSaldo(Conta conta) {
        return conta.getSaldo();
    }

    public List<Transacao> getHistorico(Conta conta) {
        return transacaoRepository.findByConta(conta);
    }

    @Transactional
    public boolean depositar(Conta conta, double valor) {
        if (valor <= 0) return false;
        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        Transacao t = new Transacao();
        t.setTipo("Depósito");
        t.setValor(valor);
        t.setDataHora(LocalDateTime.now());
        t.setConta(conta);
        transacaoRepository.save(t);

        return true;
    }

    @Transactional
    public boolean sacar(Conta conta, double valor) {
        if (valor <= 0) return false;
        if (valor > conta.getSaldo()) return false;

        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);

        Transacao t = new Transacao();
        t.setTipo("Saque");
        t.setValor(valor);
        t.setDataHora(LocalDateTime.now());
        t.setConta(conta);
        transacaoRepository.save(t);

        return true;
    }
}