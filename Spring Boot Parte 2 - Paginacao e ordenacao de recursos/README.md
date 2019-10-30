<h1>Vantagem de utilizar a interface Page</h1>

* Como vimos no vídeo anterior, foi necessário alterar o retorno do método lista, de List<Topico>, para Page<Topico>. Qual a vantagem de devolver um objeto Page, ao invés de um List?

* Adicionar dados sobre a paginação no JSON de resposta


* Ao utilizar o objeto Page, além de devolver os registros, o Spring também devolve informações sobre a paginação no JSON de resposta , como número total de registros e páginas.

<h1>Ordenando registros na lista</h1>

* Vimos que, para ordenar os registros, foi necessário passar um parâmetro com o nome do atributo para realizar a ordenação. O que acontece se o nome do atributo informado estiver incorreto?

* Um erro será devolvido ao cliente

* No nosso código, no parâmetro pageable, existe uma anotação que podemos colocar chamada @PageableDefault. Nesse PageableDefault tem alguns parâmetros. Consigo dizer que sort = id, Direction.DESC. Com isso, ele está dizendo que a paginação default é id de maneira decrescente. O default é: se não estiver vindo um parâmetro de ordenação.

* Vamos fazer o teste no Postman sem mandar os parâmetros de ordenação. Ele vem ordenado pelo id de maneira decrescente. Se eu passar o parâmetro &sort=dataCriacao,asc e disparar, veio de acordo com o parâmetro, ignorando o default. É um negócio bem interessante, bem flexível.

* Inclusive, dá para controlar também o default não só da ordem, mas também da paginação, porque e se ele não passar nenhum parâmetro? Ele vai trazer todos os registros do banco de dados, porque a paginação é opcional. Posso dizer que page = 0, size = 10. Se ele não passar os parâmetros de paginação, traga da primeira página apenas dez registros. Consigo deixar por padrão qual é a paginação, além da ordenação. É bem poderoso, bem simples, e o seu cliente da API consegue controlar a paginação, a ordenação, e se ele não mandar os parâmetros a gente consegue controlar qual vai ser o comportamento padrão.

<h1>Uso da anotação @PageableDefault</h1>

* Qual o objetivo da anotação @PageableDefault?

* Indicar o padrão de paginação/ordenação ao Spring, quando o cliente da API não enviar tais informações

* Para receber os parâmetros de ordenação e paginação diretamente nos métodos do controller, devemos habilitar o módulo SpringDataWebSupport, adicionando a anotação @EnableSpringDataWebSupport na classe ForumApplication.

<h1>Aula 02 - Melhorando desempenho com Spring Cache</h1>

<h2>Anotação @Cacheable</h2>

* a anotação @Cacheable possui o parâmetro value, do tipo String. Para que serve esse parâmetro?

* Para indicar ao Spring o nome do cache associado a um determinado método

* A string passada como parâmetro para a anotação @Cacheable funciona como um identificador único do cache.

<h2>Sobre a anotação @CacheEvict</h2>

* Sobre a utilização da anotação @CacheEvict, é correto afirmar que:

* Ela deveria ser utilizada nos métodos que alteraram os registros armazenados em cache pela API

* Isso é importante para evitar que os clientes obtenham informações desatualizadas.

* Devemos indicar, no parâmetro value, quais caches devem ser invalidados

* Nesse parâmetro devemos indicar os identificadores dos caches que deverão ser invalidados.

<h2>Boas práticas no uso de cache</h2>

* Nos últimos vídeos nós vimos como utilizar o cache do Spring Boot e como invalidar o cache para na hora que alguém cadastrar, incluir ou alterar um tópico, a lista de tópicos seja atualizada e o cliente não ficar vendo uma informação desatualizada e antiga.

* No vídeo de hoje, vou fazer uma discussão sobre a utilização de cache. Esse vídeo é opcional, não vamos implementar nada no projeto, e se você já conhece cache, você pode pular e ir para o próximo tranquilamente.

