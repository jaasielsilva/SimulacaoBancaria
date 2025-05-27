package com.exemplo.simulacaobancaria.controller;

import com.exemplo.simulacaobancaria.model.Conta;
import com.exemplo.simulacaobancaria.model.Usuario;
import com.exemplo.simulacaobancaria.repository.TransacaoRepository;
import com.exemplo.simulacaobancaria.service.AuthService;
import com.exemplo.simulacaobancaria.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimulacaoBancariaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private AuthService authService;
    @Autowired
    private TransacaoRepository transacaoRepository;

   @GetMapping("/")
public String index(Model model) {
    if (!authService.isLogado()) {
        return "redirect:/login";
    }

    Usuario usuario = authService.getUsuarioLogado();
    Conta conta = usuario.getConta();

    model.addAttribute("saldo", String.format("R$ %.2f", conta.getSaldo()));
    model.addAttribute("historico", contaService.getHistorico(conta));
    return "index";
}

@PostMapping("/depositar")
public String depositar(@RequestParam("valor") double valor, Model model) {
    Usuario usuario = authService.getUsuarioLogado();
    Conta conta = usuario.getConta();

    if (contaService.depositar(conta, valor)) {
        model.addAttribute("mensagem", "Depósito realizado com sucesso.");
        model.addAttribute("alertType", "success");
    } else {
        model.addAttribute("mensagem", "Valor de depósito inválido.");
        model.addAttribute("alertType", "danger");
    }
    return atualizarPagina(model, conta);
}

@PostMapping("/sacar")
public String sacar(@RequestParam("valor") double valor, Model model) {
    Usuario usuario = authService.getUsuarioLogado();
    Conta conta = usuario.getConta();

    boolean sucesso = contaService.sacar(conta, valor);
    if (sucesso) {
        model.addAttribute("mensagem", "Saque realizado com sucesso.");
        model.addAttribute("alertType", "success");
    } else {
        if (valor <= 0) {
            model.addAttribute("mensagem", "Valor de saque inválido.");
            model.addAttribute("alertType", "danger");
        } else {
            model.addAttribute("mensagem", "Saldo insuficiente.");
            model.addAttribute("alertType", "warning");
        }
    }
    return atualizarPagina(model, conta);
}

    private String atualizarPagina(Model model, Conta conta) {
    model.addAttribute("saldo", String.format("R$ %.2f", conta.getSaldo()));
    model.addAttribute("historico", contaService.getHistorico(conta));
    return "index";
}
}