var URL = "http://localhost:8080/Ecommerce-web/realizar-compra";
var codCliente;
var codPedido;
//document.querySelector('botao').addEventListener('click', realizarCompra);

function reqHttpPost(pedido) {
    var http = new XMLHttpRequest();
    http.open('POST', URL, true);
    http.setRequestHeader("Content-type", "application/json");
    http.send(pedido);

    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            parseJson(this.responseText);
        }
    };
}

function reqHttpPostPedidoItem(pedidoItem) {
    var http = new XMLHttpRequest();
    var urlPedidoItem = 'http://localhost:8080/Ecommerce-web/cadastrar-item';
    http.open('POST', urlPedidoItem, true);
    http.setRequestHeader("Content-type", "application/json");
    http.send(pedidoItem);

    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            parseJson(this.responseText);
        }
    };
}

function reqHttpPostCliente(cliente) {
    var http = new XMLHttpRequest();
    var urlCliente = 'http://localhost:8080/Ecommerce-web/cadastrar-cliente';
    http.open('POST', urlCliente, true);
    http.setRequestHeader("Content-type", "application/json");
    http.send(cliente);

    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            parseJson(this.responseText);
        }
    };
}

function reqHttpGetCliente() {
    var http = new XMLHttpRequest();
    var urlCliente = 'http://localhost:8080/Ecommerce-web/cadastrar-cliente';
    http.open('POST', urlCliente, false);
    http.setRequestHeader("Content-type", "application/json");
    http.send(null);

    var json = http.responseText;
    json = JSON.parse(json);
    codCliente = json.cCliente;
}

function reqHttpGetCliente() {
    var http = new XMLHttpRequest();
    var urlPedido = 'http://localhost:8080/Ecommerce-web/realizar-compra';
    http.open('POST', urlPedido, false);
    http.setRequestHeader("Content-type", "application/json");
    http.send(null);

    var json = http.responseText;
    json = JSON.parse(json);
    codPedido = json.cPedido;
}

montaCarrinho();

function montaCarrinho() {
    var json = {};
    json = window.sessionStorage.getItem('carrinho');
    json = JSON.parse(json);
    for (var i = 0; i < json.produtos.length; i++) {
        var div = document.createElement('div');
        div.setAttribute('class', 'produto');
        var div2 = document.createElement('div');
        div2.setAttribute('class', 'fotoProd');
        var p = document.createElement('p');
        p.setAttribute('class', 'desc');
        p.innerHTML = json.produtos[i].nome;
        var p2 = document.createElement('p');
        p2.setAttribute('class', 'aVista');
        p2.innerHTML = 'A vista: ';
        var span = document.createElement('span');
        span.setAttribute('class', 'sifrao');
        span.innerHTML = 'R$';
        var span2 = document.createElement('span');
        span2.setAttribute('class', 'preco');
        span2.innerHTML = json.produtos[i].preco;
        var span3 = document.createElement('span');
        span3.setAttribute('class', 'quantiti');
        span3.innerHTML = 'Quant.';
        var input = document.createElement('input');
        input.setAttribute('type', 'number');
        input.setAttribute('class', 'quant');
        input.setAttribute('readonly', 'readonly');
        input.value = json.produtos[i].qtde;
        var span4 = document.createElement('span');
        span4.setAttribute('class', 'remove');
        var f = document.createElement('i');
        f.setAttribute('class', 'fas fa-times');
        var spanCod = document.createElement('span');
        spanCod.style.display = 'none';
        spanCod.id = 'codigo';
        spanCod.innerText = json.produtos[i].codigo;

        div.appendChild(spanCod);
        div.appendChild(div2);
        div.appendChild(p);
        div.appendChild(p2);
        div.appendChild(span);
        div.appendChild(span2);
        div.appendChild(span3);
        div.appendChild(input);
        div.appendChild(span4);
        span4.appendChild(f);


        document.querySelector('.containerProd').appendChild(div);

        adicionaRemovedor();
    }
}

function adicionaRemovedor() {
    var remover = document.querySelectorAll('.remove');
    for (var i = 0; i < remover.length; i++) {
        remover[i].addEventListener('click', removeCarrinho);
    }

}

function removeCarrinho(e) {
    var carrinho = window.sessionStorage.getItem('carrinho');
    carrinho = JSON.parse(carrinho);
    var prod = e.target.parentNode.parentNode;
    var codigo = prod.querySelector('#codigo').innerHTML;
    for (var i = 0; i < carrinho.produtos.length; i++) {
        if (codigo === carrinho.produtos[i].codigo) {
            var codProdutoRemover = codigo;
            var prodRemovido = carrinho.produtos.find(function (obj, idx, arr) {
                if (obj.codigo === codProdutoRemover) {
                    arr.splice(idx, 1);
                    return true;
                } else {
                    return false;
                }
            });
        }
    }
    window.sessionStorage.setItem('carrinho', JSON.stringify(carrinho));
    console.log(prodRemovido);
    prod.innerHTML = "";
}

function realizarCompra(e) {
    var produtos = sessionStorage.getItem('carrinho');
    produtos = JSON.parse(produtos);
    var pedido = {};
    var cliente = {};
    var pedidoItem = {};
    var dadosCliente = document.querySelector('.dadosCliente form');


    cliente.endereco = dadosCliente.querySelector('.endereco').value;
    cliente.cpf = dadosCliente.querySelector('.cpf').value;
    cliente.cep = dadosCliente.querySelector('.cep').value;
    cliente.bairro = dadosCliente.querySelector('.bairro').value;
    cliente.numero = dadosCliente.querySelector('.numero').value;
    cliente.complemento = dadosCliente.querySelector('.complemento').value;
    cliente.uf = dadosCliente.querySelector('.uf').value;
    cliente.cidade = dadosCliente.querySelector('.cidade').value;
    cliente.nome = dadosCliente.querySelector('.nome').value;

    reqHttpPostCliente(cliente);
    reqHttpGetCliente();

    pedido.valor = produtos.preco;
    pedido.data = new Date();
    pedido.cCliente = codCliente;

    reqHttpPost(pedido);
    reqHttpGetPedido();

    for (var i = 0; i < produtos.produtos[i].length; i++) {
        pedidoItem.qtde = produtos.produtos[i].qtde;
        pedidoItem.valor = produtos.produtos[i].preco;
        pedidoItem.cProduto = produtos.produtos[i].codigo;
        pedidoItem.cPedido = codPedido;

        reqHttpPostPedidoItem(pedidoItem);
    }
}
