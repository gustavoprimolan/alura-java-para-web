package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//HABILITA OS CAMPOS DE PAGINACAO PARA O SPRING PEGAR E PASSAR PARA O SPRING DATA
@EnableSpringDataWebSupport
//HABILITA O USO DE CACHE NA APLICACAO
@EnableCaching
//HABILITA O SWAGGER NO PROJETO
@EnableSwagger2
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
