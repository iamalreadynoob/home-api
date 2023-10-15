package apiInterface;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/prayer")
public class PrayerTimes
{

    //USE ALADHAN API
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToday()
    {
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTomorrow()
    {
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeLeft()
    {
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonth()
    {
        return null;
    }

}
