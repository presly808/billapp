package ua.artcode.billapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.artcode.billapp.exception.AppException;
import ua.artcode.billapp.model.Bill;
import ua.artcode.billapp.model.BillStatus;
import ua.artcode.billapp.model.User;
import ua.artcode.billapp.repository.BillRepository;
import ua.artcode.billapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by serhii on 03.12.17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final BillRepository billRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }


    @Override
    public List<Bill> getOpened(User user) throws AppException {
        LOG.info("user with id " + user.getId() + " is getting his opened bills");
        return billRepository.findByCustomerAndBillStatus(user, BillStatus.OPENED);
    }
}
