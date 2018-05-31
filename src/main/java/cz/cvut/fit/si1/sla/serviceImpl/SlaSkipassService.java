package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.dao.SlaSkipassDao;
import cz.cvut.fit.si1.sla.domain.SlaSkipass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaSkipassService {

    @Autowired
    SlaSkipassDao skipassDao;

    public List<SlaSkipass> getAllList() {
        return skipassDao.getAllSkipass();
    }

    public void saveOrUpdate(SlaSkipass skipass) {
        if (skipass.isNew())
            skipassDao.create(skipass);
        else
            skipassDao.update(skipass);
    }

    public void delete(SlaSkipass skipass) {
        skipassDao.delete(skipass);
    }

    public boolean deleteById(long id) {
        return skipassDao.deleteById(id);
    }

    public SlaSkipass findById(long id) {
        return skipassDao.getOneSkipass(id);
    }
}
