let index = {
    init: function () {
        $('#btn-save').on('click', () => {
            this.save()
        })
        $('#btn-login').on('click', () => {
            this.login()
        })
        $('#btn-update').on('click', () => {
            this.update()
        })
        $('#btn-check').on('click', () => {
            event.preventDefault()
            this.check()
        })
    },
    check: () => {
        let userName = $('#username').val()
        $.ajax({
            type: 'GET',
            url: '/api/user/' + userName,
            contentType: 'application/json; charset=utf-8',
        })
            .done((resp) => {
                if (resp == 'OK') {
                    alert('사용할 수 있는 아이디입니다!')
                } else {
                    alert('중복된 아이디입니다')
                    $('#username').val('')
                    $('#username').focus()
                }
            })
            .fail((error) => {
                console.log(error)
                alert(JSON.stringify(error))
            })
    },
    save: () => {
        let data = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val(),
        }
        console.log(data) // javascript Object
        console.log(JSON.stringify(data)) // JSON 문자열

        $.ajax({
            type: 'POST',
            url: '/api/user',
            data: JSON.stringify(data),
            contentType: 'application/json;utf-8',
            dataType: 'JSON',
        })
            .done((resp) => {
                if (resp.data == 1) {
                    alert('회원가입이 완료되었습니다!')
                    console.log(resp)
                    location.href = '/'
                } else {
                    alert('아이디가 중복되었습니다!')
                    return
                }
            })
            .fail((err) => {
                console.log(JSON.stringify(err))
            })
    },
    update: () => {
        let data = {
            id: $('#id').val(),
            password: $('#password').val(),
            email: $('#email').val(),
        }
        console.log(data)
        $.ajax({
            type: 'PUT',
            url: '/user',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'JSON',
        })
            .done((resp) => {
                alert('회원 정보 수정이 완료되었습니다!')
                location.href = '/'
            })
            .fail((error) => {
                console.log(error)
                alert(JSON.stringify(error))
            })
    },
    login: () => {
        console.log('user의 로그인 함수 호출 됨')
        let data = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val(),
        }
        console.log(data) // javascript Object
        console.log(JSON.stringify(data)) // JSON 문자열

        $.ajax({
            type: 'POST',
            url: '/api/user/login',
            data: JSON.stringify(data),
            contentType: 'application/json;utf-8',
            dataType: 'JSON',
        })
            .done((resp) => {
                if (resp.data == 1) {
                    alert('로그인이 완료되었습니다!')
                    console.log(resp)
                    location.href = '/'
                } else {
                    alert('회원 정보가 맞지 않습니다!!')
                    console.log(resp)
                    return
                }
            })
            .fail((err) => {
                console.log(JSON.stringify(err))
            })
    },
}

index.init()
