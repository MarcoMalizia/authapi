package com.marco.authenticator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class AuthController {
	
	
	@PostMapping(value="/auth")
	public String getAuth(@RequestBody String data) throws UnsupportedEncodingException {
		
		//URLDecoder.decode(data, "UTF-8");
		String[] dataArr = data.split("&");
		String[] emailArr = dataArr[0].split("=");
		String email = emailArr[1].replace("%40", "@");
		String[] passArr = dataArr[1].split("=");
		String pass = passArr[1];
		System.out.println("Email: "+email);
		System.out.println("Password: "+pass);
		final String apiUrl = "https://userapi.happyforest-825d7b85.northeurope.azurecontainerapps.io/api/users/"+email;
		RestTemplate restTemplate = new RestTemplate();
		String results = restTemplate.getForObject(apiUrl, String.class);
		System.out.println(results);
		if(results!= null && results.contains(pass)) {
			System.out.println("Chiamata API effettuata con successo, utente autorizzato");
			return "Authorized";
		}else {
			System.out.println("Chiamata API effettuata con successo, utente non autorizzato");
			return "Unauthorized";
		}    
	
	 }	

}