* A discussão importante é que cache é um recurso que utilizamos para ganhar performance na aplicação, principalmente onde tenho pesquisas ao banco de dados. Ficar indo e voltando ao banco de dados o tempo inteiro é meio lento, então costumamos utilizar cache para guardar o retorno em memória.

* Porém, na teoria, poderíamos colocar a aplicação inteira em cache então, e eu nunca mais vou no banco de dados. Na prática isso não é muito interessante, porque precisamos invalidar o cache em determinados momentos. Se você ficar toda hora guardando informação, depois limpa, guarda de novo, toda hora você tem que ficar invalidando, isso tem um custo de processamento e pode até piorar a performance da sua aplicação.

* A ideia é você não utilizar cache em todos os lugares, mas sim onde fizer sentido. No geral, utilizamos cache naquelas tabelas que nunca ou raramente são atualizadas. No exemplo do curso, eu utilizei cache na funcionalidade de lista de tópicos. Só que aqui, por exemplo, não é um bom cenário de utilização de cache, porque se pensarmos no fórum da Alura, o tempo inteiro tenho os alunos cadastrando tópicos, excluindo, alterando, postando respostas. Toda hora meu cache ia ser invalidado. Isso ia dar problemas.

* Você vai ter que pensar na sua aplicação. Sabe aquelas tabelas que nunca ou raramente são atualizadas? Quase todo projeto tem aquelas tabelas, por exemplo, de país, de Estado, de cidade, de tipos. É uma tabela estável. Esse é o tópico de entidade de consulta em que faz sentido utilizar cache, porque vou fazer a consulta uma única vez, vou guardar o resultado em memória, e aquele resultado vai ficar um bom período de tempo em memória. Ele não vai ser atualizado o tempo inteiro.

* Tomem cuidado no projeto de vocês, onde vão utilizar o cache. Procurem pensar um pouco antes de utilizar e utilizem nesse tipo de situação, nesses métodos que nunca ou raramente vão ser atualizados, porque assim você evita esse custo de limpar o cache e guardar de novo.

* Essa era a discussão de hoje sobre as boas práticas no uso de cache. Espero vocês no próximo vídeo, onde vamos ver novos recursos para completar e deixar nossa API mais interessante.

* Para que o Spring guarde o retorno de um método no cache, devemos anotá-lo com @Cacheable;

* Para o Spring invalidar algum cache após um determinado método ser chamado, devemos anotá-lo com @CacheEvict;

* Devemos utilizar cache apenas para as informações que nunca ou raramente são atualizadas no banco de dados.

<h1>Aula 03 - Proteção com Spring Security</h1>

<h2>Spring Security e os endpoints da API</h2>

* Ao habilitar o Spring Security, o que acontecerá com os endpoints da API?

* Eles serão bloqueados, por padrão

* Os endpoints se tornam restritos, por padrão.


<h2>Por que indicar o método GET?</h2>

* Vimos no vídeo anterior que, para liberar acesso ao endpoint de lista de tópicos, foi necessário adicionar a seguinte linha de código:

* antMatchers(HttpMethod.GET, “/topicos”).permitAll();
* Por que foi necessário indicar o método GET nessa configuração?

* Para liberar acesso apenas às requisições do tipo GET

<h2>Objetivo do método anyRequest().authenticated()</h2>

* Nas configurações de autorização, qual o objetivo de chamar o método anyRequest().authenticated()?

* Para indicar que outras URLs que não foram configuradas devem ter acesso restrito


<h2>Lógica de Autenticação</h2>

* No vídeo anterior, vimos que foi necessário criar uma classe, implementando a interface UserDetailsService do Spring Security. Qual o objetivo dessa classe?


* Para indicar ao Spring Security que essa é a classe service que executa a lógica de autenticação

