package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaEmployee;

import java.util.List;

/**
 * employee dao
 */
public interface SlaEmployeeDao {

    /**
     * Creates employee
     *
     * @param employee employee
     */
    void create(SlaEmployee employee);

    /**
     * Returns one employee by id
     *
     * @param id id of employee
     * @return found employee
     */
    SlaEmployee getOneEmployee(Long id);

    /**
     * Returns all employees
     *
     * @return list of all employees
     */
    List<SlaEmployee> getAllEmployees();

    /**
     * Deletes specified employee
     *
     * @param employee employee
     */
    void delete(SlaEmployee employee);

    /**
     * Deltes employee by id
     *
     * @param id id of employee
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates employee
     *
     * @param employee employee with updated value
     */
    void update(SlaEmployee employee);
}