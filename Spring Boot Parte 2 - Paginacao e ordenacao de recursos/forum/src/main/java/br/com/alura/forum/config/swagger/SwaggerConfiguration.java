package br.com.alura.forum.config.swagger;

import br.com.alura.forum.modelo.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket forumApi(){
        return new Docket(DocumentationType.SWAGGER_2) //TIPO DA DOCUMENTACAO
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum")) //A PARTIR DE QUAL PACOTE VAI LER AS CLASSES DO NOSSO PROJETO
                .paths(PathSelectors.ant("/**")) //QUAIS ENDPOINTS É PARA ELE ANALISAR - TUDO
                .build()
                .ignoredParameterTypes(Usuario.class)//IGNORAR TODAS AS URLS QUE TRABALHAM COM A CLASSE DO USUÁRIO
                .globalOperationParameters(Arrays.asList( //ADICIONA PARAMETROS GLOBAIS EM TODOS OS ENDPOINTS
                        new ParameterBuilder()
                        .name("Authorization") //NOME DO PARAMETRO
                        .description("Header para token JWT") //DESCRICAO
                        .modelRef(new ModelRef("string")) //O TIPO DELE
                        .parameterType("header") //ONDE ELE SERÁ ENVIADO
                        .required(false) //SE É NECESSÁRIO
                        .build()));
    }

}
