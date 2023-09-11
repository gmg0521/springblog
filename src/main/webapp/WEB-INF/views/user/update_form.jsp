<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Empty Space for prettier save -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Empty Space for prettier save -->
<%@ include file="../layout/header.jsp" %>
<main>
    <div class="container mt-2">
        <form>
            <input type="hidden" value="${principal.id}" id="id" />
            <div class="form-group">
                <label for="username">User Name</label>
                <input type="text" id="username" class="form-control" value="${principal.username}" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" class="form-control" value="${principal.password}" />
            </div>
            <div class="form-group">
                <label for="email">이메일(e-mail)</label>
                <input type="email" id="email" class="form-control" value="${principal.email}" />
            </div>
        </form>
        <button id="btn-update" class="btn btn-primary">회원수정 완료</button>
    </div>
</main>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>
