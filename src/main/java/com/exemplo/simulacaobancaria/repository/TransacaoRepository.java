package com.exemplo.simulacaobancaria.repository;

import com.exemplo.simulacaobancaria.model.Conta;
import com.exemplo.simulacaobancaria.model.Transacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByConta(Conta conta);
}
