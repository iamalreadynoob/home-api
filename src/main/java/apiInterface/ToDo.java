package apiInterface;

import fileWorks.SAVF;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import objects.Status;
import objects.Task;
import tools.Upload;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/todo")
public class ToDo
{

    private List<Task> tasks;
    private List<Status> statuses;

    @PostConstruct
    public void init()
    {
        tasks = Upload.tasks();
        statuses = Upload.statuses();
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(Task task)
    {
        //TODO: check that the name is unique or not
        if (task != null)
        {
            tasks.add(task);
            return Response.ok(tasks).build();

            //TODO: Update database
        }
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{name}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editTask(@PathParam("name") String name, @PathParam("type") String type, @QueryParam("value") String value)
    {
        boolean flag = false;

        if (type.equals("name") || type.equals("task") || type.equals("deadline") || type.equals("status") || type.equals("tag") || type.equals("by"))
        {
            for (Task t: tasks)
            {
                if (t.getName().equals(name))
                {
                    flag = true;

                    if (type.equals("name")) t.setName(value);
                    else if (type.equals("task")) t.setTask(value);
                    else if (type.equals("deadline")) t.setDeadline(value);
                    else if (type.equals("status")) t.setStatus(value);
                    else if (type.equals("tag")) t.setTag(value);
                    else t.setBy(value);

                    break;
                }
            }
        }

        //TODO: Update db

        if (flag) return Response.ok(tasks).build();
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTask(@PathParam("name") String name)
    {
        boolean flag = false;

        for (int i = 0; i < tasks.size(); i++)
        {
            if (tasks.get(i).getName().equals(name))
            {
                tasks.remove(i);
                flag = true;
                break;
            }
        }

        if (flag) return Response.noContent().build();
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
