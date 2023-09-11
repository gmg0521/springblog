package com.merchandise.eeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.merchandise.eeblog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    // 인터페이스 내에서는 public은 생략가능하다.
    // 실제 데이터베이스의 필드명을 기입한다.
    @Modifying
    @Query(value = "INSERT INTO reply (user_Id, board_Id, content, create_date) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mySave(int userId, int boardId, String content);
    // 업데이트 된 행의 개수를 리턴해준다. 0 -> 업데이트 없음, -1 -> 오류, 1 -> 1개가 업데이트 됨

}
