package org.ngo.basic.service;

import org.ngo.basic.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    private BaseDao<T, PK> baseDao;

    @Override
    public int insert(T t) {
        return baseDao.insertByBean(t);
    }

    @Override
    public int insert(Map<String, Object> map) {
        return baseDao.insertByMap(map);
    }

    @Override
    public int insert(List<T> ts) {
        return baseDao.insertByBeanList(ts);
    }

    @Override
    public int dalete(T t) {
        return baseDao.deleteByBean(t);
    }

    @Override
    public int delete(PK id) {
        return baseDao.deleteById(id);
    }

    @Override
    public int delete(PK[] ids) {
        return baseDao.deleteByIdArray(ids);
    }

    @Override
    public int delete(List<T> ts) {
        return baseDao.deleteByBeanList(ts);
    }

    @Override
    public int update(T t) {
        return baseDao.updateByBean(t);
    }

    @Override
    public int update(Map<String, Object> map) {
        return baseDao.updateByMap(map);
    }

    @Override
    public int update(List<T> ts) {
        return baseDao.updateByBeanList(ts);
    }

    @Override
    public T get(PK id) {
        return (T) baseDao.getById(id);
    }

    @Override
    public Collection<T> list(PK[] ids) {
        return baseDao.listByIdArray(ids);
    }

    @Override
    public Collection<T> list(Collection<PK> ids) {
        return baseDao.listByIdList(ids);
    }

    @Override
    public Collection<T> list() {
        return baseDao.listAll();
    }

    @Override
    public Collection<T> list(Map<String, Object> map) {
        return baseDao.listByCondition(map);
    }

    @Override
    public int count() {
        return baseDao.countAll();
    }

    @Override
    public int count(Map<String, Object> map) {
        return baseDao.countByCondition(map);
    }
}
