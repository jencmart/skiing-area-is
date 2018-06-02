package cz.cvut.fit.si1.sla.serviceImpl;


import cz.cvut.fit.si1.sla.dao.SlaEmployeeDao;
import cz.cvut.fit.si1.sla.dao.SlaJobDao;
import cz.cvut.fit.si1.sla.domain.SlaEmployee;
import cz.cvut.fit.si1.sla.domain.SlaJob;
import cz.cvut.fit.si1.sla.service.SlaEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaEmployeeServiceImpl implements SlaEmployeeService {

    @Autowired
    SlaEmployeeDao employeeDao;

    @Autowired
    SlaJobDao jobDao;

    @Override
    public List<SlaEmployee> getAllList() {
        return employeeDao.getAllEmployees();

    }

    @Override
    public SlaEmployee findById(int id) {
        return employeeDao.getOneEmployee(((Integer) id).longValue());
    }

    @Override
    public List<SlaJob> getAllJobs() {
        return jobDao.getAllJobs();
    }
}