* Para utilizar o módulo do Spring Security, devemos adicioná-lo como dependência do projeto no arquivo pom.xml;
* Para habilitar e configurar o controle de autenticação e autorização do projeto, devemos criar uma classe e anotá-la com @Configuration e @EnableWebSecurity;
* Para liberar acesso a algum endpoint da nossa API, devemos chamar o método http.authorizeRequests().antMatchers().permitAll() dentro do método configure(HttpSecurity http), que está na classe SecurityConfigurations;
* O método anyRequest().authenticated() indica ao Spring Security para bloquear todos os endpoints que não foram liberados anteriormente com o método permitAll();
* Para implementar o controle de autenticação na API, devemos implementar a interface UserDetails na classe Usuario e também implementar a interface GrantedAuthority na classe Perfil;
* Para o Spring Security gerar automaticamente um formulário de login, devemos chamar o método and().formLogin(), dentro do método configure(HttpSecurity http), que está na classe SecurityConfigurations;
* A lógica de autenticação, que consulta o usuário no banco de dados, deve implementar a interface UserDetailsService;
* Devemos indicar ao Spring Security qual o algoritmo de hashing de senha que utilizaremos na API, chamando o método passwordEncoder(), dentro do método configure (AuthenticationManagerBuilder auth), que está na classe SecurityConfigurations.


<h1>Aula 04 - Gerando token com JWT (Json Web Token)</h1>

* Na última aula, nós aprendemos sobre o Spring security, como proteger nossa API REST liberando endpoints e em outros exigindo autenticação. Porém, a autenticação que fizemos foi a tradicional, com usuário e senha, e o servidor, sempre que o usuário efetua login, cria uma sessão para guardar essas informações. Mas isso não é uma boa prática no modelo REST. O ideal é que a nossa autenticação seja stateless. Se vocês lembram, no curso anterior tivemos uma discussão sobre o modelo REST, e uma das características desse modelo é que toda a comunicação seja de modelo stateless. Ou seja, o cliente dispara uma requisição, leva todas as informações necessárias, o servidor processa, executa o que tem que executar, devolve a resposta e acabou.

* Eu tenho um slide mostrando essa ideia da autenticação via session. Em uma aplicação web tradicional, esse é o modelo utilizado. Toda vez que o usuário vai se autenticar no sistema, ele entra no formulário de login, digita o e-mail, a senha, quando faz o login o sistema cria uma sessão e nessa session ele armazena as informações do usuário. Para o servidor conseguir diferenciar um do outro, cada sessão tem id único. Em uma aplicação Java, essa sessão é chamada de jsessionid.

* Esse id é devolvido como resposta para o navegador, no formato de um cookie. O navegador armazena isso em um cookie, armazena o id da sessão. Nas próximas requisições que esse usuário disparar nesse navegador, o browser automaticamente envia esse parâmetro como um cookie. Quando chega uma requisição para o servidor, ele verifica se está vindo um cookie Jsessionid. Se estiver, ele recupera o id, com os dados.

* Dentro da sessão tem as informações. Isso vai contra um dos princípios do REST, que é de ser stateless. Nesse modelo, para cada usuário que estiver logado na aplicação vou ter um espaço na memória armazenando as informações. Isso consome espaço de memória, e se o servidor cair vou perder todas as sessões. Se eu quiser ter escalabilidade, se eu quiser ter um balanceamento de carga com múltiplos servidores, eu teria problema de compartilhamento.

* No modelo REST o ideal é trabalhar com a autenticação de maneira stateless. Com o Spring security é possível fazer isso. Conseguimos explicar para o Spring que não é para ele criar a sessão, que toda vez que o usuário se logar vou fazer a lógica de autenticação, mas não é para criar uma session. Só que aí, nas próximas requisições que os clientes dispararem, o servidor não sabe se está logado ou não, porque não tem nada armazenado. O cliente vai ter que mandar alguma informação dizendo quem é ele, se ele está logado, se tem permissão para acessar. Isso geralmente é feito via tokens. O pessoal costuma usar a especificação JSON web token para fazer esse tipo de autenticação. Ou seja, a cada requisição, o cliente vai mandar um token identificando quem é o usuário que está disparando essa requisição, se ele tem permissão para disparar.


<h2>Página de login</h2>

* No vídeo anterior, tivemos que remover a chamada ao método and().formLogin() da classe SecurityConfigurations. Mas dessa maneira, como um usuário vai se autenticar na API?

* O usuário deverá se autenticar por uma página de login fornecida pela própria aplicação cliente

<h2>Token</h2>

