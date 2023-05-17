package br.com.xbinario.tokenizacao.infra.webclient;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.xbinario.tokenizacao.infra.webclient.exception.WebClientServiceException;

import org.springframework.http.HttpHeaders;


@Service
public class WebClientService {

    private final WebClient webClient;
    
    public WebClientService(WebClient builder) {
        this.webClient = builder;
    }  
    
    public <T, R> R executePost(final T requestObject, final Class<R> responseType, final String uri)  {
        
        try{
        
            return webClient.post()
                        .uri(uri)
                        .bodyValue(requestObject)
                        .retrieve()
                        .bodyToMono(responseType)
                        .block();

        }catch (Exception ex) {
                throw new WebClientServiceException(ex);
        }            
    }


    public <T, R> R executePostWithHeaders(final T requestObject, final Class<R> responseType, final String uri, final HttpHeaders headers)  {

        try{
        
            return webClient.post()
                        .uri(uri)
                        .headers(h -> h.addAll(headers))   
                        .bodyValue(requestObject)
                        .retrieve()
                        .bodyToMono(responseType)
                        .block();

        }catch (Exception ex) {
            throw new WebClientServiceException(ex);
        }              
    }
    


    public <T, R> R executeGet(final T requestObject, final Class<R> responseType, final String uri, final List<String> params)  {
        
       try{

            var builder = UriComponentsBuilder.fromUriString(uri);

            for (String param : params) {
                var keyValue = param.split("=");
                builder.queryParam(keyValue[0], keyValue[1]);
            }

            var uriWithParams = builder.build().toUri();

            return webClient.get()
                    .uri(uriWithParams)
                    .retrieve()
                    .bodyToMono(responseType)
                    .block();
                    
        }catch (Exception ex) {
            throw new WebClientServiceException(ex);
        }      
    }

}
