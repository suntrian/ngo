package org.ngo.basic.dao;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK extends Serializable> {
    /**
     * insert model
     *
     * @param t
     * @return rows count affected
     */
    Integer insertByBean(T t);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param map
     * @return rows num affected
     **/
    Integer insertByMap(Map<String, Object> map);

    Integer insertByBeanList(List<T> ts);

    T selectOneById(@Param("id") PK id);

    Collection<T> selectListByIdArray(PK[] ids);

    Collection<T> selectListByIdList(Collection<PK> ids);

    Collection<T> selectAll();

    Map<PK, T> selectMapByIdList(Collection<PK> ids);

    Integer deleteByBean(T t);

    Integer deleteById(@Param("id") PK id);

    Integer deleteByIdArray(PK[] ids);

    Integer deleteByIdList(Collection<PK> ids);

    Integer deleteByBeanList(Collection<T> ts);

    Integer updateByBean(T t);

    Integer updateByMap(Map<String, Object> map);

    Integer updateByBeanList(Collection<T> ts);

    Integer countAll();

    Map<PK, T> selectMapByIdList(Collection<PK> ids, String mapKey);

    Map selectOneBySql(String sql);

    List<Map> selectListBySql(String sql);

    Integer insertBySql(String sql);

    Integer updateBySql(String sql);

    Integer deleteBySql(String sql);

    void clearCache();

    void closeSession();
}
