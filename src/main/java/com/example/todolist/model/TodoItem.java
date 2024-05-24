package com.example.todolist.model;

import com.example.todolist.dto.TodoFormDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "todoItem")
@Getter
@SuperBuilder
@NoArgsConstructor
public class TodoItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long todoId;
  private String task;  // 할 일
  private LocalDate dueDate;  // 마감 날짜
  private String status;  // 할 일의 상태 (1: 진행중, 2: 완료)
  private Integer priority;  // 우선순위 (1, 2, 3)

  public void update(TodoFormDto form) {
    this.task = form.getTask();
    this.dueDate = form.getDueDate();
    this.priority = form.getPriority();
    this.status = form.getStatus();
  }
}
