package br.com.astar.setupbox.rest;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RESTClient {


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
		Client client = Client.create();

		WebResource resource = client.resource(uri);

		if (token != null) 
			resource.header("Authorization", token);

		switch(method) {
		case GET:
			return resource.get(ClientResponse.class);
		case POST:
			return resource.type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, new Gson().toJson(entity));
		case PUT: 
			return resource.put(ClientResponse.class);
		case DELETE:
			return resource.delete(ClientResponse.class);
		default: 
			return null;
		}
	}

}
