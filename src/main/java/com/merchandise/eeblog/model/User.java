package com.merchandise.eeblog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@DynamicInsert // insert시 null인 필드를 제외시켜준다.
public class User {

  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된
  private Long id; // 시퀀스 , auto_increment

  @Column(nullable = false, length = 30, unique = true)
  private String username; // 아이디

  @Column(nullable = false, length = 100) // 123456=>해쉬(비밀번호 암호화)
  private String password; //

  @Column(nullable = false, length = 50)
  private String email;

  @Enumerated(EnumType.STRING)
  @ColumnDefault("'USER'")
  private RoleType role; // Enum을 쓰는게 좋다. admin, user, maneger

  @CreationTimestamp // 시간이 자동 입력
  private Timestamp createDate;

}