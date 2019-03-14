import entities.Project;
import entities.Skill;
import service.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static Controller controller = new Controller();

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("1. Get salary of all the programmers of project 'X'");
            System.out.println("2. Get list of programmers working on project 'X'");
            System.out.println("3. Get list of all programmers that use language 'X'");
            System.out.println("4. Get list of all programmers with skill level 'X'");
            System.out.println("5. Get list of all projects");

            for (String input = "E"; !input.equals("back");){
                System.out.println("\nEnter 'back' to return to main menu or enter task number.");
                input = scanner.next();
                switch (input) {
                    case "1": executeTask1(scanner);
                              break;
                    case "2": executeTask2(scanner);
                              break;
                    case "3": executeTask3(scanner);
                              break;
                    case "4": executeTask4(scanner);
                              break;
                    case "5": executeTask5(scanner);
                              break;
                    case "back": break;
                    default:  System.out.println("Invalid input.");
                              break;
                }
            }
            System.out.println("Want to continue? (yes/no)");
        } while (!scanner.next().equals("no"));
    }

    private static void executeTask1(Scanner scanner) throws SQLException {
        System.out.println("1. Get salary of all the programmers of project 'X'");
        System.out.println("Enter the name of project: (project1, project2, project3, project4, project5)");
        Project project = (Project) controller.findBy("name", scanner.next());
        System.out.print("Result = ");
        System.out.println(controller.getTotalDevelopersSalaryOfProject(project));
    }
    private static void executeTask2(Scanner scanner) throws SQLException {
        System.out.println("2. Get list of programmers working on project 'X'");
        System.out.println("Enter the name of project: (project1, project2, project3, project4, project5)");
        Project project = (Project) controller.findBy("name", scanner.next());
        System.out.println(project.getDevsOnProject());
    }
    private static void executeTask3(Scanner scanner) throws SQLException {
        System.out.println("3. Get list of all programmers that use language 'X'");
        System.out.println("Enter the name of language: (Java, C++, C#, JS)");
        Skill skill = new Skill();
        skill.setType(scanner.next());
        System.out.println(controller.getDevelopersWithSkillType(skill));
    }
    private static void executeTask4(Scanner scanner) throws SQLException {
        System.out.println("4. Get list of all programmers with skill level 'X'");
        System.out.println("Enter the level of skill: (junior, middle, senior)");
        Skill skill = new Skill();
        skill.setLevel(scanner.next());
        System.out.println(controller.getDevelopersWithSkillLevel(skill));
    }
    private static void executeTask5(Scanner scanner) throws SQLException {
        System.out.println("5. Get list of all projects");
        System.out.println(controller.getCustomProjectList());
    }
}
