package org.ngo.corporation;

import org.junit.Test;
import org.ngo.basic.BaseTest;
import org.ngo.corporation.dao.CorporationDao;
import org.ngo.corporation.model.Corporation;
import org.springframework.beans.factory.annotation.Autowired;

public class CorporationDaoTest extends BaseTest {

    @Autowired
    private CorporationDao corporationDao;

    @Test
    public void testAddCorporation() {
         Corporation corporation = corporationDao.selectOneById(1);
         assert corporation != null;
         logger.debug(corporation.toString());
    }

}
