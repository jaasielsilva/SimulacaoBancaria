package com.exemplo.simulacaobancaria.repository;

import com.exemplo.simulacaobancaria.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
