package tools;

import fileWorks.SAVF;
import fileWorks.TextCommunication;
import objects.Lecture;

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

}
