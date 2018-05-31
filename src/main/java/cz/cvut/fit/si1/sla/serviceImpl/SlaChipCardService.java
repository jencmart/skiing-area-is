package cz.cvut.fit.si1.sla.serviceImpl;


import cz.cvut.fit.si1.sla.dao.SlaChipCardDao;
import cz.cvut.fit.si1.sla.domain.SlaChipCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaChipCardService {

    @Autowired
    SlaChipCardDao chipCardDao;

    public List<SlaChipCard> getAllList() {

        return chipCardDao.getAllChipCards();
    }

    public void saveOrUpdate(SlaChipCard chipCard) {
        if (chipCard.isNew())
            chipCardDao.create(chipCard);
        else
            chipCardDao.update(chipCard);
    }

    public void delete(SlaChipCard chipCard) {
        chipCardDao.delete(chipCard);
    }

    public void deleteById(long id) {
        chipCardDao.deleteById(id);
    }

    public SlaChipCard findById(long id) {
        return chipCardDao.getOneChipCard(id);
    }

    public SlaChipCard findByRfidId(String rfid) {
        return chipCardDao.getChipCardByRfid(rfid);
    }

    public SlaChipCard findByRfidIdFetch(String rfid) {
        return chipCardDao.getChipCardByRfidFetch(rfid);
    }
}
