package com.dairy.order.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("AUTHAPP")
public interface AuthClient {

	@GetMapping("/authapp/validate")
	public AuthResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);

}
