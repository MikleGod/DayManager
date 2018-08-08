//main events
//------------------------------------------------------------------------------------------------------------

const ERROR_MESSAGE = "Sorry, we've got error";
const SUCCESS_MESSAGE = "success!";
const USER_EXISTS_MESSAGE = "user already exists";
const VALIDATION_ERROR_MESSAGE = "Validation error!";

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
                alert(SUCCESS_MESSAGE);
            } else {
                alert(USER_EXISTS_MESSAGE);
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
    timeBegin = $("#timeBegin").val("");
    timeEnd = $("#timeEnd").val("");
    let tmiId = $("#tmiSelect").find(':selected').attr('name');
    let tmiName = $("#tmiSelect").val();
    let date = $("#date").text();
    if (validateTMPI(timeBegin, timeEnd)) {
        let query = "timeBegin=" + timeBegin + "&timeEnd=" + timeEnd + "&tmi_id=" + tmiId + "&date=" + date + "&action=add_tmpi" + JSON_ACTION;
        alert(query);
        $.ajax({
            url: "/controller",
            data: query,
            type: "POST",
            success: function () {
                $("#tm-ul").append("<li>" + tmiName + ": " + timeBegin + " - " + timeEnd + "</li>");
            },
            error: function () {
                alert(ERROR_MESSAGE);
            }
        })
    } else {
        alert(VALIDATION_ERROR_MESSAGE);
    }
}

function addCFPI() {
    let cost = $("#cost").val();
    let cfiId = $("#cfiSelect").find(':selected').attr('name');
    let cfiName = $("#cfiSelect").val();
    timeBegin = $("#cost").val("");
    let date = $("#date").val();
    if (validateTMPI(timeBegin, timeEnd)) {
        $.ajax({
            url: "/controller",
            data: "cost=" + cost + "&cfi_id=" + cfiId + "&date=" + date + "&action=add_cfpi" + JSON_ACTION,
            type: "POST",
            success: function () {
                $("#cf-ul").append("<li>" + cfiName + ": " + cost + "</li>");
            },
            error: function () {
                alert(ERROR_MESSAGE);
            }
        });
    } else {
        alert(VALIDATION_ERROR_MESSAGE);
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
                //alert(ERROR_MESSAGE);
            }
        });
    } else {
        alert(VALIDATION_ERROR_MESSAGE);
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
    try {
        let timeEndParts = timeEnd.split("-");
        let timeBeginParts = timeBegin.split("-");
        return timeBegin !== "" &&
            timeEnd !== "" &&
            +timeEndParts[0] >= 0 &&
            +timeEndParts[0] <= 23 &&
            +timeEndParts[1] >= 0 &&
            +timeEndParts[1] <= 59
            +timeBeginParts[0] >= 0 &&
            +timeBeginParts[0] <= 23 &&
            +timeBeginParts[1] >= 0 &&
            +timeBeginParts[1] <= 59 &&
            +timeBeginParts[0] <= +timeEndParts[0] &&
            +timeBeginParts[1] <= +timeEndParts[1];
    } catch (e) {
        return false;
    }
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
        },
        async: false
    });
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
                //alert(ERROR_MESSAGE);
            }
        });
    } else {
        alert(VALIDATION_ERROR_MESSAGE);
    }
}

function validateCFIName(cfiName) {
    return cfiName !== null && cfiName !== "";
}

function changeLang() {

    let lang = $("#langSelect").find(':selected').attr('name');
    $.ajax({
        url: "controller",
        dataType: "html",
        data: "action=change_language&lang=" + lang,
        method: "POST",
        success: function (ans) {
            $("body").html(ans);
        }
    })
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
                //alert(ERROR_MESSAGE);
            }
        });
    } else {
        alert(VALIDATION_ERROR_MESSAGE);
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
            alert(ERROR_MESSAGE);
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
            alert(ERROR_MESSAGE);
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