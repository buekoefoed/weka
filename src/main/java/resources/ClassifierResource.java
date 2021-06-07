package resources;

import dtos.TeamDTO;
import service.DataInstanceClassifier;

public class ClassifierResource {

    private final DataInstanceClassifier classifier = new DataInstanceClassifier();

    public Boolean classifyTeamInstance(TeamDTO teamDTO) {
        return classifier.getTeamClassification(teamDTO);
    }
}
