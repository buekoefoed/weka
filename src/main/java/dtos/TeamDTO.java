package dtos;

public class TeamDTO {
    private int avgCommunicationScore;
    private int avgExperienceScore;
    private boolean hasVettedLeader;
    private int teamSynergyScore;

    public TeamDTO() {
    }

    public TeamDTO(int avgCommunicationScore, int avgExperienceScore, boolean hasVettedLeader, int teamSynergyScore) {
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

    public boolean getHasVettedLeader() {
        return hasVettedLeader;
    }

    public int getTeamSynergyScore() {
        return teamSynergyScore;
    }
}
