package entities;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "developers")
public class Developer {
    @ManyToMany
    @JoinTable(
            name = "project_allocation",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private java.util.Set<Project> projectsForDeveloper = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "skillset",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private java.util.Set<Skill> skillsOfDeveloper = new HashSet<>();

    public java.util.Set<Skill> getSkillsOfDeveloper() {
        return skillsOfDeveloper;
    }

    public void addSkillOfDeveloper(Skill skillOfDeveloper) {
        this.skillsOfDeveloper.add(skillOfDeveloper);
    }

    public java.util.Set<Project> getProjectsForDeveloper() {
        return projectsForDeveloper;
    }

    public void addProjectForDeveloper(Project projectForDeveloper) {
        this.projectsForDeveloper.add(projectForDeveloper);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sername")
    private String sername;
    @Column(name = "salary")
    private int salary;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\nDeveloper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sername='" + sername + '\'' +
                ", salary=" + salary +
                '}';
    }
}
