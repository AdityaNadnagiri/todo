package com.rest.react.basic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthentication {

	@GetMapping(path = "/basicauth")
	public BasicAuthenticationBean getBasicAuth() {
		return new BasicAuthenticationBean("You Are Authenticated");
	}
}
