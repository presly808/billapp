package ua.artcode.billapp.service;

import ua.artcode.billapp.exception.BillApplicationException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Customer;

import java.util.List;

/**
 * Created by Serhii Kolomiiets on 09.12.2017.
 */

public interface CustomerService {
    List<Customer> getCustomerByName(String name) throws BillApplicationException;
    List<Customer> getAllCustomers() throws BillApplicationException;
    List<Bill> getOpenedBills(Customer customer) throws BillApplicationException;
}
