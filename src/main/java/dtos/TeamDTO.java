package dtos;

public class TeamDTO {
    private final int avgCommunicationScore;
    private final int avgExperienceScore;
    private final String hasVettedLeader;
    private final int teamSynergyScore;

    public TeamDTO(int avgCommunicationScore, int avgExperienceScore, String hasVettedLeader, int teamSynergyScore) {
        this.avgCommunicationScore = avgCommunicationScore;
        this.avgExperienceScore = avgExperienceScore;
        this.hasVettedLeader = hasVettedLeader;
        this.teamSynergyScore = teamSynergyScore;
    }

    public int getAvgCommunicationScore() {
        return avgCommunicationScore;
    }

    public int getAvgExperienceScore() {
        return avgExperienceScore;
    }

    public String getHasVettedLeader() {
        return hasVettedLeader;
    }

    public int getTeamSynergyScore() {
        return teamSynergyScore;
    }
}
