package org.ngo.corporation.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.corporation.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends BaseDao<Department, Integer> {
}
