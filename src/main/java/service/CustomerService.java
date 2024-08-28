package service;

import domain.actor.Customer;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;
import utils.ExceptionUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    ///CRUD Operations
    public Customer create(){
        Customer customer;

        customer = new Customer();

        return customer;
    }

    public Customer findById(Long customerId){
        if(customerId==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Customer.class));
        }

        Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()){
            throw new NotFoundException(Customer.class);
        }

        return optionalCustomer.get();
    }

    public List<Customer> findAll(){
        List<Customer> customerList;

        customerList = this.customerRepository.findAll();

        return  customerList;
    }

    public void save(Customer customer){
        if(customer==null)
            throw new NullPointerException(ExceptionUtils.getNullMessage(Customer.class));

        this.customerRepository.save(customer);
    }

    public void delete(Customer customer){
        if(customer==null){
            throw new NullPointerException(ExceptionUtils.getNullMessage(Customer.class));
        }
        if(customer.getId()==null)
            throw new NotFoundException(Customer.class);

        this.customerRepository.delete(customer);
    }
}
