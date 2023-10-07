package apiInterface;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import objects.Lecture;
import tools.Update;
import tools.Upload;

import java.util.ArrayList;
import java.util.List;

@Path("/schedule")
public class SchoolSchedule
{

    private List<Lecture> lectures;

    @PostConstruct
    public void init()
    {
        lectures.clear();
        lectures = Upload.lectures();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLectures() {return Response.ok(lectures).build();}

    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLecture(@PathParam("code") String code)
    {
        Lecture response = null;

        for (Lecture l: lectures)
        {
            if (l.getCode().equals(code))
            {
                response = l;
                break;
            }
        }

        return Response.ok(response).build();
    }

    @GET
    @Path("{day}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDay(@PathParam("day") String day)
    {
        List<Lecture> dayLec = new ArrayList<>();

        for (Lecture l: lectures)
            if (l.getDay().equals(day))
                dayLec.add(l);

        return Response.ok(dayLec).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLecture(Lecture lecture)
    {
        lectures.add(lecture);
        Update.lectures(lectures);
        return Response.ok(lectures).build();
    }

    @PUT
    @Path("{code}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("code") String code,
                         @PathParam("type") String type,
                         @QueryParam("value") String value)
    {
        if (type.equals("name") || type.equals("code") || type.equals("day") || type.equals("from") || type.equals("to"))
        {
            for (Lecture l: lectures)
            {
                if (l.getCode().equals(code))
                {
                    if (type.equals("name")) l.setName(value);
                    else if (type.equals("code")) l.setCode(value);
                    else if (type.equals("day")) l.setDay(value);
                    else if (type.equals("from")) l.setFrom(value);
                    else if (type.equals("to")) l.setTo(value);

                    Update.lectures(lectures);

                    break;
                }
            }
        }

        return Response.ok(lectures).build();
    }

    @DELETE
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("code") String code)
    {
        boolean flag = false;

        for (int i = 0; i < lectures.size(); i++)
        {
            if (lectures.get(i).getCode().equals(code))
            {
                lectures.remove(i);
                flag = true;
                break;
            }
        }

        if (flag) return Response.noContent().build();
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
