import DBConnector.Database;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class UserLogic
{
    public  ArrayList<User> getAllUsers()
    {
        User tmpUser = null;
        ArrayList<User> userList = new ArrayList<>();
        try
        {
            Connection connection = Database.connection();
            String sql  = " SELECT * FROM usertable";

            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery(sql);
                if(rs.next())
                {
                    String firstName = rs.getString("fname");
                    String lastName = rs.getString("lname");
                    String password = rs.getString("pw");
                    String phoneNumber = rs.getString("phone");
                    String adress = rs.getString("address");

                    tmpUser = new User(firstName,lastName,password,phoneNumber,adress);
                    userList.add(tmpUser);
                }
                return userList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String printAllUsersFistNames()
    {
        ArrayList<User> userList = getAllUsers();
        StringBuilder users = new StringBuilder();

        userList.stream().forEach((e) -> users.append(e.getFirstName()));

        return users.toString();
    }

    public User getUserByName(String name)
    {
            User tmpUser = null;
            try
            {
                Connection connection = Database.connection();
                String sql = " SELECT * FROM usertable WHERE fname='"+name+"'";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery(sql);
                    if (rs.next())
                    {
                        String firstName = rs.getString("fname");
                        String lastName = rs.getString("lname");
                        String password = rs.getString("pw");
                        String phoneNumber = rs.getString("phone");
                        String address = rs.getString("address");

                        tmpUser = new User(firstName,lastName,password,phoneNumber,address);
                    }

                } catch (SQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return tmpUser;
    }

    public void editUserDetails(User user)
    {
        try
        {
            Connection connection = Database.connection();
            String sql = "UPDATE usertable SET fname=?, lname=?, pw=?, phone=?, address=?";
            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getPhoneNumber());
                ps.setString(5, user.getAdress());
                ps.executeUpdate();
            }

        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
