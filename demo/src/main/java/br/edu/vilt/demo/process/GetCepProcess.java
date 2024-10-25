package br.edu.vilt.demo.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class GetCepProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String cep = exchange.getIn().getHeader("cep", String.class);
        exchange.getIn().setBody(cep);
    }
}