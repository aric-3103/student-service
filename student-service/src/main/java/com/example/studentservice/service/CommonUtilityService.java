package com.example.studentservice.service;

import com.example.studentservice.feignclient.AddressFeignClient;
import com.example.studentservice.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUtilityService {

    Logger logger = LoggerFactory.getLogger(CommonUtilityService.class);

    long count =1;
    @Autowired
    AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId) {

        logger.info("count = " + count);
        count++;
        return addressFeignClient.getById(addressId);
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable th) {
        logger.error("Error : " + th);
        return  new AddressResponse();  // this return a dummy response and Th object is optional
    }
}
