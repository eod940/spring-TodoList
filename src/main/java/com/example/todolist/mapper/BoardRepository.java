package com.example.todolist.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findBoardsByTitleContainingOrContentContaining(String title, String content);
}
