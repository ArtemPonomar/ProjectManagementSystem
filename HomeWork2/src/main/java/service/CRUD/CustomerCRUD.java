package service.CRUD;

import entities.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerCRUD extends AbstractCRUD implements CRUDable<Customer> {

    public CustomerCRUD(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void create(Customer obj) throws SQLException {
        String sqlQuery = "INSERT INTO customers (name, sername) VALUES ('" + obj.getName() + "', '" + obj.getSername() + "')";
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
        obj.setId(getRecentID("customers"));
        System.out.println("Customer was added to table 'customers' with id - " + obj.getId() + " name - " +obj.getName() + " sername - " + obj.getSername());
    }

    @Override
    public Customer read(int id) throws SQLException {
        String sqlQuery = "SELECT id, name, sername FROM customers WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        ResultSet rs = statement.executeQuery(sqlQuery);
        if (rs.next()){
            int customerID = rs.getInt(1);
            String customerName = rs.getString(2);
            String customerSername = rs.getString(3);
            Customer customer = new Customer();
            customer.setId(customerID);
            customer.setName(customerName);
            customer.setSername(customerSername);
            return customer;
        }
        return null;
    }

    @Override
    public void update(int id, Customer obj) throws SQLException {
        delete(id);
        create(obj);
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM customers WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
    }
}
