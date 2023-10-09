package tools;

import fileWorks.SAVF;
import fileWorks.TextCommunication;
import objects.Lecture;
import objects.Status;
import objects.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Upload
{

    public static List<Lecture> lectures()
    {
        List<Lecture> lectures = new ArrayList<>();

        SAVF savf = new SAVF("config.savf");
        savf.scan();
        ArrayList<String> lines = TextCommunication.read(savf.getValue("schedule-path"));

        for (String l: lines)
        {
            if (!l.isBlank() && !l.isEmpty())
            {
                String[] pieces = l.split(";");

                if (pieces.length == 5)
                {
                    Lecture lecture = new Lecture(pieces[0], pieces[1], pieces[2], pieces[3], pieces[4]);
                    lectures.add(lecture);
                }
            }
        }

        return lectures;
    }

    public static List<Task> tasks()
    {
        List<Task> tasks = new ArrayList<>();

        Statement statement = null;
        ResultSet set = null;
        Connection connection = null;

        SAVF savf = new SAVF("config.savf");
        savf.scan();
        String username = savf.getValue("db-username");
        String password = savf.getValue("db-password");
        String url = savf.getValue("db-url");

        try
        {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            set = statement.executeQuery("SELECT * FROM tasks");

            while (set.next())
            {
                Task task = new Task(set.getString("name"),
                        set.getString("task"),
                        set.getString("deadline"),
                        Integer.toString(set.getInt("status")),
                        set.getString("tag"),
                        set.getString("by"));

                tasks.add(task);
            }
        }
        catch (SQLException e){e.printStackTrace();}

        return tasks;
    }

    public static List<Status> statuses()
    {
        List<Status> statuses = new ArrayList<>();

        Statement statement = null;
        ResultSet set = null;
        Connection connection = null;

        SAVF savf = new SAVF("config.savf");
        savf.scan();
        String username = savf.getValue("db-username");
        String password = savf.getValue("db-password");
        String url = savf.getValue("db-url");

        try
        {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            set = statement.executeQuery("SELECT * FROM statuses");

            while (set.next())
            {
                Status status = new Status(set.getString("status"),
                        Integer.toString(set.getInt("id")));

                statuses.add(status);
            }
        }
        catch (SQLException e){e.printStackTrace();}

        return statuses;
    }

}
