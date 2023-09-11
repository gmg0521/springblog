package com.merchandise.eeblog.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.merchandise.eeblog.service.UserService;

// @Controller에서 기본 맵핑은 localhost:8088/~~.jsp 이다
@Controller
public class UserController {
    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/join_form")
    public String joinForm() {
        return new String("user/join_form");
    }

    @GetMapping(value = "/user/login_form")
    public String loginForm() {
        return new String("user/login_form");
    }

    @GetMapping(value = "/user/logout")
    public String logout() {
        session.invalidate();
        // index만 넣어서 부르게 되면 jsp 파일만 불러오기 때문에 BoardController에서 index로 GetMapping한 메소드가
        // 작동하지 않는다.
        // 그럼 페이지네이션만 뜨고 게시글 목록이 안 뜸
        // 그렇기 때문에 redirect로 index에 직접 접속해야지 정상적으로 보여진다.
        return new String("redirect:/index");
    }

    @GetMapping(value = "/user/update_form")
    public String updateForm(Model model) {
        model.addAttribute("principal", session.getAttribute("principal"));
        return new String("user/update_form");
    }

    @GetMapping(value = "/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.회원탈퇴(id);
        return "/user/login_form";
    }

}
