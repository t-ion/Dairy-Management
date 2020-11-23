package com.dairy.order.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.dairy.order.OrderserviceApplication;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	private static final Logger logger = LoggerFactory.getLogger(OrderserviceApplication.class);
    
    @Override
    public Exception decode(String methodKey, Response response) {
 
       
        switch (response.status()){
            case 400:
                     logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404:
            {
                     logger.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Product not found at server"); 
            }
            default:
                return new Exception(response.reason());
        } 
    }
    
}