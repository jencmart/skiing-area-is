package cz.cvut.fit.si1.sla.service;

import cz.cvut.fit.si1.sla.domain.SlaEmployee;
import cz.cvut.fit.si1.sla.domain.SlaJob;

import java.util.List;

/**
 * Used to manage employees
 */
public interface SlaEmployeeService {
    /**
     * Returns list of all employees
     *
     * @return list of all employees
     */
    List<SlaEmployee> getAllList();

    /**
     * Finds employee by id
     *
     * @param id id of employee
     * @return found employee
     */
    SlaEmployee findById(int id);

    /**
     * Returns all jobs
     *
     * @return list of all jobs
     */
    List<SlaJob> getAllJobs();
}
