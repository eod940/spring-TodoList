package com.example.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "comnCode")
@Getter
@SuperBuilder
@NoArgsConstructor
public class ComnCode {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long parentId;
  private String code;
  private String label;
}
