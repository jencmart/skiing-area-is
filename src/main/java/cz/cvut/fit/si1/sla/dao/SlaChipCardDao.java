package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaChipCard;

import java.util.List;

public interface SlaChipCardDao {

    void create(SlaChipCard chipCard);

    SlaChipCard getOneChipCard(Long id);

    SlaChipCard getChipCardByRfid(String rfid);

    List<SlaChipCard> getAllChipCards();

    List<SlaChipCard> getCurrentlyRentedChipCards();

    void delete(SlaChipCard chipCard);

    boolean deleteById(Long id);

    void update(SlaChipCard chipCard);


    SlaChipCard getChipCardByRfidFetch(String rfid);
}


