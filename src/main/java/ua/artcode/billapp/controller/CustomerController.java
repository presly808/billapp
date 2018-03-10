package ua.artcode.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.artcode.billapp.exception.BillApplicationException;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.service.CustomerService;

/**
 * Created by Serhii Kolomiiets on 09.12.2017.
 */
@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = "/rest/customer", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerByName(@RequestParam(name = "name") String name) throws BillApplicationException {
        return new ResponseEntity<>(customerService.getCustomerByName(name), HttpStatus.OK);
    }

    @RequestMapping(path = "/rest/customers", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomers() throws BillApplicationException {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/rest/customer/bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getOpenedBills(@RequestBody Customer customer) throws BillApplicationException {
        return new ResponseEntity<>(customerService.getOpenedBills(customer), HttpStatus.OK);
    }
}
