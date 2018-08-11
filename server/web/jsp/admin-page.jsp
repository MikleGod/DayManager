<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../assets/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">
    <link href="../css/signin.css" rel="stylesheet">

    <link rel="stylesheet" href="../css/private-room.css">
</head>
<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">${companyName}</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <form action="controller" method="post" name="logout">
                <input type="hidden" name="action" value="logout">
                <button type="submit" class="nav-link" href="#">${signOut}</button>
            </form>
        </li>
    </ul>
</nav>
<div id="1"></div>

<div class="container-fluid">
    <div class="row">

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-part">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom included-part">

                <div class="pc">
                    <p class="h2">
                    <h1>${chooseLang}</h1>
                    <select id="langSelect">
                        <option name="RUS">${russian}</option>
                        <option name="ENG">${english}</option>
                    </select>
                    <button onclick="changeLang()">${choose}</button>
                    </p>


                    <div id="admin-users">
                        <ul>
                            <c:forEach var="dto" items="${dtos}">
                                <li id="user ${dto.user.id}">
                                    <c:out value="${nick}: ${dto.user.nickname}"/>
                                    <button onclick="changeDisplay('user div ${dto.user.id}')">${watchActivities}</button>
                                    <div id="user div ${dto.user.id}" style="display: none">
                                        <button onclick="changeDisplay('add act ${dto}')">${addActivity}</button>
                                        <div style="display: none" id="add act ${dto}">
                                            <h1>${chooseActivity}</h1>
                                            <select id="select_user_${dto.user.id}">
                                                <c:forEach var="act" items="${all_activities}">
                                                    <option name="${act}">${act}</option>
                                                </c:forEach>
                                            </select>
                                            <button onclick="addActivity(${dto.user.id})">${add}</button>
                                        </div>
                                        <ul>
                                            <c:forEach var="activity" items="${dto.activities}">
                                                <li id="activity ${activity.id}">
                                                        ${activity}
                                                    <button onclick="deleteActivity('${dto.user.id}', '${activity}')">${delete}</button>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

            </div>
        </main>
    </div>
</div>


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/libs/jquery/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ajax_.js"></script>
<script src="/js/popper.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
<script src="/js/private-room.js"></script>

</body>
</html>