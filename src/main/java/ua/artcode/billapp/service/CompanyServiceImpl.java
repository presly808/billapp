package ua.artcode.billapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.Customer;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private  final BillRepository billRepository;

    private final UserRepository userRepository;


    @Autowired
    public CompanyServiceImpl(BillRepository billRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bill createBill(Bill bill) throws AppException {
        LOG.info("Bill " + bill.getTitle() +  " start created");
        bill.setStart(LocalDateTime.now());
        bill.setBillId(UUID.randomUUID().toString());
        billRepository.save(bill);
        return bill;
    }
}
