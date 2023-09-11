package com.merchandise.eeblog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
public class Board {
  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된
  private int id; // 시퀀스 , auto_increment

  @Column(nullable = false, length = 200)
  private String title;

  @Lob // 대용량 데이터
  private String content; // 섬머노트 라이브러리 <html>태그

  // @ColumnDefault("0")
  private int count; // 조회수

  @ManyToOne(fetch = FetchType.EAGER) // Many=Board, User=One , 연관관계를 설정한다.
  @JoinColumn(name = "userId") // DB에는 userId가 들어간다.
  private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
  // 자바와 데이터베이스가 충돌난다.

  /*
   * 게시글은 삭제할 수 없다. 외래키가 연결되어 있어서 그렇다.
   * 게시글은 삭제할때 댓글은 어떻게 할 것인지 정의가 되어있지 않다.
   * 게시글을 삭제할때 댓글을 남겨놓을 것인가?
   * cascade option 전파속성
   * cascade=CascadeType.ALL -- persist+remove
   * cascade=CascadeType.REMOVE -- 보드 게시글을 지울때 댓글도 삭제하겠다.
   * cascade=CascadeType.PERSIST -- 보드오브젝트를 넣을때 댓글도 담아서 저장한다. 실제 DB에 반영이 된다.
   * jps cascade
   * option을 검색해보자.
   */
  // @JoinColun(name="replyId")
  @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties({ "board" })
  @OrderBy("id desc")
  private List<Reply> replies;

  @CreationTimestamp
  private Timestamp createDate;
}