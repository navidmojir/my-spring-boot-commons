package ir.mojir.spring_boot_commons.helpers;

import ir.mojir.spring_boot_commons.interfaces.CurrentUserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * TODO: Access token with client credentials and token exchange flows must be implemented later
 */
public class BaseClient {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseClient.class);

	private CurrentUserProvider currentUserProvider = null;

	public void setCurrentUserProvider(CurrentUserProvider currentUserProvider) {
		this.currentUserProvider = currentUserProvider;
	}
	
	
	public <T> T  get(String url, Class<T> responseType) {
			return get(url, responseType, null);
	}

	public <T> T  get(String url, Class<T> reponseType, MultiValueMap<String, String> headers) {
		logger.info("Calling get on url {}", url);
		MultiValueMap<String, String> finalHeaders = makeAuthorizationHeader();
		if(headers != null)
			finalHeaders.addAll(headers);
		return new RestTemplate().exchange(
				url, 
				HttpMethod.GET, 
				new HttpEntity<Object>(finalHeaders), 
				reponseType).getBody();
	}

	public <RESP, REQ> RESP post(String url, Class<RESP> reponseType, REQ body)
	{
		logger.info("Calling post on url {}", url);
		return new RestTemplate().exchange(
				url,
				HttpMethod.POST,
				new HttpEntity<REQ>(body, makeAuthorizationHeader()),
				reponseType).getBody();
	}

	public void delete(String url) {
		logger.info("Calling delete on url {}", url);
		new RestTemplate().exchange(
				url, 
				HttpMethod.DELETE, 
				new HttpEntity<Object>(makeAuthorizationHeader()),
				Void.class);
}

	private MultiValueMap<String, String> makeAuthorizationHeader() {
		if(currentUserProvider == null || Validations.isBlank(currentUserProvider.getCurrentAccessToken()))
			return null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", "bearer " + currentUserProvider.getCurrentAccessToken());
		return headers;
	}

//	private String getAccessToken() {
//		if(LocalThreadContext.getData() != null &&
//				!Validations.isBlank(LocalThreadContext.getData().getAccessToken())) {
//			logger.trace("Setting access token for user {}", LocalThreadContext.getData().getUsername());
//			return LocalThreadContext.getData().getAccessToken();
//		}
//		return getAccessTokenWithClientCredentials();
//	}

//	private String getAccessTokenWithClientCredentials() {
//		logger.info("Getting access token with client credentials from auth server {} for client id {}",
//				keycloakConfig.getAuthServerUrl(), keycloakConfig.getClientId());
//
//		String token = keycloakClient.getAccessTokenWithClientCredentials(
//				keycloakConfig.getAuthServerUrl(),
//				keycloakConfig.getKcRealm(),
//				keycloakConfig.getClientId(),
//				keycloakConfig.getClientSecret()).getAccess_token();
//		return token;
//	}
	
	public void postMultipart(String url, byte[] fileBytes, String fileName) {
		
		MultiValueMap<String, String> headers = makeAuthorizationHeader();
		headers.add("content-type", "application/form-data");
		
		ByteArrayResource resource = new ByteArrayResource(fileBytes) {
	        @Override
	        public String getFilename() {
	            return fileName; // Filename has to be returned in order to be able to post.
	        }
	    };
		
		MultiValueMap<String, Object> body
		  = new LinkedMultiValueMap<>();
		body.add("file", resource);
		body.add("name", fileName);
		body.add("filename", fileName);

		new RestTemplate().exchange(
				url,
				HttpMethod.POST,
				new HttpEntity<MultiValueMap<String, Object>>(body, headers),
				Void.class);
	}

}
