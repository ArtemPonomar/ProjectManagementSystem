package entities;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table(name = "projects")
public class Project {
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Project.class);
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projectsForDeveloper")
    private java.util.Set<Developer> devsOnProject = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;



    public java.util.Set<Developer> getDevsOnProject() {
        return devsOnProject;
    }

    public void setDevsOnProject(java.util.Set<Developer> devsOnProject) {
        this.devsOnProject = devsOnProject;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
