package com.merchandise.eeblog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.merchandise.eeblog.dto.ResponseDTO;
import com.merchandise.eeblog.model.RoleType;
import com.merchandise.eeblog.model.User;
import com.merchandise.eeblog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserApiController {

    private enum statusCode {
        FAIL,
        SUCCESS
    }

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping(value = "api/user")
    public ResponseDTO<Integer> save(@RequestBody User user) {
        log.info("User API save() 호출됨");

        // 실제로 DB에 insert를 하고 return이 기대값(1)을 반환한다면 정상적으로 save 된 것이다.
        user.setRole(RoleType.ADMIN);
        Integer result = userService.회원가입(user);
        if (result == statusCode.FAIL.ordinal()) {
            return new ResponseDTO<Integer>(HttpStatus.BAD_REQUEST.value(), result);
        }
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), result); // ResponseDTO를 통해 프론트에는 상태만 보여준다.
    }

    @GetMapping(value = "api/user/{username}")
    public ResponseEntity<String> check(@PathVariable String username) {
        int result = userService.중복확인(username);
        log.info("result : {}", result);

        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("DUPLICATED");
        }
    }

    @PostMapping(value = "/api/user/login")
    public ResponseDTO<Integer> login(@RequestBody User user) {
        log.info("User API login() 호출됨");

        User principal = userService.로그인(user);

        if (principal != null) {
            session.setAttribute("principal", principal);
        } else {
            return new ResponseDTO<Integer>(HttpStatus.NOT_FOUND.value(),
                    statusCode.FAIL.ordinal());
        }
        return new ResponseDTO<Integer>(HttpStatus.OK.value(),
                statusCode.SUCCESS.ordinal());
    }

    // key=value, x-www-form-urlencode의 형태로 데이터를 받고싶으면 @ReqeustBody를 생략
    // JSONN 형태롤 받고 싶을 때 @ReqeustBody를 붙인다.
    @PutMapping(value = "/user")
    public ResponseDTO<Integer> update(@RequestBody User user) {
        userService.회원수정(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 돼있다.
        // 하지만 세션 값은 변경되지 않은 상태이기 때문에 우리가 직접 세션 값을 변경해준다.
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }
}