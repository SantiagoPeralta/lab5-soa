package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {
        // variables para detectar la opcion nueva
        HashMap<String,Object> cabecera = new HashMap<>();
        int posicionMaxTwit = q.indexOf("max:"); // Para obtener la posicion del maximo de twits a mostrar, si esta

        // Comprobamos si hay un numero maximo de twits
        if (posicionMaxTwit != -1 ){
            // Obtenemos el limite de los twits a mostrar,
            int max = Integer.parseInt(q.substring(posicionMaxTwit + 4));
            // Actualiza el valor de q, quitando el maximo de twits a mostrar
            q = q.substring(0, posicionMaxTwit - 1);
            // Ponemos en la cabecera el maximo de twits
            cabecera.put("CamelTwitterCount", max);
        }

        // Ponemos en la cabecera la clave a buscar
        cabecera.put("CamelTwitterKeywords", q);

        return producerTemplate.requestBodyAndHeader("direct:search", "", cabecera);
    }
}