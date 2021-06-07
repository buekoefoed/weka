package models;

import dtos.TeamDTO;

public class Team {
    private final int avgCommunicationScore;
    private final int avgExperienceScore;
    private final String hasVettedLeader;
    private final int teamSynergyScore;

    public Team(TeamDTO teamDTO) {
        this.avgCommunicationScore = teamDTO.getAvgCommunicationScore();
        this.avgExperienceScore = teamDTO.getAvgExperienceScore();
        if (teamDTO.getHasVettedLeader()) {
            this.hasVettedLeader = "Yes";
        } else {
            this.hasVettedLeader = "No";
        }
        this.teamSynergyScore = teamDTO.getTeamSynergyScore();
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

