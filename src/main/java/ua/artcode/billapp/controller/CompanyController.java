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



@RestController
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
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
