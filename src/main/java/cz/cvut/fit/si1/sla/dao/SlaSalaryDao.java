package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaSalary;

import java.util.List;

/**
 * Salary dao
 */
public interface SlaSalaryDao {

    /**
     * Creates salary
     *
     * @param salary salary
     */
    void create(SlaSalary salary);

    /**
     * Returns salary by id
     *
     * @param id id of salary
     * @return found salary
     */
    SlaSalary getOneSalary(Long id);

    /**
     * Returns all salaries
     *
     * @return list of all salaries
     */
    List<SlaSalary> getAllsSalaries();

    /**
     * Deletes specified salary
     *
     * @param salary salary
     */
    void delete(SlaSalary salary);

    /**
     * Deletes salary by id
     *
     * @param id id of salary
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates salary
     *
     * @param salary salary with updated values
     */
    void update(SlaSalary salary);
}