<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Title</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/registration-popup.css">
    <script src="/libs/jquery/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/ajax_.js"></script>
    <script src="/js/registation-login.js"></script>
</head>
<body>

<!-- Button trigger modal -->


<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/">Company name</a>
    <ul class="navbar-nav px-3 invisible">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 blog-main ">

            <div class="blog-post info">
                <h2 class="card-title">Sample blog post</h2>
                <p class="blog-post-meta">January 1, 2014 by <a href="#">Mark</a></p>

                <p>This blog post shows a few different types of content that's supported and styled with Bootstrap. Basic typography, images, and code are all supported.</p>

                <p>Cum sociis natoque penatibus et magnis <a href="#">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p>
                <blockquote>
                    <p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                </blockquote>
                <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
                <h2>Heading</h2>
                <p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
                <h3>Sub-heading</h3>
            </div><!-- /.blog-post -->


        </div>

        <div class="sign-form">
            <form name="login" class="form-signin" action="controller" method="post">
                <input type="hidden" name="action" value="login" />
                <h1 class="h3 mb-3 font-weight-normal sing-label-2">Please sign in</h1>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" name="mail" id="inputEmailS" class="form-control" placeholder="Email address" required>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="inputPasswordS" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block modal-btn" type="submit">Sign in</button>
                <button type="button" class="btn btn-primary btn-lg modal-btn" data-toggle="modal"
                        data-target="#myModal">
                    Sign on
                </button>
            </form>
        </div>
    </div>

</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <!-- onsubmit="return onSubmitRegisterForm()" -->
                <form class="form-signin" id="registration-form">
                    <img class="mb-4 b-icon" src="assets/android-chrome-512x512.png" alt="" width="72" height="72">
                    <h1 class="h3 mb-3 font-weight-normal sing-label">Please sign on</h1>
                    <label for="inputNickname" class="sr-only">Email address</label>
                    <input type="text" id="inputNickname" class="form-control" placeholder="Nickname" required
                           autofocus>
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" onclick="onSubmitRegisterForm()">Sign up</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!--<script src="js/common.js"></script>-->
</body>
</html>