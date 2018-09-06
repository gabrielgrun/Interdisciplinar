/* global http, formData */

var URL = "http://localhost:8080/Ecommerce-web/pesquisar-produtos";
var json;

function reqHttpGet() {
    var http = new XMLHttpRequest();
    http.open('GET', URL, true);
    http.setRequestHeader("Content-type", "application/json");
    http.send(null);

    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            json = http.responseText.split('#G#');
            var categ = {};
            var jasao = {};
            jasao = json[0];
            categ = json[1];
            jasao = JSON.parse(json[0]);
            categ = JSON.parse(json[1]);
            constroiProd(jasao);
            constroiCat(categ);
        }
    };
}

window.onload = listarProdutos;

function listarProdutos() {
    reqHttpGet();
}

function constroiCat(jasao) {
    var categorias = jasao;
    var menu = document.querySelector('.nav');
    for (var i = 0; i < categorias.length; i++) {
        var a = document.createElement('a');
        a.innerHTML = jasao[i].nome;
        menu.appendChild(a);
    }
}

function constroiProd(jasao) {
    for (var i = 0; i < jasao.length; i++) {
        var img = document.createElement('img');
        img.setAttribute('src', 'C:\java_workspace\Interdisciplinar\Ecommerce-web\web\img\prod' + jasao[i].foto);
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
        p.innerHTML = jasao[i].nome;
        var span, span2, span3, span4, spanCod;
        var fa = document.createElement('i');
        fa.setAttribute('class', 'fas fa-cart-plus');
        var div3 = document.createElement('div');
        var div4 = document.createElement('div');
        span = document.createElement('span');
        span2 = document.createElement('span');
        span3 = document.createElement('span');
        span4 = document.createElement('span');
        span.setAttribute('class', 'pagamentoBox');
        span.innerHTML = 'A vista:';
        span2.setAttribute('class', 'sifraoBox');
        span2.innerHTML = 'R$';
        span3.setAttribute('class', 'precoItemBox');
        span3.innerHTML = jasao[i].preco;
        span4.setAttribute('class', 'addCarrinhoItemBox');
        span4.appendChild(fa);
        div3.setAttribute('class', 'space');
        div4.setAttribute('class', 'visitar');
        div4.appendChild(p2);

        spanCod = document.createElement('span');
        spanCod.style.display = 'none';
        spanCod.id = 'codigo';
        spanCod.innerText = jasao[i].cProduto;
        div.appendChild(spanCod);

        div2.appendChild(img);
        div.appendChild(div2);
        div.appendChild(p);
        div.appendChild(span4);
        div.appendChild(span);
        div.appendChild(span2);
        div.appendChild(span3);
        div.appendChild(div3);
        div.appendChild(div4);
        divPrincipal.appendChild(div);

        document.querySelector('.listaItens').appendChild(divPrincipal);

        chamaCarrinho();
    }
}

function chamaCarrinho() {
        var carrinho = document.querySelectorAll('.addCarrinhoItemBox');

        for (var i = 0; i < carrinho.length; i++) {
            carrinho[i].addEventListener('click', adicionaCarrinho);
        }
    }
function adicionaCarrinho(e) {
    var prod = e.target.parentNode.parentNode;
    var carrinho;
    var qtde = prompt('Insira a quantidade:', 1);
    var preco = prod.querySelector('.precoItemBox').innerText;
    var codigo = prod.querySelector('#codigo').innerText;

    if (sessionStorage.carrinho) {
        carrinho = JSON.parse(sessionStorage.carrinho);

//            if (e.target.style.color === 'red') {
//                var codProdutoRemover = codigo;
//                var prodRemovido = carrinho.produtos.find(function (obj, idx, arr){
//                if (obj.codigo === codProdutoRemover) {
//                    arr.splice(idx, 1);
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//            }


        sessionStorage.setItem('carrinho', JSON.stringify(carrinho));
    } else {
        carrinho = {'produtos': []};
    }

    carrinho.produtos.push({'codigo': codigo, 'qtde': qtde, 'preco': preco});

    window.sessionStorage.setItem('carrinho', JSON.stringify(carrinho));

    //e.target.style.color = 'red';
    var a = window.sessionStorage.getItem('carrinho');
    console.log(a);

}

pesquisaNome();

function pesquisaNome() {
    document.querySelector('#lupaHeader').addEventListener('click', reqHttpPost);
}

function reqHttpPost() {
    var http = new XMLHttpRequest();
    http.open('POST', URL, false);
    http.send(document.querySelector('.caixaPesquisaHeader').value);
    var jasao = {};
    jasao = http.responseText;
    jasao = JSON.parse(http.responseText);
    filtrarProd(jasao);
}

function filtrarProd(jasao) {
    document.querySelector('.listaItens').innerHTML = "";
    for (var i = 0; i < jasao.length; i++) {
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
        p.innerHTML = jasao[i].nome;
        var span, span2, span3, span4, spanCod;
        var fa = document.createElement('i');
        fa.setAttribute('class', 'fas fa-cart-plus');
        var div3 = document.createElement('div');
        var div4 = document.createElement('div');
        span = document.createElement('span');
        span2 = document.createElement('span');
        span3 = document.createElement('span');
        span4 = document.createElement('span');
        span.setAttribute('class', 'pagamentoBox');
        span.innerHTML = 'A vista:';
        span2.setAttribute('class', 'sifraoBox');
        span2.innerHTML = 'R$';
        span3.setAttribute('class', 'precoItemBox');
        span3.innerHTML = jasao[i].preco;
        span4.setAttribute('class', 'addCarrinhoItemBox');
        span4.appendChild(fa);
        div3.setAttribute('class', 'space');
        div4.setAttribute('class', 'visitar');
        div4.appendChild(p2);

        spanCod = document.createElement('span');
        spanCod.style.display = 'none';
        spanCod.id = 'codigo';
        spanCod.innerText = jasao[i].cProduto;
        div.appendChild(spanCod);

        div.appendChild(div2);
        div.appendChild(p);
        div.appendChild(span4);
        div.appendChild(span);
        div.appendChild(span2);
        div.appendChild(span3);
        div.appendChild(div3);
        div.appendChild(div4);
        divPrincipal.appendChild(div);

        document.querySelector('.listaItens').appendChild(divPrincipal);

        chamaCarrinho();
    }
}

