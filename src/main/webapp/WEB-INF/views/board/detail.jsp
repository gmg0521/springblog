<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!---->
<%@ include file="../layout/header.jsp" %>
<main>
    <div class="conatiner m-2">
        <button onclick="history.back()" class="btn btn-secondary">돌아가기</button>
        <hr />
        <c:if test="${board.user.id == principal.id}">
            <a href="/board/${board.id}/update_form" class="btn btn-warning">수정</a>
            <button class="btn btn-danger" id="btn-delete">삭제</button>
        </c:if>
    </div>
    <div class="m-3">
        글번호 : <span id="id"><i>${board.id}</i></span> |
        <!-- Empty Space for prettier save -->
        작성자 : <span><i>${board.user.username}</i></span> |
        <!-- Empty Space for prettier save -->
        조회수 : <span><i>${board.count}</i></span>
    </div>
    <hr />
    <div class="m-3 form-group">
        <p>제목(title)</p>
        <h2 id="title">${board.title}</h2>
    </div>
    <hr />
    <div class="m-3 form-group">
        <p>내용(Content)</p>
        <div id="content">${board.content}</div>
    </div>
    <hr />
    <div class="card">
        <form>
            <input type="hidden" value="${principal.id}" id="userId" />
            <input type="hidden" value="${board.id}" id="boardId" />
            <div class="card-body">
                <textarea id="reply-content" class="form-control"></textarea>
            </div>
            <div class="card-footer">
                <button class="btn btn-primary" id="btn-reply-save">등록</button>
            </div>
        </form>
    </div>
    <div class="card">
        <div class="card-header">댓글리스트</div>
        <ul class="list-group" id="reply-box">
            <c:forEach var="reply" items="${board.replies}">
                <li class="list-group-item d-flex justify-content-between" id="reply-1">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자: ${reply.user.username}</div>
                        <c:if test="${reply.user.id eq principal.id}">
                            <!-- 이게 왜 빨간줄? -->
                            <button onclick="index.replyDelete(${board.id}, ${reply.id})" class="badge ms-3">삭제</button>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
