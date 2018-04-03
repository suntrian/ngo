package org.ngo.corporation.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.corporation.model.Position;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDao extends BaseDao<Position, Integer> {
}
