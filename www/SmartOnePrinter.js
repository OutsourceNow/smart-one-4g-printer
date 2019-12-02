var exec = require('cordova/exec');

module.exports = {

    printerInit: function (resolve, reject) {
        exec(resolve, reject, "SmartOnePrinter", "printerInit", []);
    },
}