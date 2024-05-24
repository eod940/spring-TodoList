package com.example.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoFormDto {
  private String task;  // 할 일

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dueDate;  // 마감 날짜
  private String status;  // 할 일의 상태 (1: 진행중, 2: 완료)
  private Integer priority;  // 우선순위 (1, 2, 3)
}
