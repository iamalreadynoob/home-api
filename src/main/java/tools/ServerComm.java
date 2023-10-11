package tools;

import java.io.File;
import java.io.IOException;

public class ServerComm
{

    protected static boolean isExist(String path)
    {
        boolean flag = false;

        File file = new File(path);
        flag = file.exists();

        return flag;
    }

}
