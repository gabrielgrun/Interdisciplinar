/* global http, formData */

var URL = "http://localhost:8080/ecommerce-web/realizar-compra";
function registrarCompra(jsonForm) {
    var pedidoData = {};
    pedidoData["CPEDIDO"] = Number(jsonForm);
    var http = new XMLHttpRequest();
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            parseJson(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            document.getElementById("result").innerHTML = "Erro: " + this.statusText;
        }
        if (this.readyState === 3) {
            document.getElementById("result").innerHTML = "Aguarde...";
        }
    };
}
    http.open("POST", URL);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(pedidoData));

    function parseJson(jsonData) {
        var obj = JSON.parse(jsonData);
        document.getElementById("result").innerHTML = "" + obj.message;
    };

