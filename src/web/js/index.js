// Index.js.
// Will make the button. when clicked send an HTTP POST request to my webserver
// that contains what was written in the textbox.

$(document).ready(function () {
    button = $("#button-send");
    textbox = $("#textbox-input");

    button.click(function () { performNotification(); });
});

function performNotification() {
    var req = new API("send-message");

    var reqObj = {
        message: textbox.val()
    };

    req.setRequestObject(reqObj);
    req.setResponseHandler(function (err, resObj) {

        if (err) {
            alert("There was an error");
            return;
        }

        alert("Yes");
    });
    req.send()

}
