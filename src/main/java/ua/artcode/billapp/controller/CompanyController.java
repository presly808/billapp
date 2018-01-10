package ua.artcode.billapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.artcode.billapp.exception.BillApplicationException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.Company;
import ua.artcode.billapp.service.CompanyService;

@RestController
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(path = "/get-closed-bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getClosedBills(@RequestParam(name = "id") Long id) throws BillApplicationException {
        Company company = companyService.getCompanyById(id);
        LOGGER.info("Start search closed bills");
        return new ResponseEntity<>(companyService.getClosedBills(company), HttpStatus.OK);

    }

    @RequestMapping(path = "/get-opened-bills", method = RequestMethod.GET)
    public ResponseEntity<Object> getOpenedBills(@RequestParam(name = "id") Long id) throws BillApplicationException {
        Company company = companyService.getCompanyById(id);
        LOGGER.info("Start search closed bills");
        return new ResponseEntity<>(companyService.getOpenedBills(company), HttpStatus.OK);
    }

    //create-bill?bill={jsonBody}
    @RequestMapping(path = "/create-bill", method = RequestMethod.POST)
    public ResponseEntity createBill(@RequestBody Bill bill) throws BillApplicationException {
        LOGGER.info("Start creating bill");
        return new ResponseEntity<>(companyService.createBill(bill), HttpStatus.OK);
    }
}
