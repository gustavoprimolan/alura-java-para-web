package br.com.alura.forum.config.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //NECESSÁRIO PARA FAZER A INJECAO DE DEPENDENCIA O AuthenticationManager no CONTROLLER
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //CONFIGURACAO DE AUTORIZACAO - QUEM PODE ACESSAR TAL URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //LIBERA ACESSO PARA TOPICOS
//        http.authorizeRequests()
//                .antMatchers("/topicos").permitAll();


        //PARA GET PERMITE TODOS E PARA OUTRAS REQUISICOES APENAS QUEM ESTÁ AUTENTICADO
        //UTILIZANDO O and().formLogin() ele irá criar uma sessao no servidor
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
//                .antMatchers(HttpMethod.GET, "topicos/*").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin(); //UTILIZA O FORMULARIO PADRAO DO SPRING PARA AUTENTICACAO
                                    //SE VOCE ACESSAR localhost:PORTA ELE VAI TE JOGAR PARA UM FORMULÁRIO PADRAO DE LOGIN

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable() //CROSS SITE REQUEST FORGERY - ATAQUE HACKER QUE ACONTECE AUTENTICACAO VIA WEB
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// A SESSAO SERÁ STATELESS, POIS SERÁ VIA JWT, ENTRETANDO SERÁ NECESSÁRIO CRIAR UM CONTROLLER PARA AUTENTICACAO
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); //REGISTRA O FILTRO PARA O SPRING - MAS ELE JÁ TEM UM FILTRO DEFAULT E PRECISA INDICAR QUEM É O FILTRO QUE VEM ANTES (UsernamePasswordAuthenticationFilter)

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
        //IGNORAR A QUESTAO DE PERMISSAO PARA O SWAGGER
        //COM ISSO PRECISA PASSAR OS ACESSOS DA PÁGINA QUE O SWAGGER IRÁ UTILIZAR
        //ENDERECOS QUE O SWAGGER CHAMA
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

}
