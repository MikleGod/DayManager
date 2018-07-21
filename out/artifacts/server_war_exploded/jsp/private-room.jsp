<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                            <a class="nav-link active" href="#">
                                <span data-feather="home"></span>
                                Private cab <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="file"></span>
                                Calendar
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
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
                        <a  class="pc-a" onclick="changeDisplay('change-privates')" href="#">change-privates</a>
                    </p>
                    <div style="display: none" id="change-privates">
                        <form class="form-signin">
                            <label for="inputEmail" class="sr-only">Current password</label>
                            <input type="email" id="inputEmail" class="form-control" placeholder="Current password" required autofocus>
                            <label for="inputPassword" class="sr-only">New password</label>
                            <input type="password" id="inputPassword" class="form-control" placeholder="New password" required>
                            <button class="btn btn-lg btn-primary btn-block">Change</button>
                        </form>
                        <!-- TODO changePassword script-->
                    </div>
                    <p class="h2">
                        <a class="pc-a" onclick="changeDisplay('look-act')" href="#">look at activities</a>
                    </p>
                    <div id="look-act" style="display: none" >
                        <p class="h2">add tmi</p>
                        <p class="h2">add cfi</p>
                        <p class="h2">watch statistics</p>
                    </div>
                    <p class="h2">
                        <a class="pc-a"  onclick="changeDisplay('admin-tmi')" href="#">admin TMI</a>
                    </p>
                    <div style="display: none" id="admin-tmi">
                        <ul class="flex-column nav">
                            <li class="nav-item">test1 <button>change</button><button>delete</button></li>
                            <li class="nav-item">test2<button>change</button><button>delete</button></li>
                            <li class="nav-item">test3<button>change</button><button>delete</button></li>
                            <li class="nav-item">test4<button>change</button><button>delete</button></li>
                        </ul>
                        <button>add</button>
                    </div>
                    <p class="h2">
                        <a  class="pc-a" onclick="changeDisplay('admin-cfi')" href="#">admin CFI</a>
                    </p>
                    <div style="display: none" id="admin-cfi">
                        <ul>
                            <li>test1 <button>change</button><button>delete</button></li>
                            <li>test2<button>change</button><button>delete</button></li>
                            <li>test3<button>change</button><button>delete</button></li>
                            <li>test4<button>change</button><button>delete</button></li>
                        </ul>
                        <button>add</button>
                    </div>
                </div>

            </div>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../js/jquery-slim.min.js"><\/script>')</script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
<script src="../js/private-room.js"></script>

</body>
</html>