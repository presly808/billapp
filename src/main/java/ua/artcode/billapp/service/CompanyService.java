package ua.artcode.billapp.service;

import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;

import java.util.List;

import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.model.Customer;

/**
 * Created by serhii on 03.12.17.
 */
public interface CompanyService {

    List<Bill> getOpenedBills(Company company) throws AppException;

    Bill createBill(Bill bill) throws AppException;
}
