package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    //CONFIGURACAO DE AUTORIZACAO - QUEM PODE ACESSAR TAL URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //LIBERA ACESSO PARA TOPICOS
//        http.authorizeRequests()
//                .antMatchers("/topicos").permitAll();


        //PARA GET PERMITE TODOS E PARA OUTRAS REQUISICOES APENAS QUEM ESTÁ AUTENTICADO
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "topicos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin(); //UTILIZA O FORMULARIO PADRAO DO SPRING PARA AUTENTICACAO
                                    //SE VOCE ACESSAR localhost:PORTA ELE VAI TE JOGAR PARA UM FORMULÁRIO PADRAO DE LOGIN
    }

    //CONFIGURAÇOES DE AUTENTICACAO
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //UTILIZA O SERVICO PARA PROCURAR O USUÁRIO
        //MD5 E CHA2 NAO SAO MAIS ALGORITMOS SEGUROS PARA CRIPTOGRAFIA
        //O SPRING IMPLEMENTA O BCrypt
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //CONFIGURACOES PARA ARQUIVOS ESTATICOS, COMO ARQUIVOS (CSS, JAVASCRIPTS, IMAGENS) -- FRONTEND
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

}
