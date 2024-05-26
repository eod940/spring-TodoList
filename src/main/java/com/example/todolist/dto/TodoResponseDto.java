package com.example.todolist.dto;

import com.example.todolist.constant.TodoStatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
  private Long todoId;
  private String task;
  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dueDate;  // 마감 날짜
  private TodoStatusType status;  // 할 일의 상태 (DOING: 진행중, COMPLETE: 완료)
  private Integer priority;  // 우선순위 (1, 2, 3)
  private Integer remainDays;  // 현재 날짜와 비교해 남은 일 수
}
