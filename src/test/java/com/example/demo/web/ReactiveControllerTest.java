import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.web.ReactiveController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class ReactiveControllerTest {

    private ReactiveController reactiveController;
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository = Mockito.mock(TodoRepository.class);
        reactiveController = new ReactiveController(todoRepository);
    }

    @Test
    @DisplayName("monoExample returns all Todo items")
    void monoExampleReturnsAllTodos() {
        List<Todo> todos = Arrays.asList(new Todo(), new Todo());
        when(todoRepository.findAll()).thenReturn(todos);

        Mono<List<Todo>> result = reactiveController.monoExample();

        StepVerifier.create(result)
                .expectNext(todos)
                .verifyComplete();
    }

    @Test
    @DisplayName("monoExample returns empty list when no Todo items exist")
    void monoExampleReturnsEmptyListWhenNoTodosExist() {
        when(todoRepository.findAll()).thenReturn(Arrays.asList());

        Mono<List<Todo>> result = reactiveController.monoExample();

        StepVerifier.create(result)
                .expectNext(Arrays.asList())
                .verifyComplete();
    }

    @Test
    @DisplayName("fluxExample returns predefined string responses")
    void fluxExampleReturnsPredefinedStrings() {
        Flux<String> expectedFlux = Flux.just("This is a Flux response - 1", "This is a Flux response - 2", "This is a Flux response - 3");

        Flux<String> result = reactiveController.fluxExample();

        StepVerifier.create(result)
                .expectNext("This is a Flux response - 1")
                .expectNext("This is a Flux response - 2")
                .expectNext("This is a Flux response - 3")
                .verifyComplete();
    }
}