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
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Company name</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>
<div id="1"></div>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar nav-part">
            <div class="nav-part-sticky">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" onclick="privateClick()" href="#">
                                <span data-feather="home"></span>
                                Private cab <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="calendarClick()" href="#">
                                <span data-feather="file"></span>
                                Calendar
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/statistics.html">
                                <span data-feather="shopping-cart"></span>
                                statistics
                            </a>
                        </li>
                    </ul>


                </div>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-part">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom included-part">

                <div class="pc">
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('change-privates')" href="#">change-privates</a>
                    </p>
                    <div style="display: none" id="change-privates">
                        <div class="form-signin">
                            <label for="inputEmail" class="sr-only">Current password</label>
                            <input type="email" id="inputEmail" class="form-control" placeholder="Current password"
                                   required autofocus>
                            <label for="inputPassword" class="sr-only">New password</label>
                            <input type="password" id="inputPassword" class="form-control" placeholder="New password"
                                   required>
                            <button class="btn btn-lg btn-primary btn-block" onclick="changePassword()">Change</button>
                        </div>
                        <!-- TODO changePassword script-->
                    </div>
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('look-act')" href="#">look at activities</a>
                    </p>
                    <div id="look-act" style="display: none">
                        <p class="h2">add tmi</p>
                        <p class="h2">add cfi</p>
                        <p class="h2">watch statistics</p>
                    </div>
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('admin-tmi')" href="#">admin TMI</a>
                    </p>
                    <div style="display: none" id="admin-tmi">

                        <button href="#" data-toggle="modal"
                                data-target="#addTM">добавить
                        </button>
                        <ul class="flex-column nav" id="tmi-ul">
                            <c:forEach var="elem" items="${timeManagerItems}" varStatus="i">
                                <li class="tmi ${elem.id}">
                                    <c:out value="${elem.name}"/>
                                    <button onclick="deleteTmi(${elem.id})">delete</button>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('admin-cfi')" href="#">admin CFI</a>
                    </p>
                    <div style="display: none" id="admin-cfi">
                        <button href="#" data-toggle="modal"
                                data-target="#addCF">добавить
                        </button>
                        <ul id="cfi-ul">
                            <c:forEach var="elem" items="${cashFlowItems}" varStatus="i">
                                <li class="cfi ${elem.id}">
                                    <c:out value="${elem.name}"/>
                                    <button onclick="deleteCfi(${elem.id})">delete</button>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

            </div>
        </main>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addCF" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div id="add_cfi">
                    <input type="text" id="cfi_name" title="cfi_name">
                    <button onclick="addCfi()">Go</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addTM" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div id="add_tmi">
                    <input type="text" id="tmi_name" title="tmi_name">
                    <button onclick="addTmi()">Go</button>
                </div>
            </div>
        </div>
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