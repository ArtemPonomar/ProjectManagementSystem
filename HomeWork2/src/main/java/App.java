import entities.Company;
import entities.Project;
import entities.Skill;
import service.DAO;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO();
        Project project = new Project();
        Skill skill = new Skill();
        project.setName("project1");
        skill.setType("Java");
        skill.setLevel("Middle");
        //task1 test
        //dao.getTotalDeveloperSalaryOfProject(project);
        //task2 test
        //System.out.println(dao.getDevelopersOnProject(project));
        //task3 test
        //System.out.println(dao.getDevelopersWithSkillType(skill));
        //task4 test
        //System.out.println(dao.getDevelopersWithSkillLevel(skill));
        //task5 test
        System.out.println(dao.getCustomProjectList());
    }
}
