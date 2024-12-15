package com.dever.dever.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dever.dever.model.Tarefa;
import com.dever.dever.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tr;

    public List<Tarefa> listarTodas(){
        return tr.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id){
        return tr.findById(id);
    }

    public Tarefa salvar(Tarefa tarefa){
        return tr.save(tarefa);
    }

    public void excluir(Long id){
        tr.deleteById(id);
    }
}
