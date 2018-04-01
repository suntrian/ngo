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

    /**
     * 批量插入
     *
     * @param ts
     * @return
     */
    Integer insertByBeanList(List<T> ts);

    /**
     * 查询（根据主键ID查询）
     **/
    T getById(@Param("id") PK id);

    Collection<T> listByIdArray(PK[] ids);

    Collection<T> listByIdList(Collection<PK> ids);

    /**
     * 查询全部
     */
    Collection<T> listAll();

    /**
     * 查询全部，条件查询，分页查询
     */
    Collection<T> listByCondition(Map<String, Object> map);

    Integer deleteByBean(T t);

    Integer deleteById(@Param("id") PK id);

    Integer deleteByIdArray(PK[] ids);

    Integer deleteByBeanList(Collection<T> ts);

    Integer updateByBean(T t);

    Integer updateByMap(Map<String, Object> map);

    Integer updateByBeanList(Collection<T> ts);

    /**
     * 所有结果数
     *
     * @return
     */
    Integer countAll();

    /**
     * 条件查询数目
     *
     * @param map
     * @return
     */
    Integer countByCondition(Map<String, Object> map);

    Map queryOneBySql(String sql);

    List<Map> queryListBySql(String sql);

    Integer insertBySql(String sql);

    Integer updateBySql(String sql);

    Integer deleteBySql(String sql);
}
