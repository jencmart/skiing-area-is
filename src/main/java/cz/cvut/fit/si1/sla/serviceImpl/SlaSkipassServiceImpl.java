package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.dao.SlaSkipassDao;
import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import cz.cvut.fit.si1.sla.service.SlaSkipassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaSkipassServiceImpl implements SlaSkipassService {

    @Autowired
    SlaSkipassDao skipassDao;

    @Override
    public List<SlaSkipass> getAllList() {
        return skipassDao.getAllSkipass();
    }

    @Override
    public void saveOrUpdate(SlaSkipass skipass) {
        if (skipass.isNew())
            skipassDao.create(skipass);
        else
            skipassDao.update(skipass);
    }

    @Override
    public void delete(SlaSkipass skipass) {
        skipassDao.delete(skipass);
    }

    @Override
    public boolean deleteById(long id) {
        return skipassDao.deleteById(id);
    }

    @Override
    public SlaSkipass findById(long id) {
        return skipassDao.getOneSkipass(id);
    }
}
