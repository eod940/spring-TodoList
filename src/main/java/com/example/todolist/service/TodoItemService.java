package com.example.todolist.service;

import com.example.todolist.dto.TodoFormDto;
import com.example.todolist.mapper.TodoItemRepository;
import com.example.todolist.model.TodoItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoItemService {

  private final TodoItemRepository todoItemRepository;

  /*
  GET
  할 일 목록을 모두 가져옵니다.
  없는 경우 null을 반환합니다.
   */
  public List<TodoItem> getAllList() {
    return todoItemRepository.findAll();
  }

  /*
  POST
  할 일을 추가합니다.
   */
  public TodoItem createTodo(TodoFormDto form) {
    return TodoItem.builder()
        .task(form.getTask())
        .status(form.getStatus())
        .dueDate(form.getDueDate())
        .priority(form.getPriority())
        .build();
  }

  /*
  PUT
  할 일을 수정합니다.
  글이 없는 경우 500를 반환합니다.
   */
  public TodoItem updateTodo(Long todoId, TodoFormDto form) {
    TodoItem todoItem = todoItemRepository.findById(todoId)
        .orElseThrow(() -> new RuntimeException("수정할 글을 찾을 수 없습니다."));
    todoItem.update(form);

    return todoItemRepository.save(todoItem);
  }

  /*
  DELETE
  할 일을 삭제합니다.
  글이 없는 경우 500을 반환합니다.
   */
  public void deleteTodo(Long todoId) {
    TodoItem todoItem = todoItemRepository.findById(todoId)
        .orElseThrow(() -> new RuntimeException("삭제할 글을 찾을 수 없습니다."));

    todoItemRepository.delete(todoItem);
  }

  /*
  GET
  인자로 받은 keyword를 이용해 검색합니다.
  없는 경우 null을 반환합니다.
   */
  public List<TodoItem> searchList(String keyword) {
    return todoItemRepository.findAllByTaskContaining(keyword);
  }
}
