package service;

import entities.*;
import service.CRUD.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DAO {
    private Connection connection;
    private Statement statement;
    private CompanyCRUD companyCRUD;
    private CustomerCRUD customerCRUD;
    private DeveloperCRUD developerCRUD;
    private ProjectCRUD projectCRUD;
    private SkillCRUD skillCRUD;

    public DAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/it_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "0000");
            statement = connection.createStatement();
            System.out.println("database initialized");
            companyCRUD = new CompanyCRUD(connection);
            customerCRUD = new CustomerCRUD(connection);
            developerCRUD = new DeveloperCRUD(connection);
            projectCRUD = new ProjectCRUD(connection);
            skillCRUD = new SkillCRUD(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalDeveloperSalaryOfProject(Project project) throws SQLException {
        String sqlQuery = "SELECT sum(developers.salary)\n" +
                "FROM ((project_allocation\n" +
                "INNER JOIN developers ON developers.id = project_allocation.developer_id)\n" +
                "INNER JOIN projects ON projects.id = project_allocation.project_id)\n" +
                "WHERE projects.name = '" + project.getName() + "'";
        ResultSet rs = statement.executeQuery(sqlQuery);
        if (rs.next()){
            System.out.println("Total salary of developers working on project " + project.getName() + " = " + rs.getInt(1));
            return rs.getInt(1);
        }
        return -1;
    }

    public List<Developer> getDevelopersOnProject(Project project) throws SQLException {
        List<Developer> developers = new ArrayList<>();
        statement = connection.createStatement();
        String sqlQuery = "SELECT developers.id\n" +
                "FROM ((project_allocation\n" +
                "INNER JOIN developers ON developers.id = project_allocation.developer_id)\n" +
                "INNER JOIN projects ON projects.id = project_allocation.project_id)\n" +
                "WHERE projects.name = '" + project.getName() + "'";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()){
            developers.add(developerCRUD.read(resultSet.getInt(1)));
        }
        return developers;
    }

    public List<Developer> getDevelopersWithSkillType(Skill skill) throws SQLException {
        statement = connection.createStatement();
        List<Developer> developers = new ArrayList<>();
        String sqlQuery = "SELECT developers.id\n" +
                "FROM ((skillset\n" +
                "INNER JOIN developers ON developers.id = skillset.developer_id)\n" +
                "INNER JOIN skills ON skills.id = skillset.skill_id)\n" +
                "WHERE skills.type = '" + skill.getType() + "'";

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()){
            developers.add(developerCRUD.read(resultSet.getInt(1)));
        }
        return developers;
    }

    public List<Developer> getDevelopersWithSkillLevel(Skill skill) throws SQLException {
        statement = connection.createStatement();
        List<Developer> developers = new ArrayList<>();
        Set<Integer> tempSet = new LinkedHashSet();         //used to remove duplicates
        String sqlQuery = "SELECT developers.id\n" +
                "FROM ((skillset\n" +
                "INNER JOIN developers ON developers.id = skillset.developer_id)\n" +
                "INNER JOIN skills ON skills.id = skillset.skill_id)\n" +
                "WHERE skills.level = '" + skill.getLevel() + "'";

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()){
            tempSet.add(resultSet.getInt(1));
        }
        for (Integer id : tempSet){
            developers.add(developerCRUD.read(id));
        }
        return developers;
    }

    public List<CustomProject> getCustomProjectList() throws SQLException {
        List<CustomProject> projects = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("select id from projects");
        while (resultSet.next()){
            CustomProject project = new CustomProject();
            Project currentProject = projectCRUD.read(resultSet.getInt(1));
            project.setName(currentProject.getName());
            project.setDevelopersCount(getDevelopersOnProject(currentProject).size());
            projects.add(project);
        }
        return projects;
    }
    public CompanyCRUD getCompanyCRUD() {
        return companyCRUD;
    }

    public CustomerCRUD getCustomerCRUD() {
        return customerCRUD;
    }

    public DeveloperCRUD getDeveloperCRUD() {
        return developerCRUD;
    }

    public ProjectCRUD getProjectCRUD() {
        return projectCRUD;
    }

    public SkillCRUD getSkillCRUD() {
        return skillCRUD;
    }

    public Statement getStatement() {
        return statement;
    }
}
