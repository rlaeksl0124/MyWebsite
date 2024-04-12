<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="loginOutLink" value="${sessionScope.userId==null? '/user/login' : '/user/logout' }"/>
<c:url var="loginOut" value="${sessionScope.userId==null? 'Login' : 'Logout' }"/>

<!DOCTYPE html>
<html>
<head>
    <title>W3.CSS Template</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
        .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
        .fa-anchor,.fa-coffee {font-size:200px}
        .readPage { display: flex; flex-direction: column; justify-content: center; align-items: center}
        .readPage form {
            display: flex;
            flex-direction: column;
        }
    </style>
</head>
<body>
<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-red w3-card w3-left-align w3-large">
        <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
        <a href="<c:url value='/'/>" class="w3-bar-item w3-button w3-padding-large w3-white">Home</a>
        <a href="<c:url value='/board/list'/>" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Board</a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Sigh up</a>
        <a href="${loginOutLink}" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="loginOn">${loginOut}</a>
    </div>


    <!-- Navbar on small screens -->
    <div id="navDemo"
         class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">

    </div>
</div>

<!-- Header -->
<header class="w3-container w3-red w3-center"
        style="padding: 128px 16px">
</header>
<script>
    let msg = "${msg}";
    if(msg=="WRT_ERR") alert("등록에 실패하였습니다.")
</script>
<div class="readPage">
    <h1>게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h1>
    <form action="/ch3/board/list" id="form">
        <input name="bno" type="hidden" value="${boardDto.bno}" readonly="readonly" >
        <input name="title" type="text" value="${boardDto.title}" placeholder="제목을 입력해 주세요" ${mode=="new" ? '' :'readonly="readonly"'}>
        <textarea name="content" rows="20" placeholder="내용을 입력해 주세요" ${mode=="new" ? '' :'readonly="readonly"'}>${boardDto.content}</textarea>
        <div>
            <button type="button" id="writeBtn">글쓰기</button>
            <button type="button" id="modifyBtn">수정</button>
            <button type="button" id="deleteBtn">삭제</button>
            <button type="button" id="listBtn">목록</button>
        </div>
    </form>
</div>

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-center w3-opacity">
    <div class="w3-xlarge w3-padding-32">
        <i class="fa fa-facebook-official w3-hover-opacity"></i> <i
            class="fa fa-instagram w3-hover-opacity"></i> <i
            class="fa fa-snapchat w3-hover-opacity"></i> <i
            class="fa fa-pinterest-p w3-hover-opacity"></i> <i
            class="fa fa-twitter w3-hover-opacity"></i> <i
            class="fa fa-linkedin w3-hover-opacity"></i>
    </div>
    <p>
        Powered by <a href="https://www.w3schools.com/w3css/default.asp"
                      target="_blank">w3.css</a>
    </p>
</footer>

<script>
    // Used to toggle the menu on small screens when clicking on the menu button
    function myFunction() {
        var x = document.getElementById("navDemo");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }
    $(document).ready(function (){
        $('#listBtn').on("click", function (){
            alert("listBtn clicked");
            location.href = "<c:url value='/board/list?curPage=${curPage}&pageSize=${pageSize}'/>";
        });

        $('#deleteBtn').on("click", function (){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $('form');
            form.attr("action", "<c:url value='/board/remove?curPage=${curPage}&pageSize=${pageSize}'/>");
            form.attr("method", "post");
            form.submit();

        })

        $('#writeBtn').on("click", function (){
            let form = $('form');
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();

        })

        $('#modifyBtn').on("click", function (){
            let form = $('form');
            let isReadOnly = $('input[name=title]').attr('readonly');

            if(isReadOnly=='readonly'){
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly',false);
                $('#modifyBtn').html("등록");
                $("h2").html("수정");
                return;
            }

            form.attr('action', "<c:url value='/board/modify?curPage=${curPage}&pageSize=${pageSize}'/>")
            form.attr("method", "post");
            form.submit();
        })
    })
</script>

</body>
</html>