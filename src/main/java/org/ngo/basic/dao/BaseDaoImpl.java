package org.ngo.basic.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.ngo.util.ReflectUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T, PK> {
    private String namespace = getDefaultNameSpace();

    private static final String THIS_CLASS = "org.ngo.basic.dao.BaseDao";
    private static final String INSERT_BY_BEAN = ".insertByBean";
    private static final String INSERT_BY_MAP = ".insertByMap";
    private static final String INSERT_BY_BEANLIST = ".insertByBeanList";
    private static final String DELETE_BY_ID = "deleteById";
    private static final String DELETE_BY_BEAN = ".deleteByBean";
    private static final String DELETE_BY_IDARRAY = ".deleteByIdArray";
    private static final String DELETE_BY_IDLIST = "";
    private static final String DELETE_BY_BEANLIST = ".deleteByBeanList";
    private static final String UPDATE_BY_BEAN = ".updateByBean";
    private static final String UPDATE_BY_MAP = ".updateByMap";
    private static final String UPDATA_BY_BEANLIST = ".updateByBeanList";
    private static final String SELECT_ONE_BY_ID = ".selectOneById";
    private static final String SELECT_LIST_BY_IDARRAY = ".selectListByIdArray";
    private static final String SELECT_LIST_BY_IDLIST = ".selectListByIdList";
    private static final String SELECT_MAP_BY_IDLIST = ".selectMapByIdList";
    private static final String SELECT_ALL = ".selectAll";
    private static final String SELECT_ONE_BY_SQL = ".selectOneBySql";
    private static final String SELECT_LIST_BY_SQL = ".selectListBySql";
    private static final String DELETE_BY_SQL = ".deleteBySql";
    private static final String UPDATE_BY_SQL = ".updateBySql";
    private static final String INSERT_BY_SQL = ".insertBySql";
    private static final String COUNT_ALL = ".countAll";


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
            return getSqlSession().insert(getNamespace() + INSERT_BY_BEAN, t);
        } catch (Exception e){
            throw new RuntimeException("insert t failed");
        }
    }

    @Override
    public Integer insertByMap(Map<String, Object> map) {
        try {
            return getSqlSession().insert(getNamespace() + INSERT_BY_MAP, map);
        } catch (Exception e){
            throw new RuntimeException("insert by map failed");
        }
    }

    @Override
    public Integer insertByBeanList(List<T> ts) {
        try {
            return getSqlSession().insert(getNamespace() + INSERT_BY_BEANLIST, ts);
        } catch (Exception e){
            throw new RuntimeException("insert batch failed");
        }
    }

    @Override
    public Integer deleteByBean(T t) {
        try {
            return getSqlSession().delete(getNamespace() + DELETE_BY_BEAN, t);
        } catch (Exception e){
            throw new RuntimeException("delete t failed");
        }
    }

    @Override
    public Integer deleteById(PK id) {
        try {
            return getSqlSession().delete(getNamespace() + DELETE_BY_ID, id);
        } catch (Exception e){
            throw new RuntimeException("delete by id failed");
        }
    }

    @Override
    public Integer deleteByIdArray(PK[] ids) {
        try {
            return getSqlSession().delete(getNamespace() + DELETE_BY_IDARRAY, ids);
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
            return getSqlSession().delete(getNamespace() + DELETE_BY_BEANLIST, ts);
        } catch (Exception e){
            throw new RuntimeException("delete by models failed");
        }
    }

    @Override
    public Integer updateByBean(T t) {
        try {
            return getSqlSession().update(getNamespace() + UPDATE_BY_BEAN,t);
        } catch (Exception e){
            throw new RuntimeException("update t failed");
        }
    }

    @Override
    public Integer updateByMap(Map<String, Object> map) {
        try {
            return getSqlSession().update(getNamespace() + UPDATE_BY_MAP, map);
        } catch (Exception e){
            throw new RuntimeException("update map failed");
        }
    }

    @Override
    public Integer updateByBeanList(Collection<T> ts) {
        try {
            return getSqlSession().update(getNamespace() + UPDATA_BY_BEANLIST, ts);
        } catch (Exception e){
            throw new RuntimeException("update by list failed");
        }
    }

    @Override
    public Integer countAll() {
        try {
            return getSqlSession().selectOne(getNamespace() + COUNT_ALL);
        } catch (Exception e){
            throw new RuntimeException("count all failed");
        }
    }


    @Override
    public T selectOneById(PK id) {
        try {
            return getSqlSession().selectOne(getNamespace() + SELECT_ONE_BY_ID, id);
        } catch (Exception e){
            throw new RuntimeException("get by id failed");
        }
    }

    @Override
    public Collection<T> selectListByIdArray(PK[] ids) {
        try {
            return getSqlSession().selectList(getNamespace() + SELECT_LIST_BY_IDARRAY, ids);
        } catch (Exception e){
            throw new RuntimeException("get by id array failed");
        }
    }

    @Override
    public Collection<T> selectListByIdList(Collection<PK> ids) {
        try {
            //return selectListByIdArray((PK[]) ids.toArray());
            return getSqlSession().selectList(getNamespace() + SELECT_LIST_BY_IDLIST, ids);
        } catch (Exception e) {
            throw new RuntimeException("get by id collection failed");
        }
    }

    @Override
    public Collection<T> selectAll() {
        try {
            return getSqlSession().selectList(getNamespace() + SELECT_ALL);
        }catch (Exception e){
            throw new RuntimeException("get all failed");
        }
    }

    @Override
    public Map<PK, T> selectMapByIdList(Collection<PK> ids){
        try {
            return getSqlSession().selectMap(getNamespace() + SELECT_LIST_BY_IDLIST, "id");
        } catch (Exception e) {
            throw new RuntimeException("select map failed");
        }
    }

    @Override
    public Map selectOneBySql(String sql) {
        return getSqlSession().selectOne(THIS_CLASS + SELECT_ONE_BY_SQL, sql);
    }

    @Override
    public List<Map> selectListBySql(String sql) {
        return getSqlSession().selectList(THIS_CLASS + SELECT_LIST_BY_SQL, sql);
    }

    @Override
    public Integer insertBySql(String sql) {
        return getSqlSession().insert(THIS_CLASS + INSERT_BY_SQL, sql);
    }

    @Override
    public Integer updateBySql(String sql) {
        return getSqlSession().update(THIS_CLASS + UPDATE_BY_SQL, sql);
    }

    @Override
    public Integer deleteBySql(String sql) {
        return getSqlSession().delete(THIS_CLASS + DELETE_BY_SQL, sql);
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
