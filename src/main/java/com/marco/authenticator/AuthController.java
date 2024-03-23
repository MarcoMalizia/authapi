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
		System.out.println("MARCO MARCO MARCO TEST TEST TEST");
		System.out.println(data);
		URLDecoder.decode(data, "UTF-8");
		System.out.println(data);
		/*String[] dataArr = data.split("&");
		String[] emailArr = dataArr[0].split("=");
		String email = emailArr[1].replace("%40", "@");
		String[] passArr = dataArr[1].split("=");
		String pass = passArr[1];
		*/
		String[] dataArr = data.split("-----");
		String email = dataArr[0];
		String pass = dataArr[1];
		final String apiUrl = "https://authenticator.happyforest-825d7b85.northeurope.azurecontainerapps.io:8080/api/users/"+email;
		RestTemplate restTemplate = new RestTemplate();
		String results = restTemplate.getForObject(apiUrl, String.class);
		if(results!= null && results.contains(pass)) {
			return "Authorized";
		}else {
			return "Unauthorized";
		}    
	
	 }	

}
