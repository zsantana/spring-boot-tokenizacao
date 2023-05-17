package br.com.xbinario.tokenizacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API de integração Rest-Client"))
public class TokenizacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenizacaoApplication.class, args);
	}

}
