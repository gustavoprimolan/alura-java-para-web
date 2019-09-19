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

