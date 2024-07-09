package com.example.demo.web;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ReactiveController {

    private final TodoRepository todoRepository;

    public ReactiveController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    @GetMapping("/mono")
    public Mono<List<Todo>> monoExample() {

        return Mono.just(todoRepository.findAll());
    }

    @GetMapping("/flux")
    public Flux<String> fluxExample() {
        return Flux.just("This is a Flux response - 1", "This is a Flux response - 2", "This is a Flux response - 3");
    }
}