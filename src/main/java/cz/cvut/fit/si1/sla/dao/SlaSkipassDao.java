package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaSkipass;

import java.util.List;


public interface SlaSkipassDao {

    void create(SlaSkipass skipass);

    SlaSkipass getOneSkipass(Long id);

    List<SlaSkipass> getAllSkipass();

    void delete(SlaSkipass skipass);

    boolean deleteById(Long id);

    void update(SlaSkipass skipass);
}


