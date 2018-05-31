package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaSalary;

import java.util.List;

public interface SlaSalaryDao {

    void create(SlaSalary salary);

    SlaSalary getOneSalary(Long id);

    List<SlaSalary> getAllsSalaries();

    void delete(SlaSalary salary);

    boolean deleteById(Long id);

    void update(SlaSalary salary);
}


