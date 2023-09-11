package com.merchandise.eeblog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.merchandise.eeblog.dto.ResponseDTO;
import com.merchandise.eeblog.model.Board;
import com.merchandise.eeblog.model.Reply;
import com.merchandise.eeblog.model.User;
import com.merchandise.eeblog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private HttpSession session;

    @PostMapping(value = "/api/board")
    public ResponseDTO<Integer> save(@RequestBody Board board) {
        boolean isSession = boardService.글쓰기(board);
        if (isSession)
            return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
        else
            return new ResponseDTO<Integer>(HttpStatus.BAD_REQUEST.value(), 0);
    }

    @DeleteMapping(value = "/api/board/{id}")
    public ResponseDTO<Integer> delete(@PathVariable int id) {
        boardService.글삭제(id);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping(value = "/api/board/{id}")
    public ResponseDTO<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정(id, board);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping(value = "/api/board/{boardId}/reply")
    public ResponseDTO<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply) {
        User user = (User) session.getAttribute("principal");

        log.info("user : {}", user);
        if (user != null) {
            boardService.댓글쓰기(user, boardId, reply);
            return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
        } else {
            return new ResponseDTO<Integer>(HttpStatus.BAD_REQUEST.value(), 0);
        }

    }

    @DeleteMapping(value = "/api/board/{boardId}/reply/{replyId}")
    public ResponseDTO<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

}
