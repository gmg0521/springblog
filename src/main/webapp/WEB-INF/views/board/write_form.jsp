<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp" %>
<main>
    <div class="container">
        <form class="container" method="post">
            <div class="mb-3">
                <label class="form-label">제목(Title)</label>
                <input type="text" class="form-control" name="title" id="title" />
            </div>
            <div class="mb-3">
                <label class="form-label">내용(Content)</label>
                <textarea class="form-control summernote" rows="3" name="content" id="content"></textarea>
            </div>
            <a class="btn btn-danger" href="/">Back</a>
        </form>
        <button type="submit" class="btn btn-primary" id="btn-write">글쓰기완료</button>
    </div>

    <script src="/js/board.js"></script>
</main>
<jsp:include page="/WEB-INF/views/layout/footer.jsp" flush="false" />
<script>
    $(function () {
        $('.summernote').summernote({
            placeholder: 'Hello Bootstrap 5',
            tabsize: 2,
            height: 600,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'italic', 'underline', 'clear']],
                ['fontname', ['fontname']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'hr']],
                ['view', ['fullscreen', 'codeview']],
                ['help', ['help']],
            ],
        })
        $('.dropdown-toggle').dropdown()
    })
</script>
