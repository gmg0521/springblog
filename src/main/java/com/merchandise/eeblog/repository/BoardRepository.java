package com.merchandise.eeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merchandise.eeblog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
