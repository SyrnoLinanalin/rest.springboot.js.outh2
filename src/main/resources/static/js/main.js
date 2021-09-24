
$(document).ready(function () {
        class User {
            constructor(id, name, email, username, password, roles) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.username = username;
                this.password = password;
                this.roles = roles;
            }
        }
        class Role {
            constructor(id, name) {
                this.id = id;
                this.name = name;
            }
        }
        run();
        fillAddUser();
        async function fillAddUser() {
            let responseRole = await fetch("http://localhost:8080/admin/role");
            let roles = await responseRole.json();
            let select = $("#user-addform select");
            for (let i = 0; i < roles.length; i++) {
                let option = new Option(roles[i].name, roles[i].id);
                select.append(option);
            }
        }
        async function run() {
            let response = await fetch("http://localhost:8080/admin/allUsers");
            let users = await response.json();

            fillTable(users);
            addEditAndDeleteEventListeners();
        }


        function addEditAndDeleteEventListeners() {
            let buttons = $("table button");
            for (let i = 0; i < buttons.length; i++) {
                buttons[i].addEventListener("click", function () {
                    showModel(buttons[i]);
                });
            }
        }
        document.getElementById("modelButton").addEventListener("click", sendRequest);
        document.getElementById("addButton").addEventListener("click", addUser);
        async function addUser() {
            await fetch($("#user-addform").attr("action"), {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(getUser("#user-addform"))
            });

            clearAddForm();
            run();
            document.getElementById("nav-users_table-link").click();





        }
        function clearAddForm() {
            $("#user-addform .name").val("");
            $("#user-addform .email").val("");
            $("#user-addform .username").val("");
            $("#user-addform .password").val("");
        }
        async function sendRequest() {
            if ($("#modelButton").text() === "Edit") {
                await fetch($("#userInfo form").attr("action"), {
                    method: "POST",
                    headers: {
                        "Content-type": "application/json"
                    },
                    body: JSON.stringify(getUser("#userInfo"))
                });
            } else {
                await fetch($("#userInfo form").attr("action"), {method: "DELETE"});
            }
            run();
            $("#userInfo").modal("hide");
        }
        function getUser(form) {
            let roles = [];
            let i = 0;
            $(form + " option:selected").each(function () {
                roles[i] = new Role(this.value, this.text);
                i++;
            });
            let id = null;
            if (form === "#userInfo") {
                id = $(form + " #id").val();
            }
            return new User(id,$(form + " .name").val(),$(form + " .email").val(), $(form + " .username").val(), $(form + " .password").val(),
                roles);
        }
        function fillTable(users) {
            let tableBody = document.getElementsByTagName("tbody")[0];
            $("#usersTable tbody tr").remove();
            for (let i = 0; i < users.length; i++) {
                let row = tableBody.insertRow();
                let cellId = row.insertCell();
                let cellName = row.insertCell();
                let cellEmail = row.insertCell();
                let cellUsername = row.insertCell();
                let cellPassword = row.insertCell();
                let cellRoles = row.insertCell();
                let cellEdit = row.insertCell();
                let cellDelete = row.insertCell();
                cellId.innerHTML = users[i].id;
                cellName.innerHTML = users[i].name
                cellEmail.innerHTML = users[i].email;
                cellUsername.innerHTML = users[i].username;
                cellPassword.innerHTML = users[i].password;
                let roles = "";
                for (let k = 0; k < users[i].roles.length; k++) {
                    roles += users[i].roles[k].name + " ";
                }
                cellRoles.innerHTML = roles;
                let editButton = document.createElement("BUTTON");
                editButton.innerHTML = "Edit";
                editButton.className = "btn btn-info";
                editButton.value = users[i].id;
                editButton.name = "Edit";
                editButton.type = "button";
                cellEdit.appendChild(editButton);
                let deleteButton = document.createElement("BUTTON");
                deleteButton.innerHTML = "Delete";
                deleteButton.className = "btn btn-danger";
                deleteButton.value = users[i].id;
                deleteButton.name = "Delete";
                deleteButton.type = "button";
                cellDelete.appendChild(deleteButton);
            }
        }
        async function showModel(button) {
            await fillModel(button.value);
            if (button.name === "Edit") {
                getEditModel(button.value);
            } else {
                getDeleteModel(button.value);
            }
            $("#userInfo").modal("show");
        }
        async function fillModel(id) {
            let responseUser = await fetch("http://localhost:8080/admin/" + id);
            let responseRole = await fetch("http://localhost:8080/admin/role");
            let user = await responseUser.json();
            let roles = await responseRole.json();
            $("#userInfo #id").val(user.id);
            $("#userInfo .name").val(user.name);
            $("#userInfo .email").val(user.email);
            $("#userInfo .username").val(user.username);
            $("#userInfo .password").val(user.password);
            let select = $("#userInfo #roles");
            select.empty();
            for (let i = 0; i < roles.length; i++) {
                let option = new Option(roles[i].name, roles[i].id);
                for (let j = 0; j < user.roles.length; j++) {
                    if (roles[i].id === user.roles[j].id) {
                        option.selected = true;
                    }
                }
                select.append(option);
            }
        }
        function getEditModel(id) {
            $("#userInfo .modal-header h3").text("Edit User");
            $("#userInfo .name").prop("disabled", false);
            $("#userInfo .email").prop("disabled", false);
            $("#userInfo .username").prop("disabled", false);
            $("#userInfo .password").prop("disabled", false);
            $("#userInfo .roles").prop("disabled", false);
            $("#userInfo form").attr("action", "http://localhost:8080/admin/edit/" + id );
            let button = $("#userInfo button");
            if (button.hasClass("btn-danger")) {
                button.removeClass("btn-danger");
                button.addClass("btn-primary")
            }
            $("#userInfo .btn-primary").text("Edit");
        }
        function getDeleteModel(id) {
            $("#userInfo .modal-header h3").text("Delete User");
            $("#userInfo .name").prop("disabled", false);
            $("#userInfo .email").prop("disabled", false);
            $("#userInfo .username").prop("disabled", true);
            $("#userInfo .password").prop("disabled", true);
            $("#userInfo #roles").prop("disabled", true);
            $("#userInfo form").attr("action", "http://localhost:8080/admin/delete/" + id );
            let button = $("#userInfo button");
            if (button.hasClass("btn-primary")) {
                button.removeClass("btn-primary");
                button.addClass("btn-danger")
            }
            $("#userInfo .btn-danger").text("Delete");
        }
    }
)



