package com.example.krunal.OneSports.utilities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class NetworkUtils {

    final static String TAG = "NetworkUtills";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getResponseFromHttpUrl()  {
        String data = " ";
        StringBuilder builder = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        dateFormat.format(cal.getTime());
        String lastDayDate2 = ""+dateFormat.format(cal.getTime());
        lastDayDate2 = lastDayDate2.replaceAll("-","");
        //This is because the API wasn't giving us current data, for live score, please check LiveScoreDemo Activity
        lastDayDate2 = "20170424";
        //Log.d(TAG, "DATE DEBUG NBA-------->>>>>: "+lastDayDate2);
        try {

            URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nba/2017-playoff/scoreboard.json?fordate=" + lastDayDate2);
            String testValue = "krunal7017:Onesports@1";
            Log.d(TAG,"getting data ");
            byte[] data1=testValue.getBytes(StandardCharsets.UTF_8);
           String encoding=Base64.encodeToString(data1 ,Base64.DEFAULT);
           //String  encoding="Basic " + new String(android.util.Base64.encode(testValue.getBytes(), android.util.Base64.NO_WRAP));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
           // connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic "+ encoding );
            InputStream content = (InputStream)connection.getInputStream();
//            BufferedReader in   =
//                    new BufferedReader (new InputStreamReader(content));
//            String line;
//
//            while ((line = in.readLine()) != null)
//            {
//                builder.append(line);
//
//            }

            Scanner scanner = new Scanner(content);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                data=data+scanner.next();
            } else {
                data=null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Log.d(TAG ,"Exception at NBA in NetworksUtils");

        }
        Log.d(TAG, data);
        return  data;

    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getResponseFromHttpUrlNfl()  {

        String data = " ";
        StringBuilder builder = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        dateFormat.format(cal.getTime());
        String lastDayDate2 = ""+dateFormat.format(cal.getTime());
        lastDayDate2 = lastDayDate2.replaceAll("-","");
        //This is because the API wasn't giving us current data, for live score, please check LiveScoreDemo Activity
        lastDayDate2 = "20171022";
        Log.d(TAG, "DATE DEBUG NFL-------->>>>>: "+lastDayDate2);


        try {

            URL url = new URL("https://api.mysportsfeeds.com/v1.2/pull/nfl/2017-regular/scoreboard.json?fordate=" + lastDayDate2);
            String testValue = "krunal7017:Onesports@1";
            Log.d(TAG,"getting data in NFL ");
            byte[] data1=testValue.getBytes(StandardCharsets.UTF_8);
            String encoding=Base64.encodeToString(data1 ,Base64.DEFAULT);
            //String  encoding="Basic " + new String(android.util.Base64.encode(testValue.getBytes(), android.util.Base64.NO_WRAP));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            // connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic "+ encoding );
            InputStream content = (InputStream)connection.getInputStream();
//            BufferedReader in   =
//                    new BufferedReader (new InputStreamReader(content));
//            String line;
//
//            while ((line = in.readLine()) != null)
//            {
//                builder.append(line);
//
//            }

            Scanner scanner = new Scanner(content);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                data=data+scanner.next();
            } else {
                data=null;
            }
        }


        catch(Exception e)
        {
            e.printStackTrace();
            Log.d(TAG ,"Exception at NFL in NetworksUtils");

        }
        Log.d(TAG, data);
        return  data;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getScheduleNBA(URL url){
        String data = "";
        try{
            //url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nba/2017-playoff/full_game_schedule.json");
            String testValue = "krunal7017:Onesports@1";
            byte[] data1=testValue.getBytes(StandardCharsets.UTF_8);
            String encoding=Base64.encodeToString(data1 ,Base64.DEFAULT);
            Log.d(TAG, "^^^INSIDE TRY");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setRequestProperty("Authorization", "Basic "+ encoding );
            InputStream content = (InputStream)connection.getInputStream();
            Scanner scanner = new Scanner(content);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                data=data+scanner.next();
            } else {
                data=null;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG ,"Exception in schedule for NBA");
        }

        return data;
    }
}
