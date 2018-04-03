package org.ngo.corporation.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.corporation.model.Corporation;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationDao extends BaseDao<Corporation, Integer> {
}
