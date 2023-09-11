package com.merchandise.eeblog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Reply {
  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; // 시퀀스 , auto_increment

  @Column(nullable = false, length = 200)
  private String content;

  @ManyToOne
  @JoinColumn(name = "boardId")
  private Board board;
  // 하나의 게시글에는 여러개의 답변이 있다.

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;
  // 한 사용자가 여러개의 답변을 만들 수 있다.

  @CreationTimestamp
  private Timestamp createDate;

}