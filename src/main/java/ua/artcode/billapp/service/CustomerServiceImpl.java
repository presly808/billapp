package ua.artcode.billapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Serhii Kolomiiets on 09.12.2017.
 * TODO: add paging!
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final BillRepository billRepository;



    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BillRepository billRepository) {
        this.customerRepository = customerRepository;
        this.billRepository = billRepository;
    }

    @Override
    public List<Customer> getCustomerByName(String name) throws AppException {
        LOG.info(String.format("Looking for customer with name \"%s\"...", name));
        return customerRepository.getCustomerByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() throws AppException {
        LOG.info("Retrieving customers list...");
        List customers = (List) customerRepository.findAll();
        return customers;
    }

    @Override
    public List<Bill> getOpenedBills(Customer customer) throws AppException {
        LOG.info(String.format("Customer (ID: %s) is getting his opened bills...", customer.getId()));
        return billRepository.findByCustomerAndBillStatus(customer, BillStatus.OPENED);
    }
}
