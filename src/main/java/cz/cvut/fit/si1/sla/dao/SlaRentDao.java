package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaRent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SlaRentDao {

    void create(SlaRent slaRent);

    SlaRent getOneRent(Long id);

    List<SlaRent> getAllRents();

    void delete(SlaRent slaRent);

    boolean deleteById(Long id);

    void update(SlaRent slaRent);
}


