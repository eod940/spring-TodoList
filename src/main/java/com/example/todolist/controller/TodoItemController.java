package com.example.todolist.controller;

import com.example.todolist.dto.TodoFormDto;
import com.example.todolist.dto.TodoResponseDto;
import com.example.todolist.model.TodoItem;
import com.example.todolist.service.TodoItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoItemController {

  private final TodoItemService todoItemService;

  @GetMapping("/list")
  public ResponseEntity<List<TodoItem>> getTodoItemList(
      @RequestParam(value = "task", required = false) String task
  ) {
    // 검색 키워드가 없으면 모두 가져오기
    if (task == null) {
      return ResponseEntity.ok(todoItemService.getAllList());
    }
    // 검색 키워드가 있으면 검색한 결과 모두 가져오기
    return ResponseEntity.ok(todoItemService.searchList(task));
  }

  @PostMapping
  public ResponseEntity<TodoItem> createTodoItem(
      @RequestBody TodoFormDto todoFormDto
  ) {
    return ResponseEntity.ok(todoItemService.createTodo(todoFormDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TodoItem> updateTodoItem(
      @PathVariable("id") Long todoId,
      @RequestBody TodoFormDto todoFormDto
  ) {
    return ResponseEntity.ok(todoItemService.updateTodo(todoId, todoFormDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodoItem(
      @PathVariable("id") Long todoId
  ) {
    todoItemService.deleteTodo(todoId);
    return ResponseEntity.noContent().build();
  }

  // Thymeleaf 템플릿 반환
  @GetMapping
  public String getAllItems(Model model) {
    model.addAttribute("todos", todoItemService.getAllDataForThymeleaf());
    return "todo-list";
  }
}
