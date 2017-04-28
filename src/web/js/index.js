// Index.js.
// Will make the button. when clicked send an HTTP POST request to my webserver
// that contains what was written in the textbox.

$(document).ready(function () {
    var Button = $("#button-send");

    Button.click( function() { performNotification(); });
});

function performNotification() {
    var Textbox = $("#textbox-input");

    $.post("localhost:8080", { notification : Textbox.val()}, function () {
        alert("Notification Sent");
    });
}
