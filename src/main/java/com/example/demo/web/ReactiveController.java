package com.example.demo.web;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The {@code ReactiveController} class handles web requests to demonstrate reactive programming concepts
 * using Spring WebFlux. It provides endpoints to fetch Todo items as a Mono or a Flux stream.
 */
@CrossOrigin

@RestController
public class ReactiveController {

    private final TodoRepository todoRepository;

    /**
     * Constructs a {@code ReactiveController} with the specified {@code TodoRepository}.
     *
     * @param todoRepository the repository used for accessing Todo items
     */
    public ReactiveController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Returns a {@code Mono<List<Todo>>} containing all Todo items.
     * This endpoint demonstrates how to return a single or empty value asynchronously.
     *
     * @return a Mono containing a list of all Todo items
     */
    @GetMapping("/mono")
    public Mono<List<Todo>> monoExample() {
        return Mono.just(todoRepository.findAll());
    }

    /**
     * Returns a {@code Flux<String>} containing a series of string responses.
     * This endpoint demonstrates how to return multiple values over time.
     *
     * @return a Flux containing a series of predefined string responses
     */
    @GetMapping("/flux")
    public Flux<String> fluxExample() {
        return Flux.just("This is a Flux response - 1", "This is a Flux response - 2", "This is a Flux response - 3");
    }
}