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