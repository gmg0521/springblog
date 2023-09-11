package com.merchandise.eeblog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merchandise.eeblog.model.User;
import com.merchandise.eeblog.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public int 회원가입(User user) {
        User userName = userRepository.findByUsername(user.getUsername());
        if (userName != null) {
            return 0;
        }
        userRepository.save(user);
        return 1;
    }

    // C, U, D를 실행할 때 Transactional을 붙여서 실패 시 Rollback이 되게...
    // 그럼 R할 때는? 가끔 조회를 할 때 부정합이 발생한다.(팬텀리드),
    // 팬텀리드(Phantom read)는 다른 트랜잭션에서 수행한 변경 작업에 의해 레코드가 보였다가 안 보였다가 하는 현상이다.
    // 이는 트랜잭션 격리 수준에 위반(Repeatable Read 위반)한다.
    // Repeatable Read는 특정 행을 조회시 항상 같은 데이터를 응답하는 것을 보장하는 격리 수준이다.
    // 무튼 저렇게 되면 데이터에 혼선이 생기니 누군가 조회를 하거나 변경 중이면 이런걸 막아주는 장치가 필요하다.
    // 그러면 R할 때에도 Transaction을 걸어줘서 모두가 동일한 데이터를 볼 수 있게 해야한다!

    // 결론 : 그냥 다 트랜잭션을 걸자...
    @Transactional(readOnly = true)
    public User 로그인(User user) {
        System.out.println("로그인 함수에서 => " + user);

        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public int 중복확인(String username) {
        User userName = userRepository.findByUsername(username);
        if (userName != null) {
            return 0;
        }
        return 1;
    }

    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성 컨텍스트 User오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정한다.
        // User 오브젝트를 SELECT를 통해 DB에서 가져오는 이유는 영속화를 하기 위해서이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update가 반영된다.(UPDATE문을 날려준다.)
        User persistance = userRepository.findById(user.getId()).orElse(null);
        persistance.setPassword(user.getPassword());
        persistance.setEmail(user.getEmail());
        // 이 함수(회원수정 함수)가 종료되면 서비스(UserService)를 종료하고 트랜잭션을 종료하며, Commit이 자동으로 된다.
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 자동으로 update!
        session.setAttribute("principal", persistance);
    }

    public void 회원탈퇴(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
            session.invalidate();
        }
    }
}
