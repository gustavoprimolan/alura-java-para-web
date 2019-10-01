package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController //ASSUME QUE TODOS OS MÉTODO VAI TER O @ResponseBody
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

//    @RequestMapping(value="/topicos", method = RequestMethod.GET)
    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else{
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }

    }

//    @RequestMapping(value = "/topicos", method = RequestMethod.POST)
    //Form é um DTO QUE CHEGA DA API
    //RequestBody PEGA NO NOSSO CORPO DA REQUISICAO
    //VALID valida o Bean validation anotado nas clase
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    //PathVariable dizer que o parametro virá pela URL
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){
//        Topico topico = topicoRepository.getOne(id);
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @Transactional // VAI PRECISAR DISSO PARA ELE ATUALIZAR, PARA ELE SABER QUE ENTIDADE MUDOU O VALOR E ATUALIZAR NO BANCO
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository); //NAO HÁ NECESSIDADE DE ATUALIZAR, POIS O JPA JÁ VALIDA QUE OS PARAMETROS FORAM MUDADOS E ATUALIZA AUTOMATICAMENTE NA TRANSACAÇÃO
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    //PADRAO PARA DEIXAR A JPA GERENCIANDO A TRANSAÇÃO - PADRÃO DO SPRING IDEAL PARA OPERAÇÕES SALVAR/ATUALIZAR/EXCLUIR
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
