package service.CRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractCRUD {
    protected Statement statement;

    private AbstractCRUD(){}

    public AbstractCRUD(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

//    private void executeUpdate(String sql) {
//        try {
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    protected int getRecentID(String table){
        String sql = "SELECT MAX(id) FROM ${1}";
        sql = sql.replace("${1}", table);
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
//    public void create(String table, String[] values) {
//        String sqlQuery = "INSERT INTO " + table + " (";
//        for (String value : values) {
//            sqlQuery += value + ", ";
//        }
//        sqlQuery += ") VALUES (";
//        for (String value : values) {
//            sqlQuery += value + ", ";
//        }
//        sqlQuery += ")";
//        executeUpdate(sqlQuery);
//    }
}
