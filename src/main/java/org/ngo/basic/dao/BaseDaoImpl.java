package org.ngo.basic.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.ngo.util.ReflectUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T, PK> {
    private String namespace = getDefaultNameSpace();

    public void setNamespace(String namespace){
        this.namespace = namespace;
    }
    public String getNamespace(){
        return this.namespace;
    }

    protected String getDefaultNameSpace() {
        Class<T> clazz = ReflectUtil.getClassGenricType(this.getClass());
        return clazz.getName();// + "Dao";
    }

    @Override
    public Integer insertByBean(T t) {
        try {
            return getSqlSession().insert(getNamespace() + ".insertByBean", t);
        } catch (Exception e){
            throw new RuntimeException("insert t failed");
        }
    }

    @Override
    public Integer insertByMap(Map<String, Object> map) {
        try {
            return getSqlSession().insert(getNamespace() + ".insertByMap", map);
        } catch (Exception e){
            throw new RuntimeException("insert by map failed");
        }
    }

    @Override
    public Integer insertByBeanList(List<T> ts) {
        try {
            return getSqlSession().insert(getNamespace() + ".insertByBeanList", ts);
        } catch (Exception e){
            throw new RuntimeException("insert batch failed");
        }
    }

    @Override
    public Integer deleteByBean(T t) {
        try {
            return getSqlSession().delete(getNamespace() + ".deleteByBean", t);
        } catch (Exception e){
            throw new RuntimeException("delete t failed");
        }
    }

    @Override
    public Integer deleteById(PK id) {
        try {
            return getSqlSession().delete(getNamespace() + ".deleteById", id);
        } catch (Exception e){
            throw new RuntimeException("delete by id failed");
        }
    }

    @Override
    public Integer deleteByIdArray(PK[] ids) {
        try {
            return getSqlSession().delete(getNamespace() + ".deleteByIdArray", ids);
        } catch (Exception e){
            throw new RuntimeException("delete by id array failed");
        }
    }

    @Override
    public Integer deleteByIdList(Collection<PK> ids){
        return deleteByIdArray((PK[]) ids.toArray());
    }

    @Override
    public Integer deleteByBeanList(Collection<T> ts) {
        try {
            return getSqlSession().delete(getNamespace() + ".deleteByBeanList", ts);
        } catch (Exception e){
            throw new RuntimeException("delete by models failed");
        }
    }

    @Override
    public Integer updateByBean(T t) {
        try {
            return getSqlSession().update(getNamespace() + ".updateByBean",t);
        } catch (Exception e){
            throw new RuntimeException("update t failed");
        }
    }

    @Override
    public Integer updateByMap(Map<String, Object> map) {
        try {
            return getSqlSession().update(getNamespace() + ".updateByMap", map);
        } catch (Exception e){
            throw new RuntimeException("update map failed");
        }
    }

    @Override
    public Integer updateByBeanList(Collection<T> ts) {
        try {
            return getSqlSession().update(getNamespace() + ".updateTs", ts);
        } catch (Exception e){
            throw new RuntimeException("update by list failed");
        }
    }

    @Override
    public Integer countAll() {
        try {
            return getSqlSession().selectOne(getNamespace() + ".count");
        } catch (Exception e){
            throw new RuntimeException("count all failed");
        }
    }

    @Override
    public Integer countByCondition(Map<String, Object> map) {
        try {
            return getSqlSession().selectOne(getNamespace() + ".count", map);
        } catch (Exception e){
            throw new RuntimeException("count by map failed");
        }
    }

    @Override
    public Map queryOneBySql(String sql) {
        return getSqlSession().selectOne("org.ngo.basic.dao.BaseDao" + ".queryOneBySql", sql);
    }

    @Override
    public List<Map> queryListBySql(String sql) {
        return getSqlSession().selectList("org.ngo.basic.dao.BaseDao" + ".queryListBySql", sql);
    }

    @Override
    public Integer insertBySql(String sql) {
        return getSqlSession().insert("org.ngo.basic.dao.BaseDao" + ".insertBySql", sql);
    }

    @Override
    public Integer updateBySql(String sql) {
        return getSqlSession().update("org.ngo.basic.dao.BaseDao" + ".updateBySql", sql);
    }

    @Override
    public Integer deleteBySql(String sql) {
        return getSqlSession().delete(this.getClass().getSuperclass().getName() + ".deleteBySql", sql);
    }


    @Override
    public T getById(PK id) {
        try {
            return getSqlSession().selectOne(getNamespace() + ".get", id);
        } catch (Exception e){
            throw new RuntimeException("get by id failed");
        }
    }

    @Override
    public Collection<T> listByIdArray(PK[] ids) {
        try {
            return getSqlSession().selectList(getNamespace() + ".selectByIdArray", ids);
        } catch (Exception e){
            throw new RuntimeException("get by id array failed");
        }
    }

    @Override
    public Collection<T> listByIdList(Collection<PK> ids) {
        try {
            return getSqlSession().selectList(getNamespace() + ".selectByIdList", ids);
        } catch (Exception e) {
            throw new RuntimeException("get by id collection failed");
        }
    }

    @Override
    public Collection<T> listAll() {
        try {
            return getSqlSession().selectList(getNamespace() + ".selectAll");
        }catch (Exception e){
            throw new RuntimeException("get all failed");
        }
    }

    @Override
    public Map<PK, T> mapByIdList(Collection<PK> ids){
        try {
            return getSqlSession().selectMap(getNamespace() + "selectMapByIdList", "id");
        } catch (Exception e) {
            throw new RuntimeException("select map failed");
        }
    }


    @Override
    public void clearCache() {
        getSqlSession().clearCache();
    }

    @Override
    public void closeSession(){
        getSqlSession().close();
    }


}
