package ua.artcode.billapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.service.CompanyService;
import ua.artcode.billapp.dto.ResponseMessage;
import ua.artcode.billapp.model.Company;

import java.util.List;

@RestController
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

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
            LOGGER.info("Start search closed bills");
            closeBills = companyService.getClosedBills(company);
        } catch (AppException e) {
            return new ResponseEntity<>(new ResponseMessage(e.toString(), ""), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(closeBills, HttpStatus.OK);

    }

    @RequestMapping(path = "/get-opened-bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getOpenedBills(@RequestParam(name = "id") Long id) {
        Company company = companyService.getCompanyById(id);
        List<Bill> closeBills = null;
        try {
            LOGGER.info("Start search closed bills");
            closeBills = companyService.getOpenedBills(company);
        } catch (AppException e) {
            return new ResponseEntity<>(new ResponseMessage(e.toString(), ""), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(closeBills, HttpStatus.OK);
    }

    //create-bill?bill={jsonBody}
    @RequestMapping(path = "/create-bill", method = RequestMethod.POST)
    public ResponseEntity createBill(@RequestBody Bill bill) {
        Bill newBill = null;
        try {
            LOGGER.info("Start creating bill");
            newBill = companyService.createBill(bill);
        } catch (AppException e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newBill, HttpStatus.OK);

    }
}
