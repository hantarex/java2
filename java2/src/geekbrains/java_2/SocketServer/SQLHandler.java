package geekbrains.java_2.SocketServer;

import java.sql.*;

public class SQLHandler {
    Connection c = null;

    public void connect() {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            c = DriverManager.getConnection("jdbc:sqlite:lesson2.sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNick(String login, String pass){
        PreparedStatement p=null;
        String nickname=null;
        String sql="select Nickname from Main where Login = ? and Password = ?";
        try {
            p=c.prepareStatement(sql);
            p.setString(1,login);
            p.setString(2,pass);
            ResultSet result=p.executeQuery();
            while (result.next()){
                nickname=result.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nickname;
    }

    public boolean changeNick(String oldnick, String newnick){
        PreparedStatement p=null;
        String sql="update Main set Nickname = ? where Nickname = ?";
        try {
            p=c.prepareStatement(sql);
            p.setString(1,newnick);
            p.setString(2,oldnick);
            int result=p.executeUpdate();
            return (result>0)?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void disconnect() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
