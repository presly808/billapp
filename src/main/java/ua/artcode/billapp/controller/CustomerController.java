package ua.artcode.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.artcode.billapp.dto.ResponseMessage;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.service.CustomerService;

import java.util.List;

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

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerByName(@RequestParam(name = "name") String name) throws AppException {
        List<Customer> customers = customerService.getCustomerByName(name);
        if (customers == null || customers.isEmpty()) return notFound();
        return new ResponseEntity<>(customers.get(0), HttpStatus.OK);
    }

    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomers() throws AppException {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers == null || customers.isEmpty()) return notFound();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(path = "/customer/bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getOpenedBills(@RequestParam(name = "customer") Customer customer) throws AppException {
        List<Bill> bills = customerService.getOpenedBills(customer);
        if (bills == null || bills.isEmpty()) return notFound();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    private ResponseEntity<Object> notFound() {
        return new ResponseEntity<>(new ResponseMessage("Not found!",""), HttpStatus.NOT_FOUND);
    }
}
