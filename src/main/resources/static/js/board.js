let index = {
    init: function () {
        $('#btn-write').on('click', () => {
            this.save()
        })
        $('#btn-delete').on('click', () => {
            this.deleteById()
        })
        $('#btn-update').on('click', () => {
            this.update()
        })
        $('#btn-reply-save').on('click', () => {
            this.replySave()
        })
    },
    save: function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
        }
        $.ajax({
            type: 'POST',
            url: '/api/board',
            data: JSON.stringify(data),
            contentType: 'application/json;utf-8',
            dataType: 'JSON',
        })
            .done(function (resp) {
                if (resp.data == 1) {
                    alert('글쓰기 완료!')
                    location.href = '/'
                } else {
                    alert('세션이 만료되었습니다.')
                    location.href = '/user/login_form'
                }
            })
            .fail(function (error) {
                console.log(JSON.stringify(error))
            })
    },
    update: function () {
        let id = $('#id').val()
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
        }
        console.log(data)
        $.ajax({
            type: 'PUT',
            url: '/api/board/' + id,
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'JSON',
        })
            .done((resp) => {
                alert('글 수정이 완료되었습니다!')
                location.href = '/board/' + id
            })
            .fail((error) => {
                console.log(error)
                alert(JSON.stringify(error))
            })
    },
    deleteById: function () {
        var id = $('#id').text()
        $.ajax({
            type: 'DELETE',
            url: '/api/board/' + id,
        })
            .done((response) => {
                alert('게시글이 삭제되었습니다!')
                location.href = '/'
            })
            .fail((error) => {
                alert(JSON.stringify(error))
            })
    },
    replySave: function () {
        let data = {
            boardId: $('#boardId').val(),
            content: $('#reply-content').val(),
        }
        console.log(data)

        $.ajax({
            type: 'POST',
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
        })
            .done((resp) => {
                if (resp.data == 1) {
                    console.log(resp)
                    alert('댓글이 등록되었습니다!')
                    location.href = `/board/${data.boardId}`
                } else {
                    alert('세션이 만료되었습니다!')
                    location.href = '/user/login_form'
                }
            })
            .fail((err) => {
                console.log(err)
                alert(JSON.stringify(err))
            })
    },
    replyDelete: function (boardId, replyId) {
        console.log(replyId)

        $.ajax({
            type: 'DELETE',
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
        })
            .done((resp) => {
                console.log(resp)
                alert('댓글 삭제 완료!')
                location.href = `/board/${boardId}`
            })
            .fail((err) => {
                console.log(err)
                alert(JSON.stringify(err))
            })
    },
}

index.init()
