package org.example;

import java.sql.*;

public class SqlCn {

    private static SqlCn instance;
    private final Connection connection;


    public SqlCn(Connection connection) {
        this.connection=connection;
    }

    public static SqlCn getconnection(Connection connection){
            if(instance==null){
                instance=new SqlCn(connection);
           }
            return instance;
    }
    public ResultSet getUser(String name){
        Statement statment;
        try {
            statment = connection.createStatement();
            return statment.executeQuery("select * from chat where username='"+name+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addUser(User user){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("insert into chat(username,password,message) values (?,?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getmessage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUser(User user){
        Statement statment;
        try {
            statment = connection.createStatement();
            ResultSet r=statment.executeQuery("select * from chat where username='"+user.getName()+"' and password='"+user.getPassword()+"'");
            if(r.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUserByUsername(User user){
        Statement statment;
        try {
            statment = connection.createStatement();
            ResultSet r=statment.executeQuery("select * from chat where username='"+user.getName()+"'");
            if(r.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateMessages(String name,String message){
        Statement statment;
        try {
            statment = connection.createStatement();
            statment.executeUpdate("update chat set message='"+message+"'where username='"+name+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
