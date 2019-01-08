package br.com.astar.setupbox.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.ClientResponse;

public class RESTClient {
	
	private static final Logger logger = LoggerFactory.getLogger(RESTClient.class);


	public enum HTTPMethod { GET, POST, PUT, DELETE; }

	//private String uri;
	private String token;

	public RESTClient(String login, String senha, String uri) {
		this.token = "Basic " + new String();
		//this.uri = uri;
	}
	
	public RESTClient() {
		
	}

	public ClientResponse request(String uri, HTTPMethod method) {
		return request(uri, method, null);
	}

	public com.sun.jersey.api.client.ClientResponse request(String uri, HTTPMethod method, Object entity) {
		logger.info("Enviando para Blockchain.....");
		//TODO - Descomentar quando a API estiver disponivel
		return null;
//		Client client = Client.create();
//
//		WebResource resource = client.resource(uri);
//
//		if (token != null) 
//			resource.header("Authorization", token);
//
//		switch(method) {
//		case GET:
//			return resource.get(ClientResponse.class);
//		case POST:
//			return resource.type(MediaType.APPLICATION_JSON)
//					.post(ClientResponse.class, new Gson().toJson(entity));
//		case PUT: 
//			return resource.put(ClientResponse.class);
//		case DELETE:
//			return resource.delete(ClientResponse.class);
//		default: 
//			return null;
//		}
	}

}
