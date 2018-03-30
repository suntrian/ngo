package org.ngo.basic.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService<T, PK extends Serializable> {
    int insert(T t);

    int insert(Map<String, Object> map);

    int insert(List<T> ts);

    int dalete(T t);

    int delete(PK id);

    int delete(PK[] ids);

    int delete(List<T> ts);

    int delete(Map<String, Object> map);

    int update(T t);

    int update(Map<String, Object> map);

    int update(List<T> ts);

    T get(PK id);

    Collection<T> list(PK[] ids);

    Collection<T> list(Collection<PK> ids);

    Collection<T> list();

    Collection<T> list(Map<String, Object> map);

    int count();

    int count(Map<String, Object> map);
}
