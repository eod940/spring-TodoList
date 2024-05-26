package com.example.todolist.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TodoStatusType {

  COMPLETE("완료"),
  DOING("진행중");

  private final String description;
}
