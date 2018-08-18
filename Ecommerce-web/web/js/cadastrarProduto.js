/* global http */

var URL = "http://localhost:8080/Ecommerce-web/cadastrar-produto";
var http = new XMLHttpRequest();

function reqHttpGet(url) {
    http.open('GET', url);
    http.send();

    JSON.parse(http.responseText);
}

function reqHttpPost(data) {
    http.open('POST', URL, false);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(data));
    return console.log(http.responseText);
}

document.querySelector('#enviar').addEventListener('click', cadastrarProduto);

function cadastrarProduto() {
    var produto = {};
    var cCategoria = {};
    var cMarca = {};

    cCategoria.cCategoria = document.querySelector('#categoria').value;

    cMarca.cMarca = document.querySelector('#marca').value;

    produto.nome = document.querySelector('#nome').value;
    produto.descricao = document.querySelector('#descricao').value;
    produto.foto = document.querySelector('#foto').value;
    produto.qtde = document.querySelector('#qtde').value;
    produto.preco = document.querySelector('#preco').value;
    produto.promocao = document.querySelector('#promocao').value;
    produto.cCategoria = cCategoria;
    produto.cMarca = cMarca;

    var http = new XMLHttpRequest();
    http.onreadystatechange = function ()
    {
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

    reqHttpPost(produto);
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById("result").innerHTML = "" + obj.message;
}