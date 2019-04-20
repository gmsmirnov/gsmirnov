/**
 * Validates if "login", "password", "email" or "description" are empty and alerts.
 *
 * @returns {boolean} true if there is no one of those fields is empty.
 */
function validate() {
    var result = true;
    if (document.getElementsByName("login")[0].value == '') {
        alert("Please correct login.");
        result = false;
    } else if (document.getElementsByName("password")[0].value == '') {
        alert("Please correct password.");
        result = false;
    } else if (document.getElementsByName("email")[0].value == '') {
        alert("Please correct email.");
        result = false;
    } else if (document.getElementsByName("description")[0].value == '') {
        alert("Please correct description.");
        result = false;
    }
    return result;
}

/**
 * Append a new user to the table. Consists of two parts: first validates input, second create POST request
 * to "jsonController".
 */
function add() {
    if (validate()) {
        createUser();
    }
}

/**
 * Creates POST request to jsonController and transfers new user's specification.
 * @returns {boolean} false
 */
function createUser() {
    $.ajax("./json", {
        method : "post",
        data : {
            login : $("#login").val(),
            password : $("#password").val(),
            email : $("#email").val(),
            country : $("#country").val(),
            city : $("#city").val()
        },
        complete : function(data) {
            console.log(JSON.parse(data.responseText));
            var result =
                "<thead>" +
                "   <tr>" +
                "       <th>Login</th>" +
                "       <th>Email</th>" +
                "       <th>Country</th>" +
                "       <th>City</th>" +
                "   </tr>" +
                "</thead>" +
                "<tbody>";
            var users = JSON.parse(data.responseText);
            for (var index = 0; index < users.length; index++) {
                result += "<tr>" +
                    "                <th>" + users[index].login + "</th>" +
                    "                <th>" + users[index].email + "</th>" +
                    "                <th>" + users[index].country + "</th>" +
                    "                <th>" + users[index].city + "</th>" +
                    "            </tr>";
            }
            result += "</tbody>";
            var table = document.getElementById("table");
            table.innerHTML = result;
        }
    });
    return false;
}

/**
 * F5 - reloads page and current table.
 */
$(
    $.ajax("./json", {
        method : "get",
        complete : function(data) {
            console.log(JSON.parse(data.responseText));
            var result =
                "<thead>" +
                "   <tr>" +
                "       <th>Login</th>" +
                "       <th>Email</th>" +
                "       <th>Country</th>" +
                "       <th>City</th>" +
                "   </tr>" +
                "</thead>" +
                "<tbody>";
            var users = JSON.parse(data.responseText);
            for (var index = 0; index < users.length; index++) {
                result += "<tr>" +
                    "                <th>" + users[index].login + "</th>" +
                    "                <th>" + users[index].email + "</th>" +
                    "                <th>" + users[index].country + "</th>" +
                    "                <th>" + users[index].city + "</th>" +
                    "            </tr>";
            }
            result += "</tbody>";
            var table = document.getElementById("table");
            table.innerHTML = result;
        }
    })
);
