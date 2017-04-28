// Index.js.
// Will make the button. when clicked send an HTTP POST request to my webserver
// that contains what was written in the textbox.

$(document).ready(function () {
    var button = $("#button-send");

    Button.click(function () { performNotification(); });
});

function performNotification() {
    var textbox = $("#textbox-input");

    $.post("http://localhost:8080/notification", { notification : Textbox.val() }, function () {
        alert("Notification Sent");
    });
}
