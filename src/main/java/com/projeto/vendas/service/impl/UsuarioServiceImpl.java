package com.projeto.vendas.service.impl;

import com.projeto.vendas.domain.entity.Usuario;
import com.projeto.vendas.domain.repository.UsuarioRepository;
import com.projeto.vendas.exception.SenhaInvalidarExcePtion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl  implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
       boolean senhaasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
       if(senhaasBatem ){
           return user;
       }
        throw new SenhaInvalidarExcePtion();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

       String[] roles = usuario.isAdmin() ?
               new String[]{"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
