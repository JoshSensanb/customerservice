package edu.iu.c322.customerservice.controller;

import edu.iu.c322.customerservice.repository.CustomerRepository;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import edu.iu.c322.customerservice.model.Customer;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private WebClient repository;

    public CustomerController(WebClient.Builder webClientBuilder) {

        repository = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    // Get https:localhost:8080/customers


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid @RequestBody Customer customer){

        Customer addedCustomer =  repository.get().uri("/create/{orderId}", customer)
                .retrieve()
                .bodyToMono(Customer.class).block();

        return addedCustomer.getId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){

        Customer addedCustomer =  repository.put().uri("/create/{orderId}", id)
                .retrieve()
                .bodyToMono(Customer.class).block();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){

        repository.delete().uri("/customer/delete{id}",id);
    }


}
