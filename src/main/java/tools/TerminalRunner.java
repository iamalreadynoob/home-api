package tools;

import java.io.IOException;
import java.util.ArrayList;

public class TerminalRunner
{

    public static String getException(Exceptions ex)
    {
        switch (ex)
        {
            case INVALID_COMMAND: return "INVALID COMMAND: arguments are missing or more than expected";
            case NO_SUCH_COMMAND: return "NO SUCH COMMAND: there is no argument";
            case NO_SUCH_INIT_ARG: return "NO SUCH INIT ARGUMENT: there is not a defined first argument like that";
            case NULL: return "NULL: execution went wrong somehow";
            case UNSUPPORTED_APP_RUNTIME: return "UNSUPPORTED APP RUNTIME: the requested runtime is not possible for this application";
            case NO_SUCH_THING: return "NO SUCH THING: there is nothing a file at pointed location";
        }

        return "NON-DEFINED ERROR";
    }

    public static String open(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);



        return output;
    }

    public static String close(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);



        return output;
    }

    public static String kill(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);



        return output;
    }

    public static String help(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);

        return output;
    }

    public static String exec(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);

        if (args.size() == 2)
        {
            if (args.get(1).endsWith(".exe"))
            {
                if (ServerComm.isExist(args.get(1)))
                {
                    ProcessBuilder builder = new ProcessBuilder(args.get(1));

                    try
                    {
                        builder.start();
                        output = args.get(1) + " located exe file was started to execute.";
                    }
                    catch (IOException e) {output = e.getMessage();}


                }
                else output = getException(Exceptions.NO_SUCH_THING);
            }
            else if (args.get(1).endsWith(".jar"))
            {
                if (ServerComm.isExist(args.get(1)))
                {
                    ProcessBuilder builder = new ProcessBuilder("java", "-jar", args.get(1));

                    try
                    {
                        builder.start();
                        output = args.get(1) + " located jar file was started to execute.";
                    }
                    catch (IOException e) {output = e.getMessage();}


                }
                else output = getException(Exceptions.NO_SUCH_THING);
            }
            else output = getException(Exceptions.UNSUPPORTED_APP_RUNTIME);
        }
        else output = getException(Exceptions.INVALID_COMMAND);

        return output;
    }

    public static String list(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);



        return output;
    }

    public static String bubble(ArrayList<String> args)
    {
        String output = getException(Exceptions.NULL);



        return output;
    }

    public enum Exceptions
    {
        INVALID_COMMAND, NO_SUCH_COMMAND, NO_SUCH_INIT_ARG, NULL, UNSUPPORTED_APP_RUNTIME, NO_SUCH_THING
    }

}
