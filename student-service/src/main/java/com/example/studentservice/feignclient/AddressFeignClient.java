package com.example.studentservice.feignclient;

import com.example.studentservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
  here we are using Eureka server in Feign client and remove this url and pass only service name
 */
@FeignClient(/*url = "${address.service.url}"*/ value = "address-service",
        path = "api/address")

/* If you want to hit your Api using Feign Client through API Gateway use this approach :
     @FeignClient(value = "api-gateway")
 */

public interface AddressFeignClient {

    //@GetMapping("/address-service/api/address/getById/{addressId}")
    @GetMapping("/getById/{addressId}")
    AddressResponse getById(@PathVariable long addressId);


}
