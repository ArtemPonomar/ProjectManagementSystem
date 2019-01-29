package service.CRUD;

import entities.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SkillCRUD extends AbstractCRUD implements CRUDable<Skill> {

    public SkillCRUD(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void create(Skill obj) throws SQLException {
        String sqlQuery = "INSERT INTO skills (type, level) VALUES ('" + obj.getType() + "', '" + obj.getLevel() + "')";
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
        obj.setId(getRecentID("skills"));
        System.out.println("Skill was added to table 'skills' with"
                + " id - " + obj.getId()
                + " type - " +obj.getType()
                + " level - " + obj.getLevel());
    }

    @Override
    public Skill read(int id) throws SQLException {
        String sqlQuery = "SELECT id, type, level FROM projects WHERE id = " + id;
        System.out.println(sqlQuery);
        ResultSet rs = statement.executeQuery(sqlQuery);
        if (rs.next()){
            int newID = rs.getInt(1);
            String type = rs.getString(2);
            String level = rs.getString(3);
            Skill skill = new Skill();
            skill.setId(newID);
            skill.setType(type);
            skill.setLevel(level);
            return skill;
        }
        return null;
    }

    @Override
    public void update(int id, Skill obj) throws SQLException {
        delete(id);
        create(obj);
    }

    @Override
    public void delete(int id) throws SQLException {
        String sqlQuery = "DELETE FROM skills WHERE id = " + Integer.toString(id);
        System.out.println(sqlQuery);
        statement.execute(sqlQuery);
    }
}
