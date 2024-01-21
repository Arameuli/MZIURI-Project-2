package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private SqlConnector sqlConnector;
    private SqlCn sqlCn;

    @Override
    public void init() throws ServletException {
        sqlConnector = new SqlConnector();
        Connection connection = sqlConnector.getConnection();
        sqlCn = SqlCn.getconnection(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("user");
        String message = req.getParameter("message");
        String messages = "";
        ResultSet r = sqlCn.getUser(name);
        while (true) {
            try {
                if (!r.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                messages = r.getString("message");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            messages += message + "\n";
        }
        if (!message.contains("\n")) {
            User user = new User(name, "", message);
            if (sqlCn.checkUserByUsername(user)) {
                sqlCn.updateMessages(name, messages);
                resp.setStatus(200);
            } else {
                resp.setStatus(403);
            }
        } else {
            resp.setStatus(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name=req.getParameter("username");
        String password=req.getParameter("password");
        User user=new User(name,password,"");
        if(sqlCn.checkUser(user)){
            String messages="";
            ResultSet r=sqlCn.getUser(name);
            while (true){
                try {
                    if (!r.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    messages=r.getString("message");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            resp.getWriter().write(messages);
            resp.setStatus(200);
        }else{

            resp.setStatus(403);
        }
    }
}

