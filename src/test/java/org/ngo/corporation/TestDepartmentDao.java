package org.ngo.corporation;

import org.junit.Test;
import org.ngo.basic.TestBase;
import org.ngo.corporation.dao.DepartmentDao;
import org.ngo.corporation.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class TestDepartmentDao extends TestBase {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testinsertDepartment(){
        Department department = new Department();
        department.setName("GGGGG");
        department.setType(4);
        department.setParent(1);
        departmentDao.insertByBean(department);
        Assert.notNull(department.getId());
        logger.debug(department.toString());

    }

    @Test
    public void testSelectDepart(){
        Department department = departmentDao.selectOneById(1);
        Assert.notNull(department, "is null");
        logger.debug(department.getId().toString());
        Department parent = department.getParentDepart();
        Assert.isNull(parent, "is null");
        //logger.debug(parent.toString());
        List<Department> children = department.getChildrenDepart();
        Assert.notEmpty(children,"is empty");
        logger.debug(children.get(0).toString());

    }


}
