package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaJob;

import java.util.List;

/**
 * Job dao
 */
public interface SlaJobDao {

    /**
     * Creates job
     *
     * @param job job
     */
    void create(SlaJob job);

    /**
     * Return job by id
     *
     * @param id id of job
     * @return found job
     */
    SlaJob getOneJob(Long id);

    /**
     * Return all jobs
     *
     * @return list of all jobs
     */
    List<SlaJob> getAllJobs();

    /**
     * Deletes specified job
     *
     * @param job job
     */
    void delete(SlaJob job);

    /**
     * Delete job by id
     *
     * @param id id of job
     * @return true when success
     */
    boolean deleteById(Long id);

    /**
     * Updates job
     *
     * @param job job with updated values
     */
    void update(SlaJob job);
}


