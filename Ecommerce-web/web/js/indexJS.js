/* global http, formData */

var URL = "http://localhost:8080/Ecommerce-web/pesquisar-produtos";
var jasao;
init();
function init() {
    document.querySelector("#cadastroEmail").addEventListener("click", alertaCadastro);
}
function alertaCadastro(e) {
    alert("Em breve habilitaremos essa opção!");
}


function reqHttpGet() {
    var http = new XMLHttpRequest();
    http.open('GET', URL, true);
    http.setRequestHeader("Content-type", "application/json");
    http.send(null);

    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            jasao = JSON.parse(this.responseText);
            //constroiProd(jasao);
        }
    };
}

window.onload = listarProdutos;

function listarProdutos() {
    reqHttpGet();
}

function constroiProd(jasao) {
    for (var i = 0; i < jasao.nome.length; i++) {
        var div = document.createElement('div');
        var divPrincipal = document.createElement('div');
        divPrincipal.setAttribute('class', 'tamanhoItemBox');
        div.setAttribute('class', 'itemBox');
        var div2 = document.createElement('div');
        div2.setAttribute('class', 'imagemBox');
        var p = document.createElement('p');
        var p2 = document.createElement('p');
        p2.innerHTML = 'Dar uma olhada!';
        p.setAttribute('class', 'Pitem');
        p.innerHTML = jasao.nome[i];
        var span, span2, span3, span4;
        var i = document.createElement('i');
        i.setAttribute('class', 'fas fa-cart-plus');
        var div3 = document.createElement('div');
        var div4 = document.createElement('div');
        span, span2, span3, span4 = document.createElement('span');
        span.setAttribute('class', 'pagamentoBox');
        span.innerHTML = 'A vista:';
        span2.setAttribute('class', 'sifraoBox');
        span2.innerHTML = 'R$';
        span3.setAttribute('class', 'precoItemBox');
        span3.innerHTML = jasao.preco[i];
        span4.setAttribute('class', 'addCarrinhoItemBox');
        span4.appendChild(i);
        div3.setAttribute('class', 'space');
        div4.setAttribute('class', 'visitar');
        div4.appendChild(p2);

        div.appendChild(div2);
        div.appendChild(p);
        div.appendChild(span4);
        div.appendChild(span);
        div.appendChild(span2);
        div.appendChild(span3);
        div.appendChild(div3);
        div.appendChild(div4);

        document.querySelector('.listaItens').appendChild(div);
        
        
    }
}


