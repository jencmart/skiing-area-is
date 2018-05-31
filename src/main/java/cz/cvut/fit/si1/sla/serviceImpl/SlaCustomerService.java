package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.dao.SlaCustomerDao;
import cz.cvut.fit.si1.sla.dao.SlaUserDao;
import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.dto.CustomerOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaCustomerService {

    @Autowired
    SlaCustomerDao customerDao;

    @Autowired
    SlaUserDao userDao;

    @Autowired
    public List<SlaCustomer> getAllList() {
        return customerDao.getAllCustomers();
    }


    private void edit(SlaCustomer customer, CustomerOrderDto customerOrderDto) {
        customer.setPhone(customerOrderDto.getPhone());
        customer.setEmail(customerOrderDto.getEmail());
        customer.setName(customerOrderDto.getName());
        customer.setSurname(customerOrderDto.getSurname());
    }

    public SlaCustomer registerNewCustomer(CustomerOrderDto customerOrderDto) {
        SlaCustomer customer = new SlaCustomer();
        edit(customer, customerOrderDto);

        customerDao.create(customer);
        return customer;
    }

    public SlaCustomer updateCustomerUser(CustomerOrderDto customerOrderDto) {
        SlaUser user = (SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SlaCustomer customer = user.getCustomer();
        edit(customer, customerOrderDto);
        customerDao.update(customer);
        return customer;
    }

    public SlaCustomer registerNewCustomerToUser(CustomerOrderDto customerOrderDto, SlaUser user) {
        SlaCustomer customer = registerNewCustomer(customerOrderDto);
        user.setCustomer(customer);
        userDao.update(user);
        return customer;
    }

    public SlaCustomer findById(int id) {
        return customerDao.getOneCustomer(((Integer) id).longValue());
    }

    public List<SlaCustomer> findCustomer(SlaCustomer customer) {
        return customerDao.find(customer);
    }
}
