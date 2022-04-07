package br.luflix.streaming2.util;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
// classe para acessar o firebase ///
/////////////////////////////////////

public class FireBaseUtil {
	// variavel para guardar as credencias da firebase
	private Credentials credenciais;
	// vat=rialve para acessecar o storage
	private Storage storage;
	// constante para o nome bucket
	private final String BUCKET_NAME = "gs://luflix-web.appspot.com";

	private final String PREFIX = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/";

	private final String SUFFIX = "?alt+media";
	
	private final String DOWNLOAD_URL = PREFIX + "%s" + SUFFIX;
	
	
	public FireBaseUtil() {
		// buscar as creedenciais (arquivo JSON)
		Resource resourse = new ClassPathResource("chave_firebase.json");
		//acessa o serviço storage
		storage = StorageOptions.newBuilder().setCredentials(credenciais).build().getService();
		try {
			// ler o arquivo para obter as credenciais
			credenciais = GoogleCredentials.fromStream(resourse.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
