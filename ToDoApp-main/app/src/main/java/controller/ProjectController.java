/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author fgabr
 */
public class ProjectController {
    
    public void save(Project project){
    
        String sql = "INSERT INTO projects(name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) "
                + "VALUES (?, ?, ?, ?)";   
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //conex�o com DB
            connection = ConnectionFactory.getConnection();
            //executa a query
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
            statement.setDate(4, new Date (project.getUpdatedAt().getTime()));
            
            //Executa a sql pela inser��o de dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while saving project", ex);
            
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
     public void update(Project project){
    
        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";   
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //conex�o com DB
            connection = ConnectionFactory.getConnection();
            //executa a query
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
            statement.setDate(4, new Date (project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            //Executa a sql pela inser��o de dados
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Error while updating project", ex);
            
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
     
     public void removeById (int idProject) {
         
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            statement.execute();
        } catch (SQLException ex){
            throw new RuntimeException("Error while deleting project", ex);
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }       
    }
     
     public List<Project> getAll (){
        
        String sql = "SELECT * FROM projects";
              
        //Listas de tarefas que ser� devolvida quando a chamada de m�todo acontecer
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            //cria��o da conex�o
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
           
            //Valor retornado pela execu��o da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos no resultSet
            while(resultSet.next()){
                
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while inserting project", ex);
        }finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }   
        return projects;
    }
}
