package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaEmployee;

import java.util.List;

public interface SlaEmployeeDao {

    void create(SlaEmployee employee);

    SlaEmployee getOneEmployee(Long id);

    List<SlaEmployee> getAllEmployees();

    void delete(SlaEmployee employee);

    boolean deleteById(Long id);

    void update(SlaEmployee employee);
}