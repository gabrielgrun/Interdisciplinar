//init();

function init(e) {
//    document.querySelector('#foto').innerHTML = "caminho da foto depois tu altera o JS";
    document.querySelector('#nomeProd').innerHTML = "Nome do produto";
    document.querySelector('.cod').innerHTML = "(Cód. 050899)";
    document.querySelector('.preco').innerHTML = "8.000,00";
    document.querySelector('#detalhesProd').innerHTML = "aqui vai toda aquela caralhada de detalhes que a gente \n\
tem preguiça de digitar, mas seria interessante que fosse alterado os caracteres do banco também para uns 2000, ae fica bonitão\n\
 o negocio.";
}

var cproduto = obterParametro('cproduto');

function obterParametro(nomeParametro) {
    var url = new URL(window.location.href);
    reqHttpGetId(url.searchParams.get(nomeParametro));
}

function reqHttpGetId(id){
    var urlReq = 'http://localhost:8080/Ecommerce-web/pesquisar-prod?cproduto=' + id;
    var http = new XMLHttpRequest();
    http.open('GET', urlReq, false);
    http.setRequestHeader("Content-type", "application/json");
    http.send();
    
    
    var json2 = {};
    json2 = http.responseText;
    json2 = JSON.parse(json2);
    constroiProd2(json2);
}

function constroiProd2(json){
        var div = document.createElement('div');
        div.setAttribute('class', 'img');
        var img = document.createElement('img');
        img.setAttribute('src', '../img/prod/' + json.foto.replace(/^.*\\/, ""));
        div.appendChild(img);
        var h2 = document.createElement('h2');
        h2.innerHTML = json.nome;
        var div2 = document.createElement('div');
        div2.setAttribute('class','div');
        var cifrao = document.createElement('span');
        cifrao.setAttribute('class', 'sifrao');
        var vista = document.createElement('vista');
        vista.setAttribute('class', 'vista');
        vista.innerHTML = 'A vista:';
        cifrao.innerHTML = '$';
        cifrao.appendChild(vista);
        var span3 = document.createElement('span');
        span3.setAttribute('class', 'preco');
        span3.innerHTML = json.preco;
        
        
        div2.appendChild(cifrao);
        div2.appendChild(span3);
        
        var detalhesCompra = document.querySelector('.detalhesCompra');
        detalhesCompra.appendChild(div);
        detalhesCompra.appendChild(h2);
        detalhesCompra.appendChild(div2);
        
        var detalhes = document.querySelector('.detalhes');
        var h1 = document.createElement('h1');
        var spanDet = document.createElement('span');
        var p = document.createElement('p');
        h1.innerHTML = 'Detalhes do Produto';
        spanDet.innerHTML = 'ATENÇÃO: Imagens meramente ilustrativas. Todas as informações divulgadas são de responsabilidade do Fabricante/Fornecedor. Verifique com os fabricantes do produto e de seus componentes eventuais limitações à utilização de todos os recursos e funcionalidades.';
        p.setAttribute('class', 'detalhesProd');
        p.innerHTML = json.descricao;
        detalhes.appendChild(h1);
        detalhes.appendChild(p);
        detalhes.appendChild(spanDet);
    
}


