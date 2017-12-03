package ua.artcode.billapp.service;

import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Customer;

import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */
public interface UserService {

    List<Bill> getOpened(Customer customer) throws AppException;

}
