package apiInterface;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import objects.Status;
import objects.Task;

import java.util.ArrayList;
import java.util.List;

@Path("/todo")
public class ToDo
{

    private List<Task> tasks = new ArrayList<>();
    private List<Status> statuses = new ArrayList<>();

    @PostConstruct
    public void init()
    {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks() {return Response.ok(tasks).build();}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(String name)
    {
        Task task = null;

        for (Task t: tasks)
        {
            if (t.getName().equals(name))
            {
                task = t;
                break;
            }
        }

        if (task != null) return Response.status(Response.Status.BAD_REQUEST).build();
        else return Response.ok(task).build();
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {return Response.ok(statuses).build();}

}
