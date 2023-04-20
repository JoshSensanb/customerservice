package edu.iu.c322.customerservice.controller;

import edu.iu.c322.customerservice.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import edu.iu.c322.customerservice.model.Customer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    // Get https:localhost:8080/customers


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public int create(@Valid @RequestBody Customer customer){

        Customer newc = customerRepository.save(customer);


    return newc.getId();

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){

        customer.setId(id);
        customerRepository.save(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){

        Customer customer = new Customer();
        customer.setId(id);
        customerRepository.delete(customer);
    }


}
