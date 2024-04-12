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
<script>
    let msg = "${msg}";
    if(msg=="WRT_OK") alert("성공적으로 등록되었습니다.")
    if(msg=="DEL_OK") alert("성공적으로 삭제되었습니다.")
    if(msg=="DEL_ERR") alert("삭제에 실패하였습니다.")
</script>
<!-- Header -->
<header class="w3-container w3-red w3-center"
        style="padding: 128px 16px">
    <button type="button" id="writeBtn">글쓰기</button>
    <table>
        <tr>
            <td class="number">번호</td>
            <td class="title">제목</td>
            <td class="writer">작성자</td>
            <td class="regdate">등록일</td>
            <td class="viewCnt">조회수</td>
        </tr>

        <c:forEach var="boardDto" items="${list}">
            <tr>
                <td class="number">${boardDto.bno}</td>
                <td class="title"><a href="<c:url value='/board/read?bno=${boardDto.bno}&curPage=${curPage}&pageSize=${pageSize}'/>">${boardDto.title}</a></td>
                <td class="writer">${boardDto.writer}</td>
                <td class="regdate">${boardDto.reg_date}</td>
                <td class="viewCnt">${boardDto.view_cnt}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div>
        <c:if test="${ph.showPrev}">
            <a href="<c:url value='/board/list?curPage=${ph.naviStart-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.naviStart}" end="${ph.naviEnd}">
            <a href="<c:url value='/board/list?curPage=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a href="<c:url value='/board/list?curPage=${ph.naviEnd+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
        </c:if>
    </div>
</header>

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
        $('#writeBtn').on("click", function (){
            alert("writeBtn clicked");
            location.href="<c:url value='/board/write'/>";
        })
    })


</script>

</body>
</html>