package ua.artcode.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.artcode.billapp.dto.ResponseMessage;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.service.CompanyService;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class CompanyController {


    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    //localhost:8080/get-closed-bills?id=1
    @RequestMapping(path = "/get-closed-bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getClosedBills(@RequestParam(name = "id") Long id) {
        Company company = companyService.getCompanyById(id);
        List<Bill> closeBills = null;
        try {
            closeBills = companyService.getClosedBills(company);
        } catch (AppException e) {
            return new ResponseEntity<>(new ResponseMessage("AppException", ""), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(closeBills, HttpStatus.OK);

    }

    @RequestMapping(path = "/get-opened-bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getOpenedBills(@RequestParam(name = "id") Long id) {
        Company company = companyService.getCompanyById(id);
        List<Bill> closeBills = null;
        try {
            closeBills = companyService.getOpenedBills(company);
        } catch (AppException e) {
            return new ResponseEntity<>(new ResponseMessage("AppException", ""), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(closeBills, HttpStatus.OK);

    }
}
