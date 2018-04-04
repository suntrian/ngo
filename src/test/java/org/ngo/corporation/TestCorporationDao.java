package org.ngo.corporation;

import org.junit.Test;
import org.ngo.basic.TestBase;
import org.ngo.corporation.dao.CorporationDao;
import org.ngo.corporation.model.Corporation;
import org.ngo.util.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestCorporationDao extends TestBase {

    @Autowired
    private CorporationDao corporationDao;

    @Test
    public void testGetCorporation() {
         Corporation corporation = corporationDao.selectOneById(1);
         assert corporation != null;
         logger.debug(corporation.toString());
    }

    @Test
    public void testAddOneCorporation(){
        Corporation corporation = new Corporation();
        corporation.setName(RandomUtil.nextString(12));
        corporation.setAddress(RandomUtil.nextString(32));
        corporation.setAddress(RandomUtil.nextString(35));
        corporation.setCode(RandomUtil.nextString(4));
        corporation.setType(new RandomUtil().nextInt(10));
        corporationDao.insertByBean(corporation);
        assert corporation.getId() != null;
        logger.debug(corporation.toString());
    }

    @Test
    public void testAddCorporation(){
        List<Corporation> corporations = new ArrayList<>();
        for (int i =0; i < 4; i++){
            Corporation corporation = new Corporation();
            corporation.setName(RandomUtil.nextString(10));
            corporation.setAccountablePerson(RandomUtil.nextString(12));
            corporation.setAddress(RandomUtil.nextString(20));
            corporations.add(corporation);
        }
        corporationDao.insertByBeanList(corporations);
        assert corporations.get(0).getId() != null;
        logger.debug(corporations.toString());
    }

}
