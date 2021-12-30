var exec = require('cordova/exec');

module.exports = {

    printerInit: function (resolve, reject) {
        exec(resolve, reject, "SmartOnePrinter", "printerInit", []);
    },
    printString: function (text, qrCodeData, resolve, reject) {
        exec(resolve, reject, "SmartOnePrinter", "printString", [text, qrCodeData]);
    },
     printBitmap: function (base64Data, width, height, resolve, reject) {
        exec(resolve, reject, "SmartOnePrinter", "printBitmap", [base64Data, width, height]);
    },
    printBarCode: function (barCodeData, resolve, reject) {
        exec(resolve, reject, "SmartOnePrinter", "printBarCode", [barCodeData]);
    },
    printQRCode: function (qrCodeData, resolve, reject) {
        exec(resolve, reject, "SmartOnePrinter", "printQRCode", [qrCodeData]);
    },
}