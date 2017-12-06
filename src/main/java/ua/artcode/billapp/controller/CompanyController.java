package ua.artcode.billapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.service.CompanyService;


@RestController
public class CompanyController {



    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //create-bill?bill={jsonBody}
    @RequestMapping(path = "/create-bill", method = RequestMethod.POST)
    public ResponseEntity<Object> createBill(@RequestBody Bill bill) {
        Bill newBill = null;
        try {
            newBill = companyService.createBill(bill);
        } catch (AppException e) {
            return new ResponseEntity<>("AppException", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newBill, HttpStatus.OK);




    }
}
