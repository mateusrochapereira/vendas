package com.projeto.vendas.config;

import com.projeto.vendas.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/clientes/salvarCliente")
                         .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/pedidos/fazerPedido")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/SalvarProduto")
                         .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/api/usuarios/**")
                        .permitAll()
                    .anyRequest().authenticated()
                .and()
                //.formLogin("/meulogin.html");
                     .httpBasic();

    }

}
