package br.com.alura.forum.repository;


import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String nomeCurso);

    //JPQL
    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);

    //SE HOUVER UM CASO ONDE EXISTE AMBIGUIDADE ENTRE OS NOMES DOS ATRIBUTOS
    //POR EXEMPLO: EU TENHO DENTRO DE TOPICO UM ATRIBUTO CHAMADO cursoNome
    //E TAMBÉM UM ATRIBUTO DO TIPO Curso e DENTRO DELE TEM nome
    //PARA DIFERENCIAR É NECESSÁRIO APENAS COLOCAR O UNDERLINE QUE O SPRING
    //IRÁ PEGAR O ATRIBUTO DO TIPO Curso E DENTRO DELE O nome
    //RELACIONAMENTO DELE E O ATRIBUTO DENTRO DO RELACIONAMENTO
//    List<Topico> findByCurso_Nome(String nomeCurso);
}
