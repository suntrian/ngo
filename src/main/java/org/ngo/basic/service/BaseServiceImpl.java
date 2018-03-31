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
        return baseDao.insert(t);
    }

    @Override
    public int insert(Map<String, Object> map) {
        return baseDao.insert(map);
    }

    @Override
    public int insert(List<T> ts) {
        return baseDao.insert(ts);
    }

    @Override
    public int dalete(T t) {
        return baseDao.delete(t);
    }

    @Override
    public int delete(PK id) {
        return baseDao.delete(id);
    }

    @Override
    public int delete(PK[] ids) {
        return baseDao.delete(ids);
    }

    @Override
    public int delete(List<T> ts) {
        return baseDao.delete(ts);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return baseDao.delete(map);
    }

    @Override
    public int update(T t) {
        return baseDao.update(t);
    }

    @Override
    public int update(Map<String, Object> map) {
        return baseDao.update(map);
    }

    @Override
    public int update(List<T> ts) {
        return baseDao.update(ts);
    }

    @Override
    public T get(PK id) {
        return (T) baseDao.get(id);
    }

    @Override
    public Collection<T> list(PK[] ids) {
        return baseDao.list(ids);
    }

    @Override
    public Collection<T> list(Collection<PK> ids) {
        return baseDao.list(ids);
    }

    @Override
    public Collection<T> list() {
        return baseDao.list();
    }

    @Override
    public Collection<T> list(Map<String, Object> map) {
        return baseDao.list(map);
    }

    @Override
    public int count() {
        return baseDao.count();
    }

    @Override
    public int count(Map<String, Object> map) {
        return baseDao.count(map);
    }
}
