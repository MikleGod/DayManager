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
            if (data.isExists){
                registerUser(mail,nickname, password);
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


function checkMailIsVacant(mail) {
    return !isUserExistsWith(mail);
}


function onValidateSignInForm(mail, password) {

}

function checkPassword(password) {

}

function changePassword(password, newPassword) {

}

//Validation
//-----------------------------------------------------------------------------------------------------
function validateMail(mail) {

}

function validatePassword(password) {

}

function validateNickname(nickname) {

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
            alert("isUserExists "+data.isExists);
        },
        async: false
    });
    alert("isUserE " + isExists);
    return isExists;
}

function addTmi(tmi) {

}

function addCfi(cfi) {

}

function deleteTmi(tmi) {

}

function deleteCfi(cfi) {

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