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


