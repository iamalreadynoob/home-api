package apiInterface;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import objects.Grade;

import java.util.ArrayList;
import java.util.List;

@Path("/grades")
public class Grades
{
    List<Grade> grades = new ArrayList<>();

    @PostConstruct
    public void init()
    {
        grades.clear();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrades() {return Response.ok(grades).build();}

    @GET
    @Path("{name}")
    public Response getGrade(@PathParam("name") String name)
    {
        for (Grade g: grades)
        {
            if (g.getName().equals(name))
                return Response.ok(g).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response changeStatus()
    {
        return null;
    }

    public Response uploadMidterm()
    {
        return null;
    }

    public Response changeMidterm()
    {
        return null;
    }

    public Response uploadFinal()
    {
        return null;
    }

    public Response changeFinal()
    {
        return null;
    }

    public Response uploadProjects()
    {
        return null;
    }

    public Response changeProjects()
    {
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade()
    {
        return null;
    }

    public Response deleteGrade()
    {
        return null;
    }

    public Response resetGrade()
    {
        return null;
    }

}
