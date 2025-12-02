package dao;

import java.util.List;

public interface Idao <T>{
    public  T insert(T obj);
    public boolean update(T obj);
    public boolean delete(T obj);
    public T findById(int id);
    public List<T> findAll();
}
