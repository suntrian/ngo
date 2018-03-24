package org.ngo.basic;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBasicDao<T, PK extends Serializable> {
    /**
     * insert model bean
     * @param t
     * @return rows count affected
     */
    public int insert(T t);

    /**
     * 添加 （匹配有值的字段）
     * @param map
     * @return rows num affected
     **/
    public int insert(Map<String,Object> map);

    /**
     * 批量插入
     * @param ts
     * @return
     */
    public int insert(List<T> ts);
    /**
     * 查询（根据主键ID查询）
     *
     **/
    public T get(@Param("id")PK id);

    public List<T> list(PK[] ids);

    /**
     * 查询全部
     * */
    public List<T> list();

    /**
     *
     * 查询全部，条件查询，分页查询
     * */
    public List<T> list(Map<String, Object> map);

    public int delete(T t);

    public int delete(@Param("id")PK id);

    public int delete(PK[] ids);

    public int delete(List<T> ts);

    public int delete(Map<String, Object> map);

    public int update(T t);

    public int update(Map<String, Object> map);

    public int update(List<T> ts);

    /**
     * 所有结果数
     * @return
     */
    public int count();

    /**
     * 条件查询数目
     * @param map
     * @return
     */
    public int count(Map<String, Object> map);
}
