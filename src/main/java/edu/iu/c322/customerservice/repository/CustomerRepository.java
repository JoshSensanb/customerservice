package edu.iu.c322.customerservice.repository;

import edu.iu.c322.customerservice.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> findAll(){
        return customers;
    }
    @PostMapping
    public int create(Customer customer){
        int id = customers.size()+1;
        customer.setId(id);
        customers.add(customer);
        return id;

    }
    public void update(Customer customer, int id){
        Customer x = getCustomerbyID(id);
        if(x!=null){
            x.setName(customer.getName());
            x.setEmail(customer.getEmail());
        }
        else{
            throw new IllegalStateException("customer id is not valid.");
        }

    }

    private Customer getCustomerbyID(int id) {
        return customers.stream().filter(x -> x.getId() == id).findAny().orElse(null);

    }
    public void delete(int id){
        Customer x = getCustomerbyID(id);
        if(x!=null){
            customers.remove(x);
        }
        else{
            throw new IllegalStateException("not a valid customer id");
        }
    }
}
