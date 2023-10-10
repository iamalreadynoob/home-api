package tools;

import fileWorks.SAVF;
import fileWorks.TextCommunication;
import objects.Lecture;
import objects.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Update
{

    public static void lectures(List<Lecture> lectures)
    {
        SAVF savf = new SAVF("config.savf");
        savf.scan();
        String path = savf.getValue("schedule-path");

        ArrayList<String> lines = new ArrayList<>();
        for (Lecture l: lectures)
        {
            String line = l.getCode() + ";" + l.getName() + ";" + l.getDay() + ";" + l.getFrom() + ";" + l.getTo();
            lines.add(line);
        }

        TextCommunication.write(path, lines);
    }

    public static void tasks(char mode, Object input)
    {
        switch (mode)
        {
            case 'a' -> SQLHandling.addTaskToDB((Task) input);
            case 'e' ->
            {
                String[] info = (String[]) input;
                SQLHandling.editTaskInsideDB(info[0], info[1], info[2]);
            }
            case 'd' -> SQLHandling.deleteTaskInsideDB((String) input);
        }
    }



}
