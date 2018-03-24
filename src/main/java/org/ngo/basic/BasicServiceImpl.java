package org.ngo.basic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BasicServiceImpl<T, PK extends Serializable> implements IBasicService<T, PK> {
    private IBasicDao<T,PK> basicDao;

    public abstract IBasicDao<T,PK> getBasicDao();

    public BasicServiceImpl() {
        basicDao = getBasicDao();
    }

    @Override
    public int insert(T t) {
        return basicDao.insert(t);
    }

    @Override
    public int insert(Map<String, Object> map){
        return basicDao.insert(map);
    }

    @Override
    public int insert(List<T> ts) {
        return basicDao.insert(ts);
    }

    @Override
    public int dalete(T t) {
        return basicDao.delete(t);
    }

    @Override
    public int delete(PK id){
        return basicDao.delete(id);
    }

    @Override
    public int delete(PK[] ids){
        return basicDao.delete(ids);
    }

    @Override
    public int delete(List<T> ts){
        return basicDao.delete(ts);
    }

    @Override
    public int delete(Map<String, Object> map){
        return basicDao.delete(map);
    }

    @Override
    public int update(T t) {
        return basicDao.update(t);
    }

    @Override
    public int update(Map<String, Object> map){
        return basicDao.update(map);
    }

    @Override
    public int update(List<T> ts) {
        return basicDao.update(ts);
    }

    @Override
    public T get(PK id) {
        return (T)basicDao.get(id);
    }

    @Override
    public List<T> list(PK[] ids){
        return basicDao.list(ids);
    }

    @Override
    public List<T> list() {
        return basicDao.list();
    }

    @Override
    public List<T> list(Map<String, Object> map){
        return basicDao.list(map);
    }

    @Override
    public int count() {
        return basicDao.count();
    }

    @Override
    public int count(Map<String, Object> map){
        return basicDao.count(map);
    }
}
