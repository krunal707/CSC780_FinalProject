package com.example.krunal.OneSports.utilities;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.krunal.OneSports.NbaSchedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;



public class NflScheduleParser extends AsyncTask<Void, Void, Void> {

    String dataParsed = "";
    String singleParsed = "";
    String query = "teamstandingsentry";
    private  static  String TAG="nflStandingsParse";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://api.mysportsfeeds.com/v1.2/pull/nfl/2017-regular/overall_team_standings.json?teamstats=W,L,T,PF,PA");
            HttpConnection connection = new HttpConnection();
            String datatoParse = connection.parseInputData(url, query);

            JSONArray JA = new JSONArray(datatoParse);
            for(int i=0; i<JA.length(); i++){
                JSONObject standingsObject = (JSONObject) JA.get(i);
                JSONObject team = standingsObject.getJSONObject("team");
                singleParsed =  "Team Name:\t\t\t\t\t\t" + team.getString("Name") + "\n" +
                        "Team Rank:\t\t\t\t\t\t\t" + standingsObject.getString("rank") + "\n\n";
                dataParsed = dataParsed + singleParsed;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        NbaSchedule.schedule_data1.setText(dataParsed);
    }
}