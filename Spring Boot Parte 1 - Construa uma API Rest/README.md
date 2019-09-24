<h1>Hello World</h1>

* É uma classe, que tem um método main, que chama o método run da classe SpringApplication. Essa classe tem a anotação @springbootapplication. Essa é a classe que vamos utilizar para rodar nosso projeto. Nele, nós não adicionamos o servidor separado e incluímos o projeto nele. Para rodar o projeto, rodamos a classe main e ele tem um Tomcat embutido que roda para subir nossa aplicação.

* Só para garantir que está tudo ok, vamos fazer nosso famoso Hello World. Vou criar um controller, mapear para o endereço barra, e ver se o Spring vai chamar o endereço.

* Para criar um controller, vou criar uma nova classe, ctrl+n, class, vou trocar o pacote para controller, e o nome da classe vai ser “Hello controller”. Você anota depois a classe com a @controller, para o Spring conseguir encontrar essa classe, e ele que vai fazer o gerenciamento da classe. Depois, nós só temos que fazer o import da anotação que vem do pacote do Spring. E vou criar um método: @RequestMapping(“/”) @ResponseBody public string Hello() { return “Hello World!”;

* Como eu não quero navegar para uma página, tenho que colocar mais uma anotação, que é o @ResponseBody. Senão, o Spring vai considerar que o retorno é uma página que vai procurar essa página no nosso projeto. Colocando o @ResponseBody ele devolve a string direto para o navegador.

<h1>Sobre o Spring e Spring Boot</h1>

* O Spring é um dos frameworks mais antigos do Java, foi criado em 2002, 2003, e até hoje está firme e forte no mercado, cada vez mais popular e sendo utilizado por desenvolvedores Java não só no Brasil, mas no mundo inteiro.

* O Spring foi desenvolvido por um desenvolvedor chamado Rod Johnson. Naquela época, o pessoal utilizava muito para desenvolver aplicações corporativas, com Java, o Java EE, que era até chamado J2EE. dentro do J2EE você tinha algumas tecnologias, como RMI, JB, dentre outras, que eram um pouco complexas, e muitos desenvolvedores acabavam sofrendo quando iam desenvolver aplicações grandes, que precisavam ter uma boa performance e boa escalabilidade. Não por culpa do J2EE em si, mas por mal uso, por não conhecer boas práticas.

* O Rod era um especialista em J2EE. Ele sabia muito bem das armadilhas e como montar uma boa arquitetura, robusta, escalável, de fácil manutenção. Ele teve essa visão, viu essa dificuldade, baseado nas consultorias que ele prestava, e teve a ideia de escrever um livro. Então, ele escreveu, dando dicas de como montar uma arquitetura escalável, robusta, performática, usando o J2EE, sem cair nas armadilhas do mercado.

* O livro que ele escreveu se chama Expert one-on-one: J2EE Design and development. É um livro que ficou bem famoso. Além de mostrar esses problemas do J2EE e como você poderia montar uma boa arquitetura, ele também mostrava algumas das dificuldades e coisas ruins do J2EE em si. Ele apresentou no livro dele um código, como se fosse uma biblioteca alternativa. Nesse livro, ele escreveu mais de trinta mil linhas de código.

* Posteriormente, junto com o livro, foi publicado um fórum onde as pessoas podiam discutir e fazer o download da biblioteca. Várias pessoas começaram a baixar e utilizar nos projetos. Alguns desenvolvedores deram uma ideia para o Rod: por que não pegamos esse código, transformamos em uma biblioteca e lançamos no mercado gratuitamente, para ser uma alternativa ao J2EE?

* Depois, alguns desenvolvedores se reuniram com ele e eles foram criando essa biblioteca, que foi publicada em 2003, sendo que a versão 1.0 saiu em 2004. Assim nasce o Spring framework.

* A ideia do Spring era ser uma alternativa a esse modelo complexo do J2EE. O grande foco dele era em simplicidade de código. O coração do Spring foi todo baseado naquela ideia, nos padrões de inversão de controle e injeção de dependências. Com isso, o seu código, a sua lógica de negócio, sua regra não precisava correr atrás das dependências da infraestrutura. Nós invertíamos o controle e a infraestrutura chegava de bandeja para o seu código, para que ele seja fácil de manter, desacoplado.

* Esse foi o coração do Spring. E isso agradou muito os desenvolvedores, que conseguiam implementar seu sistema com classes Java seguindo o padrão POJO, classe simples, sem ter muita dependência de infraestrutura, nem nada muito complexo.

* Com isso, o Spring acabou se tornando popular, foi evoluindo e desenvolvido também em módulos. Você tinha o módulo central de IOC, inversão de controles, e em volta dele você tinha alguns módulos, como MVC, o módulo de segurança, de transação. Inclusive, ele até suportava o JavaEE. Ele se integra com tecnologias como JPA, e como o bin validation, que veremos no curso. Então, era uma alternativa, mas nunca foi um concorrente, nunca quis substituir o J2EE. Sempre quis ser um complemento. Inclusive, ele utiliza muitas das tecnologias do JavaEE.

* Ele foi evoluindo, foram surgindo novos módulos. Enquanto isso, o JavaEE também foi evoluindo. Foram surgindo outras tecnologias, o EJB, que era o grande coração do J2EE, que dava muito problema, muita dor de cabeça. Foram surgindo outras tecnologias e frameworks no mercado, como JSF, Wicket, depois o CDI, dentre outras coisas. Mas aí o JavaEE acabou dando uma esfriada, tendo alguns problemas, ficando para trás. Em 2013, 2014, o pessoal do Spring criou o Spring Boot, que foi um projeto que revolucionou o desenvolvimento para Java e que fez o Spring alavancar de novo no mercado.

* A ideia do Spring Boot é que você consiga desenvolver uma aplicação sem o uso de um container, sem ser o desenvolvimento tradicional, em que você precisa ter sua aplicação e roda ela dentro de um servidor. Quando você termina de desenvolver, você gera um WAR e joga ele dentro de um servidor. A ideia é inverter esse processo. Temos um servidor embutido e rodamos a aplicação em um main. Conforme vamos ver no curso, o nosso projeto vai ser inicializado por uma classe main.

* Com isso, não preciso mais gerar um WAR. Posso gerar o build da minha aplicação como sendo um JAR, que é muito mais leve e mais simples de ser executado. Isso acabou indo de encontro com ideias fortes no mercado, como a de desenvolvimento de micro serviços. O pessoal utiliza muito o Spring Boot para montar micro serviços, como o API REST. Se você tem uma API REST, para desenvolver com o Spring Boot é uma mão na roda, porque além de ter essas ideias e do contêiner ser embutido, o Spring Boot também simplificou muito uma coisa muito chata no Spring, que é a parte de configuração.

* Quando o Spring começou, toda a parte de configuração era feita via XML. você tinha um XML gigantesco configurando zilhões de coisas. Depois eles evoluíram e trouxeram o suporte para anotações, mas mesmo assim você tinha que criar várias classes com vários bings configurados com anotações. Ficava algo muito complexo. Para você criar um projeto do zero, você gastava um tempão só para fazer a parte de configuração.

* A ideia do Spring Boot é que muitas coisas já vêm configuradas por padrão para você. Você consegue criar um projeto e inicializá-lo de uma maneira muito rápida, muito produtiva, o que atraiu as empresas a utilizarem o Java.

* O Spring Boot foi evoluindo, e está hoje firme e forte no mercado como sendo o principal framework utilizado por desenvolvedores Java para construir aplicações REST, usando micro serviços, containers, vai de encontro com a ideia de usar o Spring Boot, uma aplicação Java leve, simples, rodando dentro de um container, usando o Docker, por exemplo. Tudo isso acabou casando com as tendências de mercado. Graças a isso o Spring disparou, o Boot foi o que fez o Spring disparar no mercado.

* O Java EE acabou decaindo. Não morreu, mas caiu em desuso. Hoje, é bem difícil ver um projeto sendo desenvolvido com Java EE. O pessoal prefere utilizar o Spring, Spring Boot, Spring Cloud, que é um conjunto de bibliotecas que foram utilizadas para montar micro serviços e ter toda aquela infraestrutura de micro serviços.

* Um grande case é o caso da Netflix, que tem vários projetos que o Spring Boot simplificou muito. Essa é a história do Spring e do Spring Boot. Por isso recomendamos fortemente, se você é um desenvolvedor Java, você vai trabalhar com desenvolvimento de aplicações Java, vai montar uma API REST, vai trabalhar com micro serviços, Spring Boot e Spring Cloud é o canal, é o que está sendo utilizado no mercado, tanto no Brasil quanto no exterior.

<h1>Aula 02 - Publicando Endpoint</h1>

* Uso da anotação @ResponseBody

* Como vimos no vídeo anterior, foi necessário adicionar a anotação @ResponseBody em cima do método lista, na classe controller. Qual o objetivo dessa anotação?

* Indicar ao Spring que o retorno do método deve ser devolvido como resposta

* Por padrão, o Spring considera que o retorno do método é o nome da página que ele deve carregar, mas ao utilizar a anotação @ResponseBody, indicamos que o retorno do método deve ser serializado e devolvido no corpo da resposta.

* Ao invés de devolvermos uma lista de tópicos, vamos criar outra classe que representa só os dados que quero devolver nesse endpoint. E essa classe é só uma classe de valor, que só tem aqueles atributos que quero devolver. Geralmente o pessoal utiliza o padrão DTO, data transfer object, ou muita gente também chama de VO, value object, para esse tipo de classe.

* Nesse treinamento, vamos utilizar o padrão, vamos chamar de DTO. Ao invés de devolver uma lista de tópicos, vou devolver uma lista de tópico DTO. Ele reclama, porque não existe essa classe no nosso projeto, então vamos ter que criar.

* Módulo Spring Boot DevTools

* No vídeo anterior, vimos como adicionar o módulo Spring Boot DevTools ao nosso projeto. Qual a vantagem de se utilizar esse módulo do Spring Boot?

* Evitar a necessidade de reiniciar o servidor manualmente a cada mudança no código da aplicação

* O módulo DevTools inclui ferramentas utilitárias no projeto, dentre elas a Automatic Restart, que reinicia o servidor automaticamente ao detectar alterações no código fonte da aplicação.

<h2>O que é REST?</h2>

* Continuando nosso treinamento de API REST com Spring Boot. No vídeo de hoje vou falar um pouco sobre esse modelo, chamado de REST. Esse vídeo é opcional, se você já conhece o modelo ou não tem interesse em conhecer, você pode pular para a próxima aula, porque nele não vamos adicionar nada à nossa API.

* Como vamos construir uma API REST, é importante conhecer alguns conceitos desse modelo. REST é uma abreviação para representational state transfer, transferência do estado representacional. É um nome meio esquisito, mas nós vamos entender qual a ideia desse modelo.

* Ele nada mais é do que um modelo de arquitetura para sistemas distribuídos. Toda a arquitetura que você tem em um sistema conversando com outro é um sistema distribuído. O criador desse modelo REST, o Roy Fielding, que foi um dos criadores do protocolo HTTP, em 2000 escreveu sua tese de doutorado, onde ele citava alguns dos modelos que poderiam ser utilizados para arquitetura de sistemas distribuídos. Dentre esses modelos estava o REST.

* A ideia dele é que tenho dois sistemas conversando, com transferência de informações via rede. Como eu poderia projetar esse sistema para ter uma boa performance, escalabilidade, sem evitar alguns probleminhas?

* Ele foi a base para a evolução do protocolo HTTP. Hoje a web é toda baseada nesse modelo REST. Depois de muitos anos, o pessoal percebeu que poderiam usar esse modelo para fazer integração de sistemas pela web. Quando iam fazer integração via web, utilizavam aquele webservice no modelo SOAP, só que ele era todo baseado em xml, um pouco complexo, e o pessoal começou a pensar que poderiam usar o próprio REST, já que temos uma arquitetura distribuída baseada na web, no protocolo HTTP, e quero ter flexibilidade. Eles começaram a montar APIs seguindo esse modelo para fazer integração entre sistemas web.

* Quando falamos de uma aplicação web, o que podemos aproveitar do modelo REST? Existem alguns conceitos importantes que podemos trazer para a nossa API. O primeiro conceito é o de recursos.

* Toda aplicação gerencia coisas. Se pensarmos no nosso fórum, ele gerencia alunos, tópicos, respostas, cursos, matrículas, entre outras informações. Essas coisas que o sistema gerencia, que uma aplicação gerencia, no modelo REST é chamado de recurso. No nosso caso, esses seriam os recursos da nossa aplicação.

* Como eu tenho vários recursos que a aplicação vai manipular, preciso de alguma maneira diferenciar um do outro. E a maneira que usamos para isso é utilizando o conceito de URI. Ou seja, cada recurso vai ter uma URI, um identificador único. Do aluno, poderia ser /alunos. Tópicos, /tópicos. Resposta, /respostas. Curso, /cursos. Essa URI é o identificador único de cada recurso. Com isso, consigo diferenciar qual recurso que a API vai manipular.

* Mas mesmo assim ainda tenho um problema. Imagine que estou disparando uma requisição para a URI /alunos. Ou seja, o cliente quer que o servidor manipule o recurso de aluno dele. Mas o que você quer fazer nesse recurso? Posso fazer várias manipulações. Posso devolver todos os recursos, criar um novo recurso, atualizar, excluir. Preciso diferenciar qual a operação, como quero manipular aquele recurso.

* Para tratar dessa parte de manipulação entra outro conceito do HTTP, que são os verbos, os métodos do HTTP. Por exemplo, podemos usar o GET para fazer leitura. Se eu disparar uma requisição GET para o recurso /alunos é porque quero recuperar os recursos. Se eu disparar um POST quero cadastrar, o PUT é atualizar e o DELETE é para excluir.

* Pelos verbos HTTP consigo diferenciar a manipulação que quero fazer em cima daquele recurso. Só que como essa manipulação vai funcionar? Como o cliente me manda uma representação desse recurso e como a API REST devolve essa representação?

* É aí que entra o R do REST. A API está transferindo uma representação do recurso. Ela recebe e devolve representações de determinados recursos. E essas representações são feitas pelos media types, pelos formatos. Geralmente o pessoal utiliza o JSON, mas poderia ser qualquer formato, como XML, HTML, TXT, binário, qualquer outro.

* Com isso, consigo representar esses meus recursos. Daí que veio a ideia do nome REST, porque o que o servidor faz é transferir uma representação de um recurso, do estado atual daquele recurso.

* Perceba que no modelo REST a ideia é utilizar os conceitos, as coisas do protocolo HTTP. As URIs, verbos, representações, media types, entre outros.

* Esse foi só um pedacinho do REST, só os principais conceitos. Também existem outros importantes, como por exemplo comunicação stateless. A web é baseada no protocolo HTTP, que segue o modelo REST. Como a web consegue suportar bilhões de usuários conectados ao mesmo tempo? Um dos segredos é a comunicação stateless. Os servidores da internet no mundo não estão guardando estado da comunicação. Ela é toda stateless. Não tenho que ter zilhões de servidores com zilhões de gigabytes de memória para guardar estado.

* A ideia da API REST é também seguir esse modelo de comunicação stateless, sem usar as seções para armazenar dados dos usuários logados.

* Essa foi só uma breve introdução sobre os principais conceitos do modelo REST, já que vamos construir uma API REST, precisamos ter um pouco de entendimento sobre esses conceitos, porque vamos utilizar isso na construção da nossa API.

<h2>Configuraçoes do banco de dados da aplicação</h2>

* Em uma aplicação Spring Boot, onde são declaradas as configurações do banco de dados utilizado por ela?

* No arquivo application.properties

* No arquivo application.properties, devem ser declaradas as configurações da aplicação, inclusive as relacionadas ao banco de dados dela.