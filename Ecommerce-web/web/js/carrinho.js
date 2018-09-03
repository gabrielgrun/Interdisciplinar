var URL = "http://localhost:8080/Ecommerce-web/realizar-compra";
document.querySelector('botao').addEventListener('click', realizarCompra);

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById("result").innerHTML = "" + obj.message;
}

function reqHttpPost() {
    var http = new XMLHttpRequest();
    http.open('POST', URL, true);
    http.setRequestHeader("Content-type", "application/json");
    http.send(produtos);

    http.onreadystatechange = function ()
    {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            parseJson(this.responseText);
        }
    };
}

function realizarCompra(e) {
    var produtos = sessionStorage.getItem('produto');
    var pedido = {};
    var cliente = {};
    var pedidoItem = {};

//    cliente.endereco = ;
//    cliente.cpf = ;
//    cliente.cep = ;
//    cliente.bairro = ;
//    cliente.numero = ;
//    cliente.complemento = ;
//    cliente.uf = ;
//    cliente.cidade = ;

    pedidoItem.qtde = produtos.qtde;
    pedidoItem.valor = produtos.preco;
    pedidoItem.cProduto = produtos.codigo;
    pedidoItem.cpedido = pedido;

    pedido.valor = produtos.preco;
    pedido.data = new Date();
    pedido.cliente = cliente;

    reqHttpPost();
    
}
