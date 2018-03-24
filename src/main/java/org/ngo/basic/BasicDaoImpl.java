package org.ngo.basic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public abstract class BasicDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements IBasicDao<T, PK>{
    private String namespace;

    public void setNamespace(String namespace){
        this.namespace = namespace;
    }
    public String getNamespace(){
        return this.namespace;
    }

    @Override
    public int insert(T t) {
        try {
            return getSqlSession().insert(getNamespace() + ".insertT", t);
        } catch (Exception e){
            throw new RuntimeException("insert t failed");
        }
    }

    @Override
    public int insert(Map<String, Object> map){
        try {
            return getSqlSession().insert(getNamespace() + ".insertMap", map);
        } catch (Exception e){
            throw new RuntimeException("insert by map failed");
        }
    }

    @Override
    public int insert(List<T> ts){
        try {
            return getSqlSession().insert(getNamespace() + ".insertList", ts);
        } catch (Exception e){
            throw new RuntimeException("insert batch failed");
        }
    }

    @Override
    public int delete(T t) {
        try {
            return getSqlSession().delete(getNamespace() + ".deleteT",t);
        } catch (Exception e){
            throw new RuntimeException("delete t failed");
        }
    }

    @Override
    public int delete(PK id){
        try {
            return getSqlSession().delete(getNamespace() + ".deleteId", id);
        } catch (Exception e){
            throw new RuntimeException("delete by id failed");
        }
    }

    @Override
    public int delete(PK[] ids){
        try {
            return getSqlSession().delete(getNamespace() + ".deleteIds", ids);
        } catch (Exception e){
            throw new RuntimeException("delete by id array failed");
        }
    }

    @Override
    public int delete(List<T> ts){
        try {
            return getSqlSession().delete(getNamespace() + ".deleteList", ts);
        } catch (Exception e){
            throw new RuntimeException("delete by models failed");
        }
    }

    @Override
    public int delete(Map<String, Object> map){
        try {
            return getSqlSession().delete(getNamespace() + ".deleteMap", map);
        } catch (Exception e ){
            throw new RuntimeException("delete by map failed");
        }
    }

    @Override
    public int update(T t) {
        try {
            return getSqlSession().update(getNamespace() + ".updateT",t);
        } catch (Exception e){
            throw new RuntimeException("update t failed");
        }
    }

    @Override
    public int update(Map<String, Object> map){
        try {
            return getSqlSession().update(getNamespace() + ".updateMap", map);
        } catch (Exception e){
            throw new RuntimeException("update map failed");
        }
    }

    @Override
    public int update(List<T> ts){
        try {
            return getSqlSession().update(getNamespace() + ".updateTs", ts);
        } catch (Exception e){
            throw new RuntimeException("update by list failed");
        }
    }

    @Override
    public T get(PK id) {
        try {
            return getSqlSession().selectOne(getNamespace() + ".selectId", id);
        } catch (Exception e){
            throw new RuntimeException("get by id failed");
        }
    }

    @Override
    public List<T> list(PK[] ids){
        try {
            return getSqlSession().selectList(getNamespace() + ".selectIds", ids);
        } catch (Exception e){
            throw new RuntimeException("get by id array failed");
        }
    }

    @Override
    public List<T> list() {
        try {
            return getSqlSession().selectList(getNamespace() + ".get");
        }catch (Exception e){
            throw new RuntimeException("get all failed");
        }
    }

    @Override
    public List<T> list(Map<String,Object> map){
        try {
            return getSqlSession().selectList(getNamespace() + ".selectMap", map);
        } catch (Exception e){
            throw new RuntimeException("get by map failed");
        }
    }

    @Override
    public int count() {
        try {
            return getSqlSession().selectOne(getNamespace() + ".count");
        } catch (Exception e){
            throw new RuntimeException("count all failed");
        }
    }

    @Override
    public int count(Map<String, Object> map){
        try {
            return getSqlSession().selectOne(getNamespace() + ".count", map);
        } catch (Exception e){
            throw new RuntimeException("count by map failed");
        }
    }

}
