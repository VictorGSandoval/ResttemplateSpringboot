package com.example.springbootguidetoresttemplate.controller;

import com.example.springbootguidetoresttemplate.dto.LoginDto;
import com.example.springbootguidetoresttemplate.dto.UnicornDto;
import com.example.springbootguidetoresttemplate.util.LoginResponse;
import com.example.springbootguidetoresttemplate.util.UnicornResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class UnicornController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/unicornsByEntity",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnicornResponse> createUnicornByEntity(@RequestBody UnicornDto unicornDto) {
        return restTemplate.postForEntity(
                "https://crudcrud.com/api/02c41e5948154409b38f5d7aeaf23f7a/unicorns",
                unicornDto,
                UnicornResponse.class
        );
    }

//CREATE MY METOD
    @PostMapping(value = "/loginByEntity",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> createLoginByEntity(@RequestHeader("Ocp-Apim-Subscription-Key") String header, @RequestBody LoginDto loginDto) {
        return restTemplate.postForEntity(
                "url",
                loginDto,
                LoginResponse.class
        );
    }

    //second metod yo

    @PostMapping(value = "/loginByObject",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse createLoginByObject(@RequestBody LoginDto loginDto) throws RestClientException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("nameKey", "xxxxx");

        HttpEntity<LoginDto> request = new HttpEntity<>(loginDto, headers);

        return restTemplate.postForObject(
                "url",
                request,
                LoginResponse.class);
    }

    //end

    @PostMapping(value = "/unicornsByObject",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UnicornResponse createUnicornByObject(@RequestBody UnicornDto unicornDto) throws RestClientException, JsonProcessingException {


        return restTemplate.postForObject(
                "https://crudcrud.com/api/02c41e5948154409b38f5d7aeaf23f7a/unicorns",
                unicornDto,
                UnicornResponse.class);
    }

    @GetMapping("/unicornsByEntity/{id}")
    public ResponseEntity<String> getUnicornByIdByEntity(@PathVariable final String id) {
        return restTemplate.getForEntity(
                "https://crudcrud.com/api/02c41e5948154409b38f5d7aeaf23f7a/unicorns/" + id,
                String.class);
    }


    @GetMapping("/unicornsByObject")
    public List<UnicornResponse> getUnicornByObject() {
        List<UnicornResponse> unicornResponseList = new ArrayList<>();
        unicornResponseList = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                "https://crudcrud.com/api/02c41e5948154409b38f5d7aeaf23f7a/unicorns",
                UnicornResponse[].class
        )));

        return unicornResponseList;
    }

    @DeleteMapping("/unicorns/{id}")
    public void deleteUnicornsByIdByEntity(@PathVariable final String id){
        restTemplate.delete("https://crudcrud.com/api/02c41e5948154409b38f5d7aeaf23f7a/unicorns/"+id);
    }


    @PutMapping(value = "/unicorns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUnicorn(@PathVariable final String id, @RequestBody UnicornDto unicornDto){
        restTemplate.exchange(
                "https://crudcrud.com/api/02c41e5948154409b38f5d7aeaf23f7a/unicorns"+id,
                HttpMethod.PUT,
                new HttpEntity<>(unicornDto),
                Void.class
        );
    }
}
