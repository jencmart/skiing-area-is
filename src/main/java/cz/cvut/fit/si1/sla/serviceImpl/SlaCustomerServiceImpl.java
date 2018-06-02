package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.dao.SlaCustomerDao;
import cz.cvut.fit.si1.sla.dao.SlaUserDao;
import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.dto.CustomerOrderDto;
import cz.cvut.fit.si1.sla.service.SlaCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaCustomerServiceImpl implements SlaCustomerService {

    @Autowired
    SlaCustomerDao customerDao;

    @Autowired
    SlaUserDao userDao;

    @Override
    @Autowired
    public List<SlaCustomer> getAllList() {
        return customerDao.getAllCustomers();
    }


    public void edit(SlaCustomer customer, CustomerOrderDto customerOrderDto) {
        customer.setPhone(customerOrderDto.getPhone());
        customer.setEmail(customerOrderDto.getEmail());
        customer.setName(customerOrderDto.getName());
        customer.setSurname(customerOrderDto.getSurname());
    }

    @Override
    public SlaCustomer registerNewCustomer(CustomerOrderDto customerOrderDto) {
        SlaCustomer customer = new SlaCustomer();
        edit(customer, customerOrderDto);

        customerDao.create(customer);
        return customer;
    }

    @Override
    public SlaCustomer updateCustomerUser(CustomerOrderDto customerOrderDto) {
        SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SlaCustomer customer = user.getCustomer();
        edit(customer, customerOrderDto);
        customerDao.update(customer);
        return customer;
    }

    @Override
    public SlaCustomer registerNewCustomerToUser(CustomerOrderDto customerOrderDto, SlaUser user) {
        SlaCustomer customer = registerNewCustomer(customerOrderDto);
        user.setCustomer(customer);
        userDao.update(user);
        return customer;
    }

    @Override
    public SlaCustomer findById(int id) {
        return customerDao.getOneCustomer(((Integer) id).longValue());
    }

    @Override
    public List<SlaCustomer> findCustomer(SlaCustomer customer) {
        return customerDao.find(customer);
    }
}
