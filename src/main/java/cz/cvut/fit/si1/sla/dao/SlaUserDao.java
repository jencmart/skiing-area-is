package cz.cvut.fit.si1.sla.dao;

import cz.cvut.fit.si1.sla.domain.SlaUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SlaUserDao {

    void create(SlaUser user);

    SlaUser getOneUser(Long id);

    SlaUser getUserByUsername(String username);

    List<SlaUser> getAllUsers();

    void delete(SlaUser user);

    boolean deleteById(Long id);

    void update(SlaUser user);
}


