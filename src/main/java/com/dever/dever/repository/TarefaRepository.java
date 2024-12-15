package com.dever.dever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dever.dever.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long>{

}
