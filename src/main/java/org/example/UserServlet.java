package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/register")
public class UserServlet extends HttpServlet {
    private SqlConnector sqlConnector;
    private SqlCn sqlCn;
    @Override
    public void init() throws ServletException {
        sqlConnector=new SqlConnector();
        Connection connection=sqlConnector.getConnection();
        sqlCn= SqlCn.getconnection(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        if(name.equals("") || password.equals("")){
            resp.setStatus(403);
        }else{
            User user=new User(name,password,"");
            if(sqlCn.checkUserByUsername(user)){
                resp.setStatus(403);
            }else{
                sqlCn.addUser(user);
                resp.setStatus(200);
            }
        }
    }
}
