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
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
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
                            <a class="nav-link" href="#">
                                <span data-feather="home"></span>
                                Private cab <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <span data-feather="file"></span>
                                Calendar
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="../html-patterns/statistics.html">
                                <span data-feather="shopping-cart"></span>
                                statistics
                            </a>
                        </li>
                    </ul>


                </div>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 main-part">
            <nav class="navbar navbar-dark bg-dark flex-md-nowrap p-0 shadow">
                <a class="navbar-brand col-sm-3 col-md-2 mr-0 a" onclick="cfi_tmi('tm', 'cf')" href="#">tmi</a>
                <a class="navbar-brand col-sm-3 col-md-2 mr-0 a" href="#" data-toggle="modal"
                   data-target="#myModal">choose day</a>
                <a class="navbar-brand col-sm-3 col-md-2 mr-0 a" onclick="tmi_cfi('cf', 'tm')" href="#">cfi</a>
            </nav>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom included-part">
                <div id="tm" style="display: block">
                    <ul>
                        <li>Транпорт 8,00-9,00<button>изменить</button></li>
                        <li>еда 18,00-00,00<button>изменить</button></li>
                        <li><button>добавить</button></li>
                    </ul>
                </div>
                <div id="cf" style="display: none">
                    <ul>
                        <li>транспорт 8<button>изменить</button></li>
                        <li>еда 20<button>изменить</button></li>
                        <li>кино 18<button>изменить</button></li>
                        <li><button>добавить</button></li>
                    </ul>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="calendar-popup">
                                    <table id="calendar3" style="margin-right: auto; margin-left: auto;">
                                        <thead>
                                        <tr><td colspan="4"><select>
                                            <option value="0">Январь</option>
                                            <option value="1">Февраль</option>
                                            <option value="2">Март</option>
                                            <option value="3">Апрель</option>
                                            <option value="4">Май</option>
                                            <option value="5">Июнь</option>
                                            <option value="6">Июль</option>
                                            <option value="7">Август</option>
                                            <option value="8">Сентябрь</option>
                                            <option value="9">Октябрь</option>
                                            <option value="10">Ноябрь</option>
                                            <option value="11">Декабрь</option>
                                        </select><td colspan="3"><input type="number" value="" min="0" max="9999" size="4">
                                        <tr><td>Пн<td>Вт<td>Ср<td>Чт<td>Пт<td>Сб<td>Вс
                                        <tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
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

<script src="../js/calendar-scr.js"></script>
</body>
</html>