package com.merchandise.eeblog.service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.merchandise.eeblog.dto.ReplySaveRequestDTO;
import com.merchandise.eeblog.model.Board;
import com.merchandise.eeblog.model.User;
import com.merchandise.eeblog.repository.BoardRepository;
import com.merchandise.eeblog.repository.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

// @Autowired와 동일한 것 두가지

/*
 * 1.생성자 초기화
 * public class BoardService {
 * private BoardRepository boardRepository;
 * private ReplyRepository replyRepository;
 *
 * public BoardErvice(BoardRepository bRepo, ReplyRepository rRepo) {
 * this.boardRepository = bRepo;
 * this.replyRepository = rRepo;
 * }
 */

/*
 * 2. @RequiredArgsConstrutor
 * Constructor에 Argument가 필요한 경우에는 다 주입해줘라
 * 초기화되지 않는 것은 생성자를 만들때 초기화 해줘라. => 초기화를 해줘야한다(final)
 *
 * @RequiredArgsConstructor
 * public class BoardService {
 * private final BoardRepository boardRepository;
 * private final ReplyRepository replyRepository;
 * }
 */

public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HttpSession session;

    @Transactional
    public boolean 글쓰기(Board board) {
        if (session.getAttribute("principal") != null) {
            User user = (User) session.getAttribute("principal");
            board.setCount(0);
            board.setUser(user);
            boardRepository.save(board);
            return true;
        } else {
            log.info("session2 : " + session.getAttribute("principal"));
            return false;
        }

    }

    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board 상세보기(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("요청한 게시판을 찾을 수 없습니다.");
        });
        board.setCount(board.getCount() + 1);
        boardRepository.save(board);
        return board;
    }

    // 자기 아이디일때만 삭제버튼 보이게
    @Transactional
    public void 글삭제(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정(int id, Board requesBoard) {
        // 영속화
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("요청하신 게시판을 찾을 수 없습니다");
        }); // 영속화 완료

        board.setTitle(requesBoard.getTitle());
        board.setContent(requesBoard.getContent());

        // boardRepository.save(board);
        // 해당 함수 종료 시 (서비스가 종료될 때) 트랜잭션이 종료된다. 이때 더티체킹 - 자동업데이트가 된다.
        // DB가 flush된다. 즉, Commit 된다.
    }

    // DTO로 파라미터를 받아서 처리 가능.
    // 규모가 크면 DTO로 처리하는 것이 좋다.
    @Transactional
    public int 댓글쓰기(User user, ReplySaveRequestDTO requestReply) {
        // Board board =
        // boardRepository.findById(requestReply.getBoardId()).orElseThrow(() -> {
        // return new IllegalArgumentException("댓글쓰기 실패 : 게시글의 아이디를 찾을 수 없습니다.");
        // });

        // board.setCount(board.getCount() - 1);

        // boardRepository.save(board);

        // 방법 1
        // reply.update(user, board, requestReply.getContent());

        // 방법 2
        // Reply reply = Reply.builder()
        // .user(user).board(board)
        // .content(requestReply.getContent())
        // .build();

        // 방법 1,2를 사용할 때 저장
        // replyRepository.save(reply);

        // 요 라인 하나로 위에 코드를 모두 함축 시킬 수 있다.
        int result = replyRepository.mySave(requestReply.getUserId(), requestReply.getBoardId(),
                requestReply.getContent());

        return result;
    }

    @Transactional
    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
