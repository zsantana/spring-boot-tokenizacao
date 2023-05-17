package br.com.xbinario.tokenizacao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.ssl.SSLContexts;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSslInitializer {

	public SSLContext createSslCustomContext() throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, KeyManagementException {

		ClassLoader classLoader = getClass().getClassLoader();

		if (classLoader != null) {

			log.info("### Carregando certificado");
			InputStream stream = classLoader.getResourceAsStream("clientkeystore.p12");

			try {
			
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				keyStore.load(stream, "*ss$rd".replace("*", "pa").replace("$", "wo").toCharArray());

				return SSLContexts.custom().loadTrustMaterial(keyStore, null).build();
			
			} finally {
				try {
					stream.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
		return null;
	}
}
