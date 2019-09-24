package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//@Controller
@RestController //ASSUME QUE TODOS OS MÉTODO VAI TER O @ResponseBody
public class TopicosController {

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(){
        Topico topico = new Topico("Dúvida","Duvida com Spring", new Curso("Spring", "Programacao"));

        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }

}
