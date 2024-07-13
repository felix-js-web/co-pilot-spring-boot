package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    private Todo sampleTodo;

    @BeforeEach
    void setUp() {
        sampleTodo = new Todo();
        sampleTodo.setTitle("Test Todo");
        sampleTodo.setCompleted(false);
        // Assuming Todo entity has a title and completed status
        todoRepository.save(sampleTodo);
    }

    @Test
    void whenFindById_thenReturnTodo() {
        Todo found = todoRepository.findById(sampleTodo.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo(sampleTodo.getTitle());
    }

    @Test
    void whenFindAll_thenReturnTodoList() {
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos).isNotEmpty();
        assertThat(todos.get(0).getTitle()).isEqualTo(sampleTodo.getTitle());
    }

    @Test
    void whenSave_thenPersistTodo() {
        Todo newTodo = new Todo();
        newTodo.setTitle("New Todo");
        newTodo.setCompleted(true);
        Todo savedTodo = todoRepository.save(newTodo);
        assertThat(savedTodo).isNotNull();
        assertThat(savedTodo.getId()).isNotNull();
        assertThat(savedTodo.getTitle()).isEqualTo("New Todo");
    }

    @Test
    void whenDelete_thenRemoveTodo() {
        Todo toDelete = new Todo();
        toDelete.setTitle("Delete Me");
        toDelete.setCompleted(false);
        todoRepository.save(toDelete);
        assertThat(todoRepository.findById(toDelete.getId())).isNotEmpty();
        todoRepository.delete(toDelete);
        assertThat(todoRepository.findById(toDelete.getId())).isEmpty();
    }
}