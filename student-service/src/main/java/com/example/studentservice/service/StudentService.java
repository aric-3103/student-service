package com.example.studentservice.service;

import com.example.studentservice.entity.Student;
import com.example.studentservice.feignclient.AddressFeignClient;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.request.CreateStudentRequest;
import com.example.studentservice.response.AddressResponse;
import com.example.studentservice.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;

    @Autowired
    CommonUtilityService commonUtilityService;

    public StudentResponse createStudent(CreateStudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(student.getEmail());

        student.setStudentId(student.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);

        //TODO: WEb client call
        //studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        /* Calling Address service Feign client */
        //studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

        studentResponse.setAddressResponse(commonUtilityService.getAddressById(student.getAddressId()));
        return studentResponse;
    }

    public StudentResponse getById(long studentId) {
        logger.info("Student Service - getById - Inside student : " + studentId);

        Student student = studentRepository.findById(studentId).get();
        StudentResponse studentResponse = new StudentResponse(student);

        //TODO : This below comment line used for WEB Client calling
        //studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        //TODO : This below line used to call FeignClient object.
        //studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

        studentResponse.setAddressResponse(commonUtilityService.getAddressById(student.getAddressId()));
        return studentResponse;
    }

    /*
    Calling Address Service using WebClient
     */
    /*public AddressResponse getAddressById(long addressId) {
        Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
                .retrieve().bodyToMono(AddressResponse.class);
        return addressResponseMono.block();

    }*/

    //In this method we apply the Circuit breaker pattern because inside this method we call the REst Api to Address-service
   /* @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId) {

        return addressFeignClient.getById(addressId);
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable th) {
        return  new AddressResponse();  // this return a dummy response and Th object is optional
    }*/

}
