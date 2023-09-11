<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>예섬 블로그</title>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Bootstrap CSS v5.3.1 -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
            crossorigin="anonymous"
        />
        <!-- jQeury v3.7.1 -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

        <!-- include summernote css/js -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.20/summernote-bs5.min.css"
            integrity="sha512-ngQ4IGzHQ3s/Hh8kMyG4FC74wzitukRMIcTOoKT3EyzFZCILOPF0twiXOQn75eDINUfKBYmzYn2AA8DkAk8veQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.20/summernote-bs5.min.js"
            integrity="sha512-6F1RVfnxCprKJmfulcxxym1Dar5FsT/V2jiEUvABiaEiFWoQ8yHvqRM/Slf0qJKiwin6IDQucjXuolCfCKnaJQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
    </head>
    <body>
        <!-- sticky-top을 넣으면 상단바 고정됨 -->
        <!-- nav에 안 넣고 헤더에 넣는 이유는 positino:sticky의 특징상 가장 가까운 부모 요소랑 붙는 특징이 있어서 임. -->
        <header class="sticky-top">
            <nav class="navbar navbar-expand-lg navbar-dark bg-black">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Home</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarText">
                        <c:choose>
                            <%-- 로그인이 되있으면 --%>
                            <c:when test="${empty sessionScope.principal}">
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/user/login_form">로그인</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/user/join_form">회원가입</a>
                                    </li>
                                </ul>
                            </c:when>
                            <%-- 로그인이 안 되있으면 --%>
                            <c:otherwise>
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/board/write_form">글쓰기</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/user/update_form">회원정보</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/user/logout">로그아웃</a>
                                    </li>
                                    <hr>
                                    <li class="nav-item">
                                        <a href="/user/delete/${principal.id}" class="nav-link">회원탈퇴</a>
                                    </li>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
