package service;

import entities.CustomProject;
import entities.Developer;
import entities.Project;
import entities.Skill;
import org.hibernate.Session;
import util.HibernateFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {
    private DAOHibernate<Developer> developerDAO = new DAOHibernate<>(Developer.class);
    private DAOHibernate<Project> projectDAO = new DAOHibernate<>(Project.class);

    public Object findBy(String fieldName, String value){
        try(Session session = HibernateFactory.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Project where " + fieldName + "  = '" + value + "'")
                    .getResultStream().findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalDevelopersSalaryOfProject(Project project){
        int totalSalary = 0;
        for (Developer developer : project.getDevsOnProject()){
            totalSalary += developer.getSalary();
        }
        return totalSalary;
    }

    public Set<Developer> getDevelopersWithSkillType (Skill skill){
        Set<Developer> resultSet = new HashSet<>();
        for(Developer dev : developerDAO.getAll()){
            for (Skill s : dev.getSkillsOfDeveloper()){
                if (s.getType().equalsIgnoreCase(skill.getType())){
                    resultSet.add(dev);
                }
            }
        }
        return resultSet;
    }

    public Set<Developer> getDevelopersWithSkillLevel (Skill skill){
        Set<Developer> resultSet = new HashSet<>();
        System.out.println("Checking for skill type = " + skill.getLevel());
        for(Developer dev : developerDAO.getAll()){
            for (Skill s : dev.getSkillsOfDeveloper()){
                System.out.println("==================== skill level = " + s.getLevel());
                if (s.getLevel().equalsIgnoreCase(skill.getLevel())){
                    resultSet.add(dev);
                }
            }
        }
        return resultSet;
    }

    public List<CustomProject> getCustomProjectList(){
        List<CustomProject> customProjects = new ArrayList<>();

        for (Project project: projectDAO.getAll()){
            CustomProject customProject = new CustomProject();
            customProject.setName(project.getName());
            customProject.setDevelopersCount(project.getDevsOnProject().size());
            customProjects.add(customProject);
        }

        return customProjects;
    }
}
