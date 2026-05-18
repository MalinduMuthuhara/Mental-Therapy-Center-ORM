package edu.ijse.fx.orm.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T>extends SuperDAO{
    boolean save(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(String id) throws SQLException;
    T search(String id) throws SQLException;
    ArrayList<T> getAll() throws SQLException;
}
