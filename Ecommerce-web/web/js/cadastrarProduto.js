var URL = "http://localhost:8080/ecommerce-web/cadastrar-produto";

function reqHttpGet(url){
    http.open('GET', url)
    http.send();

    JSON.parse(http.responseText);
}

function reqHttpPost(url, data){
    http.open('POST', url);
    JSON.stringify(data);
    http.send(data);

    JSON.parse(http.responseText);
}

function cadastrarProduto(){
    var produto = {};
    var categoria = {};
    var marca = {};
    
    categoria.nome = document.querySelector('#categoria').value;

    marca.nome = document.querySelector('#marca').value;

    produto.nome = document.querySelector('#nome').value;
    produto.descricao = document.querySelector('#descricao').value;
    produto.foto = document.querySelector('#foto').value;
    produto.qtde = document.querySelector('#qtde').value;
    produto.preco = document.querySelector('#preco').value;
    produto.promocao = document.querySelector('#promocao').value;
    produto.categoria = categoria;
    produto.marca = marca;

    reqHttpPost(URL, produto);
}