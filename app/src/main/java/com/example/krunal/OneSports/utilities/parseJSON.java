package com.example.krunal.OneSports.utilities;

import android.content.Context;
import android.util.Log;

import com.example.krunal.OneSports.model.NBAData;
import com.example.krunal.OneSports.model.ScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class parseJSON {

    private  static  String TAG="parseJSON";

    public static ArrayList<NBAData> parseJsonData(Context context, String json)throws JSONException{
        ArrayList<NBAData> result = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(json);
//JSONArray gameScore = mainResponse.getJSONArray("gameScore");
        JSONObject scoreboard = mainResponse.getJSONObject("scoreboard");
        JSONArray gameScore = scoreboard.getJSONArray("gameScore");
        Log.d("gamescore", gameScore.toString());

        Log.d(TAG,"Before for loop");



        for(int i=0; i<gameScore.length(); i++){
            JSONObject gameObject = gameScore.getJSONObject(i); //contains data under first {
            JSONObject game = gameObject.getJSONObject("game");
            String id = game.getString("ID");
            JSONObject awayTeam = game.getJSONObject("awayTeam");
            JSONObject homeTeam = game.getJSONObject("homeTeam");
            Log.d(TAG ,"id is:"+ id);
            String homeTeamName = homeTeam.getString("Name");
            String awayTeamName = awayTeam.getString("Name");
            String homeTeamCity = homeTeam.getString("City");
            String awayTeamCity = awayTeam.getString("City");
            String homeScore = gameObject.getString("homeScore");
            String awayScore = gameObject.getString("awayScore");
            String location = game.getString("location");
            String gameDate = game.getString("date");
            NBAData items = new NBAData(homeTeamName, awayTeamName, homeTeamCity, awayTeamCity, homeScore, awayScore, location, gameDate);
            result.add(items);
            Log.v("data is:", items.getHomeTeam()+items.getAwayScore()+items.getHomeTeamCity()+items.getAwayTeamCity()+items.getHomeScore()+items.getAwayScore()+items.getLocation());
        }

        return result;
    }

    public static ArrayList<NBAData> parseJsonDataNfl(Context context, String json)throws JSONException{
        ArrayList<NBAData> result = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(json);
//JSONArray gameScore = mainResponse.getJSONArray("gameScore");
        JSONObject scoreboard = mainResponse.getJSONObject("scoreboard");
        JSONArray gameScore = scoreboard.getJSONArray("gameScore");
        Log.d("gamescore", gameScore.toString());
// Log.d(TAG, "****NFL gamescore length: ", ""+gameScore.length());



        for(int i=0; i<gameScore.length(); i++){
            JSONObject gameObject = gameScore.getJSONObject(i); //contains data under first {
            JSONObject game = gameObject.getJSONObject("game");
            String id = game.getString("ID");
            JSONObject awayTeam = game.getJSONObject("awayTeam");
            JSONObject homeTeam = game.getJSONObject("homeTeam");
            Log.d(TAG ,"id is:"+ id);
            String homeTeamName = homeTeam.getString("Name");
            String awayTeamName = awayTeam.getString("Name");
            String homeTeamCity = homeTeam.getString("City");
            String awayTeamCity = awayTeam.getString("City");
            String homeScore = gameObject.getString("homeScore");
            String awayScore = gameObject.getString("awayScore");
            String location = game.getString("location");
            String gameDate = game.getString("date");
            NBAData items = new NBAData(homeTeamName, awayTeamName, homeTeamCity, awayTeamCity, homeScore, awayScore, location, gameDate);
            result.add(items);
            Log.v("data is:", items.getHomeTeam()+items.getAwayScore()+items.getHomeTeamCity()+items.getAwayTeamCity()+items.getHomeScore()+items.getAwayScore()+items.getLocation());
        }

        return result;
    }

    public static ArrayList<ScheduleModel> parseNBAScheduleJSON (String scheduleResponse) throws JSONException {
        ArrayList<ScheduleModel> parseSchedule = new ArrayList<>();
        JSONObject mainResponse = new JSONObject(scheduleResponse);
        JSONObject fullGameSchedule = mainResponse.getJSONObject("fullgameschedule");
        JSONArray gameEntry = fullGameSchedule.getJSONArray("gameentry");
        Log.d(TAG, "****BEFORE FOR LOOP*****");
        for(int i = 0;i<gameEntry.length();i++){
            JSONObject entryObject = gameEntry.getJSONObject(i);
            String gameDate = entryObject.getString("date");
            Log.d(TAG, "$$$$$$$$$GAME DATE: "+gameDate);
            JSONObject awayTeam = entryObject.getJSONObject("awayTeam");
            String awayTeamName = awayTeam.getString("Name");
            JSONObject homeTeam = entryObject.getJSONObject("homeTeam");
            String homeTeamName = homeTeam.getString("Name");
            String gameLocation = entryObject.getString("location");
            String gameTime = entryObject.getString("time");
            //public ScheduleModel(String gameName, String homeTeam, String awayTeam, String gameLocation, String gameDate) {
            ScheduleModel scheduleModel = new ScheduleModel("nba", homeTeamName, awayTeamName, gameLocation, gameDate, gameTime);
            parseSchedule.add(scheduleModel);
            Log.d(TAG, "$$$$$$$$$DATA: "+awayTeamName);
            Log.d(TAG, "$$$$$$$$$DATA: "+homeTeamName);
            Log.d(TAG, "$$$$$$$$$DATA: "+gameLocation);
        }
        return parseSchedule;
    }




}
