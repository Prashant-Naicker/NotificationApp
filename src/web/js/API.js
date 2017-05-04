// API class that will be the base for request/response handling

var API = function (a) {
    var self = this;

    self.reqObj = null;
    self.responseHandler = null;

    self.setRequestObject = function (reqObj) { self.reqObj = reqObj; };

    self.setResponseHandler = function (handler) {
        self.responseHandler = handler;
    };

    self.send = function () {
        $.ajax({
            url: "http://localhost:8080/" + a,
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(self.reqObj),
            timeout: 10000,
            success: function (data) {
                if (typeof self.responseHandler !== "function") { return; }
                self.responseHandler(null, data);
            },
            error: function (data) {
                if (typeof self.responseHandler !== "function") { return; }
                self.responseHandler(data, null);
            }
        });
    };
};
