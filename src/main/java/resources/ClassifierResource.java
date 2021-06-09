package resources;

import ch.qos.logback.core.status.Status;
import dtos.TeamDTO;
import service.DataInstanceClassifier;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weka")
public class ClassifierResource {

    private final DataInstanceClassifier classifier = new DataInstanceClassifier();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/j48/classify")
    public Response classifyTeamInstance(TeamDTO teamDTO) {
        Boolean result = classifier.getTeamClassification(teamDTO);
        if (result == null) {
            return Response.status(Status.ERROR).build();
        }
        return Response.ok(result).build();
    }
}
