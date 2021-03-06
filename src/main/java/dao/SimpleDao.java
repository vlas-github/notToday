package dao;


import java.util.List;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
public interface SimpleDao<T> {
    T getById(String id);
    T save(T t);
    T update(T t);
    List<T> getList();
    List<T> getList(T pattern);
}
