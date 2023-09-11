<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>
<main>
    <div class="container mt-2">
        <form class="p-3">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" class="form-control" autocomplete="on" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" />
            </div>

            <div class="form-group form-check">
                <label class="form-check-label"> <input class="form-check-input" id="chk-id-save" type="checkbox" />Remember me </label>
            </div>
        </form>
        <button id="btn-login" class="btn btn-primary">로그인</button>
    </div>
</main>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
