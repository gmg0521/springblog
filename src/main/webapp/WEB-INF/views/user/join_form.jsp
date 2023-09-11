<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>
<main>
    <div class="container m-3">
        <form>
            <div class="form-group">
                <label for="username">Username(사용자 아이디)</label>
                <input type="text" id="username" class="form-control" />
                <button id="btn-check" class="btn btn-success m-2">중복 확인</button>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" class="form-control" />
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" class="form-control" />
            </div>
        </form>
        <button id="btn-save" class="btn btn-primary">회원가입</button>
    </div>
</main>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
