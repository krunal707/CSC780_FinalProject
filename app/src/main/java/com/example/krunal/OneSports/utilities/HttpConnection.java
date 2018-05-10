package com.example.krunal.OneSports.utilities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by richa on 18-04-2018.
 */

public class HttpConnection {

    String data = "";
//    String datatoParse = "";
    private static BufferedReader bufferedReader;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setHttpConnection(URL url) throws IOException {
        String testValue = "krunal7017:Onesports@1";
        byte[] data1=testValue.getBytes(StandardCharsets.UTF_8);
        String encoding= Base64.encodeToString(data1 ,Base64.DEFAULT);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setRequestProperty("Authorization", "Basic "+ encoding );
        InputStream inputStream = (InputStream)connection.getInputStream();

        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String parseInputData(URL url, String query) throws IOException {
        String line = "";
        String datatoParse = "";
        setHttpConnection(url);

        while (line != null){
            line = bufferedReader.readLine();
            data = data + line;
            if (data.contains(query)){
                int index = data.indexOf(query);
                datatoParse = data.substring(index + query.length() + 2, data.length() - 6);
            }
        }
        return datatoParse;
    }
}
