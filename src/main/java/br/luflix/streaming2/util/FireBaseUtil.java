package br.luflix.streaming2.util;

import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
// classe para acessar o firebase ///
/////////////////////////////////////


@Service
public class FireBaseUtil {
	// variavel para guardar as credencias da firebase
	private Credentials credenciais;
	// vat=rialve para acessecar o storage
	private Storage storage;
	// constante para o nome bucket
	private final String BUCKET_NAME = "luflix-web.appspot.com";
	//constante para o prefixo da url
	private final String PREFIX = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/";

	private final String SUFFIX = "?alt=media";

	private final String DOWNLOAD_URL = PREFIX + "%s" + SUFFIX;

	public FireBaseUtil() {
		// buscar as creedenciais (arquivo JSON)
		Resource resourse = new ClassPathResource("chave_firebase.json");
		// acessa o serviço storage
		
		try {
			// ler o arquivo para obter as credenciais
			credenciais = GoogleCredentials.fromStream(resourse.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		storage = StorageOptions.newBuilder().setCredentials(credenciais).build().getService();
	}

	// metodo para extensoa d arquivo
	private String getExtensao(String nomeArq) {
		// retorna o trecho da String que vai do ultimo ponto ate o fim
		return nomeArq.substring(nomeArq.lastIndexOf('.'));
	}

	public String uploadFile(MultipartFile arq) throws IOException {
		// gera uma String aleatori para o nome do arquivo
		String nomeArquivo = UUID.randomUUID().toString() + getExtensao(arq.getOriginalFilename());

		BlobId blobId = BlobId.of(BUCKET_NAME, nomeArquivo);

		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

		storage.create(blobInfo, arq.getBytes());

		return String.format(DOWNLOAD_URL, nomeArquivo);
	}
	public void deletar(String nomeArquivo) {
		nomeArquivo = nomeArquivo.replace(PREFIX, "").replace(SUFFIX, "");
		Blob blob = storage.get(BlobId.of(BUCKET_NAME, nomeArquivo));
		storage.delete(blob.getBlobId());
	
	}
}
