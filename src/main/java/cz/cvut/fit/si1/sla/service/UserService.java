package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaEmployee;
import cz.cvut.fit.si1.sla.domain.SlaUser;
import cz.cvut.fit.si1.sla.dto.CustomerUserDto;
import cz.cvut.fit.si1.sla.dto.EmployeeUserRegisterDto;

import java.util.List;

/**
 * Used to manage users
 */
public interface UserService {
    /**
     * Founds user by his username
     *
     * @param username username
     * @return found user
     */
    SlaUser loadUserByUsername(String username);

    /**
     * Creates user from dto and register new customer tied to this user
     *
     * @param customerUserDto dto of customer
     * @return created user
     */
    SlaUser registerNewCustomer(CustomerUserDto customerUserDto);

    /**
     * Returns all users
     *
     * @return list of all users
     */
    List<SlaUser> getAllList();

    /**
     * Creates user from dto and register new employee tied to this user
     *
     * @param employeeUserRegisterDto dto of user registration
     * @return created employee
     */
    SlaEmployee registerNewEmployee(EmployeeUserRegisterDto employeeUserRegisterDto);
}