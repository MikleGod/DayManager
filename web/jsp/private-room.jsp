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
        <nav class="col-md-2 d-none d-md-block bg-light sidebar nav-part">
            <div class="nav-part-sticky">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" onclick="privateClick()" href="#">
                                <span data-feather="home"></span>
                                ${privateCab}<span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="calendarClick()" href="#">
                                <span data-feather="file"></span>
                                ${calendarNav}
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/statistics.html">
                                <span data-feather="shopping-cart"></span>
                                ${statisticsNav}
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
                        <h1>${chooseLang}</h1>
                        <select id="langSelect">
                            <option name="RUS">${russian}</option>
                            <option name="ENG">${english}</option>
                        </select>
                    <button onclick="changeLang()">${choose}</button>
                    </p>

                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('change-privates')" href="#">${changePassword}</a>
                    </p>
                    <div style="display: none" id="change-privates">
                        <div class="form-signin">
                            <label for="inputEmail" class="sr-only">${currentPassword}</label>
                            <input type="email" id="inputEmail" class="form-control" placeholder="${currentPassword}"
                                   required autofocus>
                            <label for="inputPassword" class="sr-only">${newPassword}</label>
                            <input type="password" id="inputPassword" class="form-control" placeholder="${newPassword}"
                                   required>
                            <button class="btn btn-lg btn-primary btn-block" onclick="changePassword()">${change}</button>
                        </div>
                        <!-- TODO changePassword script-->
                    </div>
                    <!--<p class="h2">
                        <a class="pc-a" onclick="changeDisplay('look-act')" href="#">${lookActivities}</a>
                    </p>
                    <div id="look-act" style="display: none">
                        <p class="h2">add tmi</p>
                        <p class="h2">add cfi</p>
                        <p class="h2">watch statistics</p>
                    </div>-->
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('admin-tmi')" href="#">${showTMI}</a>
                    </p>
                    <div style="display: none" id="admin-tmi">

                        <button href="#" data-toggle="modal"
                                data-target="#addTM">${add}
                        </button>
                        <ul class="flex-column nav" id="tmi-ul">
                            <c:forEach var="elem" items="${timeManagerItems}" varStatus="i">
                                <li class="tmi ${elem.id}">
                                    <c:out value="${elem.name}"/>
                                    <button onclick="deleteTmi(${elem.id})">${delete}</button>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('admin-cfi')" href="#">${showCFI}</a>
                    </p>
                    <div style="display: none" id="admin-cfi">
                        <button href="#" data-toggle="modal"
                                data-target="#addCF">${add}
                        </button>
                        <ul id="cfi-ul">
                            <c:forEach var="elem" items="${cashFlowItems}" varStatus="i">
                                <li class="cfi ${elem.id}">
                                    <c:out value="${elem.name}"/>
                                    <button onclick="deleteCfi(${elem.id})">${delete}</button>
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
                    <h1>${typeCFIName}</h1>
                    <input type="text" id="cfi_name" title="cfi_name">
                    <button onclick="addCfi()">${add}</button>
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
                    <h1>${typeTMIName}</h1>
                    <input type="text" id="tmi_name" title="tmi_name">
                    <button onclick="addTmi()">${add}</button>
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