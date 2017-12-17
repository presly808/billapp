package ua.artcode.billapp.service;

import ua.artcode.billapp.exception.BillApplicationException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;

import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */
public interface CompanyService {

    List<Bill> getOpenedBills(Company company) throws BillApplicationException;

    Bill createBill(Bill bill) throws BillApplicationException;

    List<Bill> getClosedBills(Company company) throws BillApplicationException;

    Company getCompanyById(Long id);
}