* No último vídeo, vimos que ao devolver o token para o cliente, foi enviado juntamente outra informação, chamada Bearer. A que se refere essa informação?

* É o tipo de autenticação a ser feita pelo cliente com o token que lhe foi devolvido

* Bearer é um dos mecanismos de autenticação utilizados no protocolo HTTP, tal como o Basic e o Digest.

* Em uma API Rest, não é uma boa prática utilizar autenticação com o uso de session;
* Uma das maneiras de fazer autenticação stateless é utilizando tokens JWT (Json Web Token);
* Para utilizar JWT na API, devemos adicionar a dependência da biblioteca jjwt no arquivo pom.xml do projeto;
* Para configurar a autenticação stateless no Spring Security, devemos utilizar o método sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
* Para disparar manualmente o processo de autenticação no Spring Security, devemos utilizar a classe AuthenticationManager;
* Para poder injetar o AuthenticationManager no controller, devemos criar um método anotado com @Bean, na classe SecurityConfigurations, que retorna uma chamada ao método super.authenticationManager();
* Para criar o token JWT, devemos utilizar a classe Jwts;
* O token tem um período de expiração, que pode ser definida no arquivo application.properties;
* Para injetar uma propriedade do arquivo application.properties, devemos utilizar a anotação @Value.


<h1>Aula 05 - Autenticação via JWT</h1>

<h2>Anotação para registrar o filtro?</h2>

* No último vídeo, vimos que precisamos criar um filtro, que vai conter a lógica de recuperar o token do cabeçalho Authorization, validá-lo e autenticar o cliente. Vimos também que esse filtro precisa ser registrado no Spring.

* Qual anotação foi utilizada para registrar o filtro?

* R: Não existe anotação para registrar o filtro


<h2>Injeçao de dependencias no Filter</h2>

* Por que não é possível fazer injeção de dependências com a anotação @Autowired na classe AutenticacaoViaTokenFilter?

* Porque ela não é um bean gerenciado pelo Spring

* O filtro foi instanciado manualmente por nós, na classe SecurityConfigurations e portanto o Spring não consegue realizar injeção de dependências via @Autowired.

<h2>Forçando a autenticação via SecurityContextHolder</h2>

* Vimos no último vídeo que foi necessário indicar ao Spring que o cliente está autenticado. Por que essa autenticação foi feita com a classe SecurityContextHolder e não com a AuthenticationManager?

* Porque a classe AuthenticationManager dispara o processo de autenticação via username/password

* A classe AuthenticationManager deve ser utilizada apenas na lógica de autenticação via username/password, para a geração do token.

* Para enviar o token JWT na requisição, é necessário adicionar o cabeçalho Authorization, passando como valor Bearer token;
* Para criar um filtro no Spring, devemos criar uma classe que herda da classe OncePerRequestFilter;
* Para recuperar o token JWT da requisição no filter, devemos chamar o método request.getHeader("Authorization");
* Para habilitar o filtro no Spring Security, devemos chamar o método and().addFilterBefore(new AutenticacaoViaTokenFilter(), UsernamePasswordAuthenticationFilter.class);
* Para indicar ao Spring Security que o cliente está autenticado, devemos utilizar a classe SecurityContextHolder, chamando o método SecurityContextHolder.getContext().setAuthentication(authentication).

<h1>Aula 06 - Monitoramento com Spring Boot Actuator</h1>

<h2>Spring Boot Actuator</h2>

* Sobre o Spring Boot Actuator, quais das seguintes afirmações abaixo são corretas?

* As informações que ele exibe podem ser customizadas pela API

* É possível controlar na API quais informações serão exibidas ou escondidas pelo Actuator.

* É uma ferramenta utilizada para monitoramento de uma API

* O principal objetivo do Actuator é fornecer informações para o monitoramento da API.

<h2>Sobre o Spring Boot Admin</h2>

* Sobre o Spring Boot Admin, é correto afirmar que:

* Ele deve ser criado como uma aplicação Spring Boot

* Para utilizá-lo, devemos criar um projeto Spring Boot e o adicionar como uma de suas dependências.

