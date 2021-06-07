package resources;

import ch.qos.logback.core.status.Status;
import dtos.TeamDTO;
import service.DataInstanceClassifier;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/j48")
public class ClassifierResource {

    private final DataInstanceClassifier classifier = new DataInstanceClassifier();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/classify")
    public Response classifyTeamInstance(TeamDTO teamDTO) {
        Boolean result = classifier.getTeamClassification(teamDTO);
        if (result == null) {
            return Response.status(Status.ERROR).build();
        }
        return Response.ok(result).build();
    }
}
