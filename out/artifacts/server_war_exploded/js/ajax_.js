//main events
//------------------------------------------------------------------------------------------------------------


function onSubmitRegisterForm() {
    var mail, password, nickname;
    mail = $("#inputEmail").val();
    password = $("#inputPassword").val();
    nickname = $("#inputNickname").val();
    $.ajax({
        url: '/controller',
        dataType: "json",
        type: "POST",
        data: "action=" + CHECK_MAIL_ACTION + "&mail=" + mail + JSON_ACTION,
        success: function (data, text) {
            if (data.isExists) {
                registerUser(mail, nickname, password);
                alert("success!");
            } else {
                alert("user already exists");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText + " " + textStatus + " " + errorThrown);
        },
        async: true
    });
}

function statClick() {
    $.ajax({
        url: "/controller",
        type: "POST",
        data: "action=statistics",
        success: function (data) {
            $("body").html(data);
        },
        dataType: "html"
    });
}

function privateClick() {
    $.ajax({
        url: "/controller",
        type: "POST",
        data: "action=private",
        success: function (data) {
            $("body").html(data);
        },
        dataType: "html"
    });
}

function calendarClick() {
    $.ajax({
        url: "/controller",
        type: "POST",
        data: "action=calendar",
        success: function (data) {
            $("body").html(data);
        },
        dataType: "html"
    });
}

function checkMailIsVacant(mail) {
    return !isUserExistsWith(mail);
}


function onValidateSignInForm(mail, password) {

}

function checkPassword(password) {

}

function addTMPI() {
    let timeBegin = $("#timeBegin").val();
    let timeEnd = $("#timeEnd").val();
    let tmiId = $("#tmiSelect").find(':selected').attr('name');
    let tmiName = $("#tmiSelect").val();
    let date = $("#date").val();
    if (validateTMPI(timeBegin, timeEnd)) {
        $.ajax({
            url: "/controller",
            data: "timeBegin=" + timeBegin + "&timeEnd=" + timeEnd + "&tmiId=" + tmiId + "&date=" + date + "&action=add_tmpi" + JSON_ACTION,
            type: "POST",
            success: function () {
                $("#tm-ul").append("<li>" + tmiName + ": " + timeBegin + " - " + timeEnd + "</li>");
            },
            error: function () {
                alert("Sorry, we've got error");
            }
        })
    } else {
        alert("Validation error!");
    }
}

function addCFPI() {
    let cost = $("#cost").val();
    let cfiId = $("#cfiSelect").find(':selected').attr('name');
    let cfiName = $("#cfiSelect").val();
    let date = $("#date").val();
    if (validateTMPI(timeBegin, timeEnd)) {
        $.ajax({
            url: "/controller",
            data: "cost=" + cost + "&cfiId=" + cfiId + "&date=" + date + "&action=add_cfpi" + JSON_ACTION,
            type: "POST",
            success: function () {
                $("#cf-ul").append("<li>" + cfiName + ": " + cost + "</li>");
            },
            error: function () {
                alert("Sorry, we've got error");
            }
        });
    } else {
        alert("Validation error!");
    }
}

function changePassword() {
    let oldPass = $("#inputEmail").val();
    let password = $("#inputPassword").val();
    let message = "Success";
    $("#inputEmail").val("");
    $("#inputPassword").val("");
    if (validatePassword2(oldPass, password)) {
        $.ajax({
            url: "/controller",
            data: "oldPass=" + oldPass + "&password=" + password + "&action=change_privates" + JSON_ACTION,
            type: "POST",
            dataType: "json",
            success: function (answ) {
                alert(answ.message);
            },
            error: function () {
                alert("Sorry, we've got error");
            }
        });
    } else {
        alert("validation error!");
    }
}

//Validation
//-----------------------------------------------------------------------------------------------------
function validateMail(mail) {
    return mail !== "";
}

function validatePassword1(password) {
    return password !== "";

}


function validatePassword2(newPass, oldPass) {
    return newPass !== "" && oldPass !== "";

}

function validateNickname(nickname) {

}

function validateTMPI(timeEnd, timeBegin) {
    return timeBegin !== "" && timeEnd !== "";
}

//AJAX
//-------------------------------------------------------------------------------------------------------------

const REGISTRATION_ACTION = "registration";
const CHECK_MAIL_ACTION = "check_mail";
const JSON_ACTION = "&json=true";

function registerUser(mail, nickname, password) {
    $.ajax({
        url: '/controller',             // указываем URL и
        dataType: "json",                     // тип загружаемых данных
        type: "POST",
        data: "action=" + REGISTRATION_ACTION + "&mail=" + mail +
        "&password=" + password + "&nickname=" + nickname + JSON_ACTION
    });
}

function isUserExistsWith(mail) {
    let isExists = true;
    $.ajax({
        url: '/controller',
        dataType: "json",
        type: "POST",
        data: "action=" + CHECK_MAIL_ACTION + "&mail=" + mail + JSON_ACTION,
        success: function (data, text) {
            hui = text;
            isExists = data.isExists;
            alert("isUserExists " + data.isExists);
        },
        async: false
    });
    alert("isUserE " + isExists);
    return isExists;
}

function addTmi() {
    let tmiName = $("#tmi_name").val();

    if (validateCFIName(tmiName)) {
        $.ajax({
            url: "/controller",
            data: "tmi_name=" + tmiName + "&action=add_tmi" + JSON_ACTION,
            type: "POST",
            dataType: "json",
            success: function (ans) {
                $("#tmi-ul").append("<li class='tmi " + ans.id + "'>" + tmiName + "<button onclick='deleteCfi(" + ans.id + ")'>delete</button></li>");
            },
            error: function () {
                alert("Sorry, we've got error");
            }
        });
    } else {
        alert("Validation error!");
    }
}

function validateCFIName(cfiName) {
    return true;
}

function addCfi() {
    let cfiName = $("#cfi_name").val();

    if (validateCFIName(cfiName)) {
        $.ajax({
            url: "/controller",
            data: "cfi_name=" + cfiName + "&action=add_cfi" + JSON_ACTION,
            type: "POST",
            dataType: "json",
            success: function (ans) {
                $("#cfi-ul").append("<li class='cfi " + ans.id + "'>" + cfiName + "<button onclick='deleteCfi(" + ans.id + ")'>delete</button></li>");
            },
            error: function () {
                alert("Sorry, we've got error");
            }
        });
    } else {
        alert("Validation error!");
    }
}

function deleteTmi(tmi) {
    $.ajax({
        url: "/controller",
        data: "tmi_id=" + tmi + "&action=delete_tmi" + JSON_ACTION,
        type: "POST",
        success: function () {
            $("li.tmi" + "." + tmi).remove();
        },
        error: function () {
            alert("Sorry, we've got error");
        }
    });
}

function deleteCfi(cfi) {
    $.ajax({
        url: "/controller",
        data: "cfi_id=" + cfi + "&action=delete_cfi" + JSON_ACTION,
        type: "POST",
        success: function () {
            $("li.cfi" + "." + cfi).remove();
        },
        error: function () {
            alert("Sorry, we've got error");
        }
    });
}

function changeTmi(tmi) {

}

function changeCfi(cfi) {

}

function getTmFor(year, month, day) {

}

function changeTmFor(year, month, day) {

}

function getCfFor(year, month, day) {

}

function changeCfFor(year, month, day) {

}

//Parsing JSON
//-------------------------------------------------------------------------------------------------------------

function parseIsUserExists(json) {

}