package com.vasanth.store.customer;

import ch.qos.logback.core.util.StringUtil;
import com.vasanth.store.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest request) {
        Customer customer = this.repo.save(this.mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = this.repo.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %s",request.id())
                ));

        mergeCustomer(customer,request);
        this.repo.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }

        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }

        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }
}
