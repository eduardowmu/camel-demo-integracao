package br.edu.vilt.demo.process;

import br.edu.vilt.demo.dto.EngRespDto;
import br.edu.vilt.demo.dto.ResponseDto;
import br.edu.vilt.demo.mapper.TranslateMapper;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnglishResponseProcess implements Processor {
    @Autowired
    private TranslateMapper translateMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        String response = exchange.getIn().getBody(String.class);
        ResponseDto responseDto = new Gson().fromJson(response, ResponseDto.class);
        EngRespDto engRespDto = this.translateMapper.toEnglish(responseDto);
        exchange.getIn().setBody(new Gson().toJson(engRespDto));
    }
}