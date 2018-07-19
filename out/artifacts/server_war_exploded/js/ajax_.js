


//main events
//------------------------------------------------------------------------------------------------------------


function onSubmitRegisterForm() {
    var mail, password, nickname;
    mail = $("#inputEmail").val();
    password = $("#inputPassword").val();
    nickname = $("#inputNickname").val();
    if (/**checkMailIsVacant(mail)*/true) {
        $("#inputEmail").val("");
        $("#inputPassword").val("");
        $("#inputNickname").val("");
        registerUser(mail, nickname, password);
    } else {
        alert("Error");
        return false;
    }
    return true;
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
        dataType : "json",                     // тип загружаемых данных
        type: "POST",
        data: "action=" + REGISTRATION_ACTION + "&mail=" + mail +
             "&password=" + password + "&nickname=" + nickname + JSON_ACTION

    });
}

function isUserExistsWith(mail) {


    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === XMLHttpRequest.DONE && xhttp.status === 200) {
            //parse answer
            return true;
        }
        return false;
    };

    xhttp.open('POST', 'controller', false);

    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhttp.send("action=checkMail&mail=" + mail + JSON_ACTION);

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