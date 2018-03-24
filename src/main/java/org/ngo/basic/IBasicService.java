package org.ngo.basic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBasicService<T, PK extends Serializable>  {
    public int insert(T t);
    public int insert(Map<String, Object> map);
    public int insert(List<T> ts);
    public int dalete(T t);
    public int delete(PK id);
    public int delete(PK[] ids);
    public int delete(List<T> ts);
    public int delete(Map<String, Object> map);
    public int update(T t);
    public int update(Map<String, Object> map);
    public int update(List<T> ts);
    public T get(PK id);
    public List<T> list(PK[] ids);
    public List<T> list();
    public List<T> list(Map<String, Object> map);
    public int count();
    public int count(Map<String, Object> map);
}
