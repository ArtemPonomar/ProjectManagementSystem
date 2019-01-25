package service.CRUD;

import entities.Customer;
import entities.Developer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeveloperCRUD extends AbstractCRUD implements CRUDable<Developer>{

    public DeveloperCRUD(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void create(Developer obj) throws SQLException {
        String sqlQuery = "INSERT INTO developers (name, sername, salary) VALUES ('" + obj.getName() + "', '" + obj.getSername() + "', '" + obj.getSalary() + "')";
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
        obj.setId(getRecentID("developers"));
        System.out.println("Developer was added to table 'developers' with id - " + obj.getId() + " name - " +obj.getName() + " sername - " + obj.getSername() + " salary - " + obj.getSalary());
    }

    @Override
    public Developer read(int id) throws SQLException {
        String sqlQuery = "SELECT id, name, sername, salary FROM developers WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        ResultSet rs = statement.executeQuery(sqlQuery);
        if (rs.next()){
            int newID = rs.getInt(1);
            String name = rs.getString(2);
            String sername = rs.getString(3);
            int salary = rs.getInt(4);
            Developer developer = new Developer();
            developer.setId(newID);
            developer.setName(name);
            developer.setSername(sername);
            developer.setSalary(salary);
            return developer;
        }
        return null;
    }

    @Override
    public void update(int id, Developer obj) throws SQLException {
        delete(id);
        create(obj);
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM developers WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
    }
}
