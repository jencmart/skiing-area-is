package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.dao.*;
import cz.cvut.fit.si1.sla.domain.SlaCustomer;
import cz.cvut.fit.si1.sla.domain.SlaEmployee;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.domain.SlaUserRole;
import cz.cvut.fit.si1.sla.dto.CustomerUserDto;
import cz.cvut.fit.si1.sla.dto.EmployeeUserRegisterDto;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Service
@Transactional
public class UserService {

    @Autowired
    SlaUserRoleDao userRoleDao;

    @Autowired
    SlaUserDao userDao;

    @Autowired
    SlaRoleDao roleDao;

    @Autowired
    SlaCustomerDao customerDao;

    @Autowired
    SlaEmployeeDao employeeDao;

    @Autowired
    SlaJobDao jobDao;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public SlaUser loadUserByUserame(String username) {
        return userDao.getUserByUsername(username);
    }

    public SlaUser registerNewCustomer(CustomerUserDto customerUserDto) {
        //create customer
        SlaCustomer customer = new SlaCustomer();
        customer.setPhone(customerUserDto.getPhone());
        customer.setEmail(customerUserDto.getEmail());
        customer.setSurname(customerUserDto.getSurname());
        customer.setName(customerUserDto.getName());
        customerDao.create(customer);

        // create user account linked to customer
        SlaUser user = new SlaUser();
        user.setUsername(customerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(customerUserDto.getPassword()));
        user.setCustomer(customer);
        userDao.create(user);

        // create user-role linked to user
        SlaUserRole userRole = new SlaUserRole();
        userRole.setUser(user);

        userRole.setRole(roleDao.getRoleByName("ROLE_CUSTOMER"));
        userRoleDao.create(userRole);

        Hibernate.initialize(user.getCustomer());
        return user;
    }

    public List<SlaUser> getAllList() {
        return userDao.getAllUsers();
    }

    public SlaEmployee registerNewEmployee(EmployeeUserRegisterDto employeeUserRegisterDto) {

        SlaEmployee employee = new SlaEmployee();

        employee.setName(employeeUserRegisterDto.getName());
        employee.setSurname(employeeUserRegisterDto.getSurname());
        employee.setPhone(employeeUserRegisterDto.getPhone());
        employee.setForeignLanguages(employeeUserRegisterDto.getForeignLanguages());
        employee.setJob(jobDao.getOneJob(((Integer) employeeUserRegisterDto.getJob()).longValue()));

        Random random = new Random();
        employee.setIdNumber(((Integer) random.nextInt()).toString());
        employeeDao.create(employee);

        SlaUser user = new SlaUser();
        user.setUsername(employeeUserRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(employeeUserRegisterDto.getPassword()));
        user.setEmployee(employee);
        userDao.create(user);

        SlaUserRole userRole = new SlaUserRole();
        userRole.setUser(user);

        userRole.setRole(roleDao.getRoleByName("ROLE_ADMIN"));
        userRoleDao.create(userRole);

        Hibernate.initialize(user.getCustomer());

        return employee;
    }
}
