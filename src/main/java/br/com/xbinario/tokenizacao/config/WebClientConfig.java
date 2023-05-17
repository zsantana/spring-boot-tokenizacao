package br.com.xbinario.tokenizacao.config;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;


@Configuration
public class WebClientConfig {
		
	@Bean
	public WebClient createWebClient() throws SSLException {

		/*
		// Carrega o certificado do servidor em um objeto KeyStore
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		try (InputStream in = new FileInputStream("caminho/para/o/certificado-do-servidor")) {
			keyStore.load(in, "senha-do-keystore".toCharArray());
		}

		// Configura o contexto SSL para usar o certificado do servidor
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);
		 */

		SslContext sslContext = SslContextBuilder
											.forClient()
											//.trustManager(trustManagerFactory)
											.trustManager(InsecureTrustManagerFactory.INSTANCE)
											.sslProvider(SslProvider.JDK)
											.build();
		
		ClientHttpConnector httpConnector = new ReactorClientHttpConnector(HttpClient.create().secure(t -> t.sslContext(sslContext)));
		return WebClient.builder().clientConnector(httpConnector).build();

	}
	
}
