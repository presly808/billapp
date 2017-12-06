package ua.artcode.billapp.service;

import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;

import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */
public interface CompanyService {

    List<Bill> getOpenedBills(Company company) throws AppException;

    List<Bill> getClosedBills(Company company) throws AppException;
}
