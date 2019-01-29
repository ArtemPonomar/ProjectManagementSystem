package service.CRUD;

import entities.Developer;
import entities.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectCRUD extends AbstractCRUD implements CRUDable<Project> {

    public ProjectCRUD(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void create(Project obj) throws SQLException {
        String sqlQuery = "INSERT INTO projects (name, description) VALUES ('" + obj.getName() + "', '" + obj.getDescription() + "')";
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
        obj.setId(getRecentID("projects"));
        System.out.println("Project was added to table 'projects' with"
                + " id - " + obj.getId()
                + " name - " +obj.getName()
                + " description - " + obj.getDescription());
    }

    @Override
    public Project read(int id) throws SQLException {
        String sqlQuery = "SELECT id, name, description FROM projects WHERE id = " + id;
        //System.out.println(sqlQuery);
        ResultSet rs = statement.executeQuery(sqlQuery);
        if (rs.next()){
            int newID = rs.getInt(1);
            String name = rs.getString(2);
            String description = rs.getString(3);
            Project project = new Project();
            project.setId(newID);
            project.setName(name);
            project.setDescription(description);
            return project;
        }
        return null;
    }

    @Override
    public void update(int id, Project obj) throws SQLException {
        delete(id);
        create(obj);
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM projects WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
    }
}