* Ele fornece uma interface gráfica para monitoramento da API

* O Spring Boot Admin possui interface gráfica contendo detalhes sobre os recursos utilizados pela API.

<h2>Spring Boot Admin Client</h2>

* Como fazer para que o Spring Boot Admin Server monitore uma API?

* É necessário adicionar o endereço do projeto Spring Boot Admin Server nas configurações da API cliente.

* O projeto cliente é quem deve conhecer o endereço do projeto Spring Boot Admin Server.

<h2>O que aprendemos?</h2>

* Para adicionar o Spring Boot Actuator no projeto, devemos adicioná-lo como uma dependência no arquivo pom.xml;
* Para acessar as informações disponibilizadas pelo Actuator, devemos entrar no endereço http://localhost:8080/actuator;
* Para liberar acesso ao Actuator no Spring Security, devemos chamar o método .antMatchers(HttpMethod.GET, "/actuator/**");
* Para que o Actuator exponha mais informações sobre a API, devemos adicionar as propriedades management.endpoint.health.show-details=always e management.endpoints.web.exposure.include=* no arquivo application.properties;
* Para utilizar o Spring Boot Admin, devemos criar um projeto Spring Boot e adicionar nele os módulos spring-boot-starter-web e spring-boot-admin-server;
* Para trocar a porta na qual o servidor do Spring Boot Admin rodará, devemos adicionar a propriedade server.port=8081 no arquivo application.properties;
* Para o Spring Boot Admin conseguir monitorar a nossa API, devemos adicionar no projeto da API o módulo spring-boot-admin-client e também adicionar a propriedade spring.boot.admin.client.url=http://localhost:8081 no arquivo application.properties;
* Para acessar a interface gráfica do Spring Boot Admin, devemos entrar no endereço http://localhost:8081.

<h1>Aula 07 - Documentação da API com Swagger</h1>

<h2>Documentação da API</h2>

* No último vídeo, vimos como configurar o SpringFox Swagger, para gerar a documentação, de maneira automatizada, da nossa API. Nas configurações, foi necessário adicionar o seguinte trecho de código:

* ignoredParameterTypes(Usuario.class)

* Por que essa configuração foi necessária?

* Para ignorar nos endpoints os parâmetros relacionados à classe Usuario

* Como nossa classe Usuario possui atributos relacionados ao login, senha e perfis de acesso, não é recomendado que essas informações sejam expostas na documentação do Swagger.

<h2>Token JWT na documentação do Swagger</h2>

* Como fazer para testar os endpoints que exigem autenticação na interface do Swagger?

* É necessário configurar um header para enviar o token de autenticação na interface do Swagger

* Sem esse header não será possível testar os endpoints que exigem autenticação pela interface do Swagger.


<h2>Classe SwaggerConfigurations</h2>

```java
@EnableSwagger2
@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Usuario.class)
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                    .name("Authorization")
                                    .description("Header para Token JWT")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build()));
    }

}

```

<h2>Classe SecurityConfigurations</h2>

* Caso você não queira digitar o código da classe SecurityConfigurations mostrado no vídeo anterior, pode copiar daqui:

```java
@Override
public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
}

```

* Para documentar a nossa API Rest, podemos utilizar o Swagger, com o módulo SpringFox Swagger;
* Para utilizar o SpringFox Swagger na API, devemos adicionar suas dependências no arquivo pom.xml;
* Para habilitar o Swagger na API, devemos adicionar a anotação @EnableSwagger2 na classe ForumApplication;
* As configurações do Swagger devem ser feitas criando-se uma classe chamada SwaggerConfigurations e adicionando nela a anotação @Configuration;
* Para configurar quais endpoints e pacotes da API o Swagger deve gerar a documentação, devemos criar um método anotado com @Bean, que devolve um objeto do tipo Docket;
* Para acessar a documentação da API, devemos entrar no endereço http://localhost:8080/swagger-ui.html;
* Para liberar acesso ao Swagger no Spring Security, devemos chamar o seguinte método web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**"), dentro do método void configure(WebSecurity web), que está na classe SecurityConfigurations.

