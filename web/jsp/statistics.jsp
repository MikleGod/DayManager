<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../assets/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/dashboard.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">

    <link rel="stylesheet" href="../css/private-room.css">
    <link rel="stylesheet" href="../css/statistics.css">

    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script src="/libs/jquery/jquery.js"></script>
</head>
<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">${companyName}</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">${signOut}</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar nav-part">
            <div class="nav-part-sticky">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" onclick="privateClick()" href="#">
                                <span data-feather="home"></span>
                                ${privateCab} <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " onclick="calendarClick()" href="#">
                                <span data-feather="file"></span>
                                ${calendarNav}
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" onclick="statClick()" href="#">
                                <span data-feather="shopping-cart"></span>
                                ${statisticsNav}
                            </a>
                        </li>
                    </ul>


                </div>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-part">
            <nav class="navbar navbar-dark bg-dark flex-md-nowrap p-0 shadow">
                <a class="navbar-brand col-sm-3 col-md-2 mr-0 a" onclick="tmi_cfi('tm-part', 'cf-part')"
                   href="#">${tmi}</a>
                <a class="navbar-brand col-sm-3 col-md-2 mr-0 a" href="#">${chooseTime}</a>
                <a class="navbar-brand col-sm-3 col-md-2 mr-0 a" onclick="tmi_cfi('cf-part', 'tm-part')"
                   href="#">${cfi}</a>
            </nav>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom included-part">
                <div id="tm-part" style="display: block">
                    <div id="tmi-label" style="width: 500px; height: 400px;"></div>
                    <div id="tmi_s">
                        <h1>${timeSpend}</h1>
                        <ul>
                            <c:forEach var="elem" items="${timeManageStatDto}">
                            <li id="tmi ${elem.tmi.id}">${elem.tmi.name}: ${elem.time}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div id="cf-part" style="display: none">
                    <div id="cfi-label" style="width: 500px; height: 400px;"></div>
                    <div id="cfi_s">
                        <h1>${moneySpend}</h1>
                        <ul>
                            <c:forEach var="elem" items="${cashFlowStatDto}">
                                <li id="cfi ${elem.cfi.id}">${elem.cfi.name}: ${elem.cost}</li>
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
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
<script src="/js/private-room.js"></script>


</body>
</html>