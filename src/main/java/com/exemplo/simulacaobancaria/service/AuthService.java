package com.exemplo.simulacaobancaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.simulacaobancaria.model.Conta;
import com.exemplo.simulacaobancaria.model.Usuario;
import com.exemplo.simulacaobancaria.repository.ContaRepository;
import com.exemplo.simulacaobancaria.repository.UsuarioRepository;

@Service
public class AuthService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ContaRepository contaRepository;

    private Usuario usuarioLogado;

    public boolean login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            usuarioLogado = usuario.get();
            return true;
        }
        return false;
    }

    public void logout() {
        usuarioLogado = null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean isLogado() {
        return usuarioLogado != null;
    }

    public void cadastrarUsuario(String nome, String email, String senha) {
        if (usuarioRepository.findByEmail(email).isPresent()) return;

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setUsuario(usuario);
        usuario.setConta(conta);

        usuarioRepository.save(usuario);
    }
}
