package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {


    //CONFIGURACAO DE AUTORIZACAO - QUEM PODE ACESSAR TAL URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //LIBERA ACESSO PARA TOPICOS
//        http.authorizeRequests()
//                .antMatchers("/topicos").permitAll();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "topicos/*").permitAll();
    }

    //CONFIGURAÇOES DE AUTENTICACAO
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    //CONFIGURACOES PARA ARQUIVOS ESTATICOS, COMO ARQUIVOS (CSS, JAVASCRIPTS, IMAGENS) -- FRONTEND
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
