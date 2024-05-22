package com.example.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.todolist.model.Board;
import com.example.todolist.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String getAllBoards(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Board> boards;
        if (search != null && !search.isEmpty()) {
            boards = boardService.searchBoards(search);
        } else {
            boards = boardService.getAllBoards();
        }
        model.addAttribute("boards", boards);
        return "boards"; // Thymeleaf 템플릿 이름
    }
    
    @GetMapping("/board-form")
    public String showBoardForm(Model model) {
        // 필요한 로직을 구현하여 모델에 데이터를 추가하고
        // board-form.html로 이동합니다.
        return "board-form"; // Thymeleaf 템플릿 이름
    }

    //일반
//    @PostMapping
//    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
//    	Board insBoard = boardService.createBoard(board);
//        return ResponseEntity.ok(insBoard);
//    }
    //예외 추가
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody Board board) {
        try {
//        	throw new Exception("에러");
            Board insBoard = boardService.createBoard(board);
            return ResponseEntity.ok(insBoard);
        } catch (Exception e) {
            // 예외 발생 시 클라이언트에게 오류 메시지와 함께 400 Bad Request 상태 코드 반환
            return ResponseEntity.badRequest().body("등록에 실패했습니다: " + e.getMessage());
        }
    }

    //코드값만 반환
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") Long id) {
        Board board = boardService.getBoardById(id);
        return ResponseEntity.ok(board);
    }
    
    //화면추가
    @GetMapping("/detail/{id}")
    public String showBoardDetail(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "detail"; // 상세 페이지 템플릿 이름
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "edit-form"; // 수정 폼 템플릿 이름
    }

    //postman에서 json형태로 수정시
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoardJson(@PathVariable("id") Long id, @RequestBody Board boardDetails) {
        Board updatedBoard = boardService.updateBoard(id, boardDetails);
        return ResponseEntity.ok(updatedBoard);
    }
    
    @PutMapping("/update/{id}")
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute Board updatedBoard) {
        // 수정 로직 구현
        boardService.updateBoard(id, updatedBoard);
        return "redirect:/api/boards/detail/" + id; // 상세 페이지로 리다이렉트
    }

    //일반
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long id) {
//  	boardService.deleteBoard(id);
//      return ResponseEntity.ok().build();
//  }
    //리다이렉트 추가
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
    	boardService.deleteBoard(id);
        return "redirect:/api/boards"; // 삭제 후 목록 페이지로 리다이렉트
    }
}