package tools;

import fileWorks.SAVF;
import objects.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLHandling
{

    protected static void addTaskToDB(Task task)
    {
        try
        {

            Connection connection = getDatabase();
            String command = "INSERT INTO tasks (name, task, deadline, status, tag, by) (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(command);

            statement.setString(1, task.getName());
            statement.setString(2, task.getTask());
            statement.setDate(3, getDate(task.getDeadline()));
            statement.setString(4, task.getStatus());
            statement.setString(5, task.getTag());
            statement.setString(6, task.getBy());

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (SQLException e) {e.printStackTrace();}
    }

    protected static void editTaskInsideDB(String name, String type, String value)
    {
        try
        {
            Connection connection = getDatabase();
            String command = "UPDATE tasks SET " + type + " = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(command);

            if (type.equals("deadline")) statement.setDate(1, getDate(value));
            else statement.setString(1, value);

            statement.setString(2, name);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (SQLException e) {e.printStackTrace();}
    }

    protected static void deleteTaskInsideDB(String name)
    {
        try
        {
            Connection connection = getDatabase();
            String command = "DELETE FROM tasks WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setString(1, name);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (SQLException e){e.printStackTrace();}
    }

    protected static Connection getDatabase()
    {
        SAVF savf = new SAVF("config.savf");
        savf.scan();
        String username = savf.getValue("db-username");
        String password = savf.getValue("db-password");
        String url = savf.getValue("db-url");

        try {return DriverManager.getConnection(url, username, password);}
        catch (SQLException e) {return null;}
    }

    private static java.sql.Date getDate(String raw)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {date = format.parse(raw);}
        catch (Exception e) {e.printStackTrace();}

        return new java.sql.Date(date.getTime());
    }

}
