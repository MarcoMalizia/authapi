package com.marco.authenticator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class AuthController {
	
	
	@PostMapping(value="/auth")
	public String getAuth(@RequestBody String data) {
		String[] arrSplit = data.split("-----");
		String email = arrSplit[0];
		String pass = arrSplit[1];
		final String apiUrl = "http://localhost:8080/api/users/"+email;
		RestTemplate restTemplate = new RestTemplate();
		String results = restTemplate.getForObject(apiUrl, String.class);
		if(results!= null && results.contains(pass)) {
			return "Authorized";
		}else {
			return "Unauthorized";
		}    
	
	 }	

}
