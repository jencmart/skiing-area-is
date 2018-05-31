package cz.cvut.fit.si1.sla.serviceImpl;


import cz.cvut.fit.si1.sla.dao.SlaAddressDao;
import cz.cvut.fit.si1.sla.domain.SlaAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlaAddressService {

    @Autowired
    private SlaAddressDao slaAddressDao;

    public List<SlaAddress> getAllList() {
        return slaAddressDao.getAllAddresses();
    }
}
