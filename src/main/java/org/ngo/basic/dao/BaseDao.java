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
    int insert(T t);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param map
     * @return rows num affected
     **/
    int insert(Map<String, Object> map);

    /**
     * 批量插入
     *
     * @param ts
     * @return
     */
    int insert(List<T> ts);

    /**
     * 查询（根据主键ID查询）
     **/
    T get(@Param("id") PK id);

    Collection<T> list(PK[] ids);

    Collection<T> list(Collection<PK> ids);

    /**
     * 查询全部
     */
    Collection<T> list();

    /**
     * 查询全部，条件查询，分页查询
     */
    Collection<T> list(Map<String, Object> map);

    int delete(T t);

    int delete(@Param("id") PK id);

    int delete(PK[] ids);

    int delete(List<T> ts);

    int delete(Map<String, Object> map);

    int update(T t);

    int update(Map<String, Object> map);

    int update(List<T> ts);

    /**
     * 所有结果数
     *
     * @return
     */
    int count();

    /**
     * 条件查询数目
     *
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    Map queryOneBySql(String sql);

    List<Map> queryListBySql(String sql);

    Integer insertBySql(String sql);

    Integer updateBySql(String sql);
}
