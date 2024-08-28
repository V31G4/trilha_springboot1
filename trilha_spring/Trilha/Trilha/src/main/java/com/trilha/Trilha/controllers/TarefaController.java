package com.trilha.Trilha.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trilha.Trilha.tarefa.Tarefa;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	private final Map<Long, Tarefa> tarefas = new HashMap<>();
	private long idCounter = 1;
	
	@GetMapping
	public List<Tarefa> getTarefas(){
		return new ArrayList<>(tarefas.values());
	}
	
	@PostMapping
	public Tarefa addTarefa(@RequestBody Tarefa tarefa) {
		tarefa.setId(idCounter++);
		tarefas.put(tarefa.getId(), tarefa);
		return tarefa;
	}
	
	@PutMapping("/{id}")
	public Tarefa updateTarefa(@PathVariable long id, @RequestBody Tarefa tarefa) {
		if(!tarefas.containsKey(id)) {
			throw new RuntimeException("Tarefa não encontrada.");
		}
		tarefa.setId(id);
		tarefas.put(id, tarefa);
		return tarefa;
	}
	
	@DeleteMapping("/{id}")
	public void deleteTarefa(@PathVariable long id) {
		tarefas.remove(id);
	}
	
}
