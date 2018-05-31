package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaJob;

import java.util.List;


public interface SlaJobDao {

    void create(SlaJob job);

    SlaJob getOneJob(Long id);

    List<SlaJob> getAllJobs();

    void delete(SlaJob job);

    boolean deleteById(Long id);

    void update(SlaJob job);
}


