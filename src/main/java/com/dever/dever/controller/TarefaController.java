package com.dever.dever.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dever.dever.service.TarefaService;
import com.dever.dever.model.Tarefa;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService ts;

    @GetMapping
    public List<Tarefa> listarTodas(){
        return ts.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id){
        return ts.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarefa salvar(@RequestBody Tarefa tarefa){
        return ts.salvar(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada){
        return ts.buscarPorId(id)
        .map(tarefa->{
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
        
            tarefa.setDataCriacao(tarefaAtualizada.getDataCriacao());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());

            ts.salvar(tarefa);
            return ResponseEntity.ok(tarefa);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        if(ts.buscarPorId(id).isPresent()){
            ts.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
