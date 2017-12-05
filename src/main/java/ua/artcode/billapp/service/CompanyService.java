package ua.artcode.billapp.service;

import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.model.Customer;

/**
 * Created by serhii on 03.12.17.
 */
public interface CompanyService {

    Bill createBill(Bill bill) throws AppException;
}
