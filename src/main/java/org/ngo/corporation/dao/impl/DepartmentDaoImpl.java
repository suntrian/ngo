package org.ngo.corporation.dao.impl;

import org.ngo.basic.dao.BaseDaoImpl;
import org.ngo.corporation.dao.DepartmentDao;
import org.ngo.corporation.model.Department;

import java.util.List;

public class DepartmentDaoImpl extends BaseDaoImpl<Department, Integer> implements DepartmentDao {
    @Override
    public List<Department> selectChildrenDepart(Integer parent) {
        return getSqlSession().selectList(this.getDefaultNameSpace() + ".selectChildren",parent);
    }
}
