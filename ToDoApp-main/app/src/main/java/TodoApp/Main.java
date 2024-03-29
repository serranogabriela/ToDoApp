/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TodoApp;

import controller.ProjectController;
import java.util.List;
import model.Project;

public class Main {
    

    public static void main(String[] args) {
        
        ProjectController projectController = new ProjectController();
        
//        Project project = new Project();
//        project.setName("Projeto Teste");
//        project.setDescription("description");
//        projectController.save(project);
        
        Project project = new Project();
        project.setId(7);
        project.setName("new Project Name");
        project.setDescription("description");
        
        projectController.update(project);
        
        List<Project> projects = projectController.getAll();
        System.out.println("My Projects - " + projects.size());
        
        projectController.removeById(7);
        
    }
}
