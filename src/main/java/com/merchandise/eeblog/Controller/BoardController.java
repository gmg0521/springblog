package com.merchandise.eeblog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.merchandise.eeblog.model.Board;
import com.merchandise.eeblog.service.BoardService;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping(value = { "", "/", "/index" })
    public String index(Model model,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("boards", boardService.글목록(pageable));
        return new String("index");
    }

    @GetMapping(value = "/board/write_form")
    public String saveForm() {
        return new String("board/write_form");
    }

    @GetMapping(value = "/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        Board board = boardService.상세보기(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping(value = "/board/{id}/update_form")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.상세보기(id));
        return new String("board/update_form");
    }

}
