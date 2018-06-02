package cz.cvut.fit.si1.sla.serviceImpl;


import cz.cvut.fit.si1.sla.dao.SlaChipCardDao;
import cz.cvut.fit.si1.sla.domain.SlaChipCard;
import cz.cvut.fit.si1.sla.service.SlaChipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaChipCardServiceImpl implements SlaChipCardService {

    @Autowired
    SlaChipCardDao chipCardDao;

    @Override
    public List<SlaChipCard> getAllList() {

        return chipCardDao.getAllChipCards();
    }

    @Override
    public void saveOrUpdate(SlaChipCard chipCard) {
        if (chipCard.isNew())
            chipCardDao.create(chipCard);
        else
            chipCardDao.update(chipCard);
    }

    @Override
    public void delete(SlaChipCard chipCard) {
        chipCardDao.delete(chipCard);
    }

    @Override
    public void deleteById(long id) {
        chipCardDao.deleteById(id);
    }

    @Override
    public SlaChipCard findById(long id) {
        return chipCardDao.getOneChipCard(id);
    }

    @Override
    public SlaChipCard findByRfidId(String rfid) {
        return chipCardDao.getChipCardByRfid(rfid);
    }

    @Override
    public SlaChipCard findByRfidIdFetch(String rfid) {
        return chipCardDao.getChipCardByRfidFetch(rfid);
    }
}
