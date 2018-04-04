package org.ngo.corporation.dao;

import org.ngo.basic.dao.BaseDao;
import org.ngo.corporation.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao extends BaseDao<Department, Integer> {

    List<Department> selectChildrenDepart(Integer parent);

}
