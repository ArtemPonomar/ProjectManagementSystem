package service.CRUD;

import entities.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyCRUD extends AbstractCRUD implements CRUDable<Company>{

    public CompanyCRUD(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void create(Company obj) throws SQLException {
        String sqlQuery = "INSERT INTO companies (name, description) VALUES ('" + obj.getName() + "', '" + obj.getDescription() + "')";
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
        obj.setId(getRecentID("companies"));
        System.out.println("Company was added to table 'companies' with id - " + obj.getId() + " name - " +obj.getName() + " description - " + obj.getDescription());
    }

    @Override
    public Company read(int id) throws SQLException {
        String sqlQuery = "SELECT id, name, description FROM companies WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        ResultSet rs = statement.executeQuery(sqlQuery);
        if (rs.next()){
            int companyID = rs.getInt(1);
            String companyName = rs.getString(2);
            String companyDescription = rs.getString(3);
            Company company = new Company(companyName, companyDescription);
            company.setId(companyID);
            return company;
        }
        return null;
    }

    @Override
    public void update(int id, Company obj) throws SQLException {
        delete(id);
        create(obj);
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM companies WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
    }
}
