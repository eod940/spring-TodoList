package com.example.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.mapper.BoardRepository;
import com.example.todolist.model.Board;
import com.example.todolist.util.DateUtil;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found with id " + id));
    }
    
    public List<Board> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        // 날짜를 포맷하는 로직을 추가합니다.
        boards.forEach(board -> {
            String formattedCreatedDate = DateUtil.formatDate(board.getCreatedDate());
            String formattedModifiedDate = DateUtil.formatDate(board.getModifiedDate());
            board.setFormattedCreatedDate(formattedCreatedDate); // 포맷된 생성 날짜를 설정합니다.
            board.setFormattedModifiedDate(formattedModifiedDate); // 포맷된 수정 날짜를 설정합니다.
        });
        return boards;
    }
    
    public List<Board> searchBoards(String search) {
        return boardRepository.findBoardsByTitleContainingOrContentContaining(search, search);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }
    
    public Board updateBoard(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found with id " + id));
        if (boardDetails.getTitle() != null) board.setTitle(boardDetails.getTitle());
        if (boardDetails.getContent() != null) board.setContent(boardDetails.getContent());
        if (boardDetails.getCreatedBy() != null) board.setCreatedBy(boardDetails.getCreatedBy());
        if (boardDetails.getModifiedBy() != null) board.setModifiedBy(boardDetails.getModifiedBy());
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found with id " + id));
        boardRepository.delete(board);
    }
    
}
