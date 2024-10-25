package br.edu.vilt.demo.route;

import br.edu.vilt.demo.process.EnglishResponseProcess;
import br.edu.vilt.demo.process.GetCepProcess;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdressRoute extends RouteBuilder {
    @Autowired
    private GetCepProcess process;
    @Autowired
    private EnglishResponseProcess responseProcess;

    @Override
    public void configure() throws Exception {
        rest("/app/v1")
                .produces("application/json")
                .get("/{cep}")
                .description("Integração com API publica viaCEP")
                .to("direct:my-camel-app");

        from("direct:my-camel-app")
                .log("Rest API calling")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .process(this.process)
                //.setBody(header("cep"))
                .toD("https://viacep.com.br/ws/${body}/json"
                        .concat("?q=ssl&bridgeEndpoint=true")
                )
                .process(this.responseProcess)
                ;
    }
}
