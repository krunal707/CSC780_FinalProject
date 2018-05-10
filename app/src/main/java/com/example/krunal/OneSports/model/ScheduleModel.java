package com.example.krunal.OneSports.model;


public class ScheduleModel {
    public String gameName;
    public String homeTeam;
    public String awayTeam;
    public String gameLocation;
    public String gameDate;
    public String gameTime;

    public ScheduleModel(String gameName, String homeTeam, String awayTeam, String gameLocation, String gameDate, String gameTime) {
        this.gameName = gameName;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameLocation = gameLocation;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }
}
