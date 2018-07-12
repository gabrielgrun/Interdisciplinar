/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.json.Json;

/**
 *
 * @author Gabriel
 */
@Stateless
public class PesquisarProdutosBean implements PesquisarProdutosBeanRemote, PesquisarProdutosBeanLocal {

    public Json pesquisar(String jsonJs) throws AppException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        String condicoes = "";

        try {
            map = mapper.readValue(jsonJs, new TypeReference<Map<String, String>>() {
            });

            for (String key : map.keySet()) {
                condicoes += "AND " + key + "=" + map.get(key);
            }
            //if(){}
            for (Object value : map.values()) {
                // valores
            }
        } catch (IOException ex) {
            throw new AppException("Erro no Json", ex);
        }

        String SQL = "SELECT *"
                + "  FROM PRODUTO "
                + "  WHERE PRODUTO.CPRODUTO > -1"
                + condicoes;
//                + "  AND PRODUTO.CMARCA ="
//                + "  AND PRODUTO.CCATEGORIA ="
//                + "  AND PRODUTO.PRECO" + fs + sd;

        return null;
    }
}
