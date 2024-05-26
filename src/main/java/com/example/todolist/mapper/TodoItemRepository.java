package com.example.todolist.mapper;

import com.example.todolist.model.TodoItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

  List<TodoItem> findAllByTaskContaining(String keyword);
}
