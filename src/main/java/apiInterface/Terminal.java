package apiInterface;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tools.TerminalRunner;
import tools.Upload;

import java.util.ArrayList;

public class Terminal
{
    boolean inBubble;
    String bubbleLoc;

    @PostConstruct
    public void init()
    {
        inBubble = false;
        bubbleLoc = null;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response runCommand(String command)
    {
        String terminalOutput = "";

        ArrayList<String> args = Upload.parseCommand(command);

        if (!args.isEmpty())
        {
            if (inBubble)
            {

            }
            else
            {
                if (args.get(0).equals("open")) terminalOutput = TerminalRunner.open(args);
                else if (args.get(0).equals("close")) terminalOutput = TerminalRunner.close(args);
                else if (args.get(0).equals("kill")) terminalOutput = TerminalRunner.kill(args);
                else if (args.get(0).equals("help")) terminalOutput = TerminalRunner.help(args);
                else if (args.get(0).equals("exec")) terminalOutput = TerminalRunner.exec(args);
                else if (args.get(0).equals("list")) terminalOutput = TerminalRunner.list(args);
                else  terminalOutput = TerminalRunner.getException(TerminalRunner.Exceptions.NO_SUCH_INIT_ARG);
            }
        }
        else terminalOutput = TerminalRunner.getException(TerminalRunner.Exceptions.NO_SUCH_COMMAND);

        return Response.ok(terminalOutput).build();
    }

}
