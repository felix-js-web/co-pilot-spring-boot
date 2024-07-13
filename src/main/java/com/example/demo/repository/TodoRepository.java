package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@code TodoRepository} interface extends the {@link JpaRepository} interface,
 * providing CRUD operations for {@link Todo} entities.
 *
 * This repository interface allows for the direct execution of Create, Read, Update,
 * and Delete (CRUD) operations on Todo items within the database, without the need
 * for explicit implementation. The Spring Data JPA framework automatically implements
 * this interface at runtime.
 *
 * @see Todo The Todo entity class that this repository manages.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}