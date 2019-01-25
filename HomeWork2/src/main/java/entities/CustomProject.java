package entities;

//Object used in task5
public class CustomProject {
    private String name;
    private int developersCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDevelopersCount() {
        return developersCount;
    }

    public void setDevelopersCount(int developersCount) {
        this.developersCount = developersCount;
    }

    @Override
    public String toString() {
        return "\nProject " +
                "name = '" + name + '\'' +
                ", developersCount = " + developersCount + "\n";
    }
}
