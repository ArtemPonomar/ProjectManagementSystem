package service.CRUD;

import java.sql.SQLException;

public interface CRUDable<T> {
    void create(T obj) throws SQLException;
    T read(int id) throws SQLException;
    void update(int id, T obj) throws SQLException;
    void delete(int id) throws SQLException;
}
