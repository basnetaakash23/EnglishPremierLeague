package com.example.aakashbasnet.englishpremierleague;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by aakashbasnet on 2/5/18.
 */

public class Queryutils {
    private static final String LOG_TAG ="Error:" ;

    public static ArrayList<PremierLeague> fetchPremierLeagueData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        //return extractFeatureFromJson(jsonResponse);
        ArrayList<PremierLeague> premierleague = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}

        return premierleague;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        String auth = "fXmiNuglMPmshrhCloR0jVWzXAFLp11VGFEjsnEhZqPZP4oBgS";

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            String basicAuth = "Basic " + new String(Base64.encode(auth.getBytes(), Base64.NO_WRAP));
            urlConnection.setRequestProperty("X-Mashape-Key",auth);
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        Log.d("Output",output.toString());
        return output.toString();
    }

    /**
     * Return an {@link } object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private static ArrayList<PremierLeague> extractFeatureFromJson(String priemierleagueJSON) {
        // If the JSON string is empty or null, then return early.
        ArrayList<PremierLeague> leaguetable =new  ArrayList<PremierLeague>();
        if (TextUtils.isEmpty(priemierleagueJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(priemierleagueJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("records");

            // If there are results in the features array
            if (featureArray.length() > 0) {
                int i =0;
                while(i<featureArray.length()) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject properties = featureArray.getJSONObject(i);


                    // Extract out the title, number of people, and perceived strength values
                    String team  = properties.getString("team");
                    String played = properties.getString("played");
                    int played_= Integer.parseInt(played);
                    String win = properties.getString("win");
                    int win_ = Integer.parseInt(win);
                    String loss = properties.getString("loss");
                    int loss_ = Integer.parseInt(loss);
                    String points = properties.getString("points");
                    int points_ = Integer.parseInt(points);

                    // Create a new {@link Event} object
                    leaguetable.add(new PremierLeague(team, played_, win_, loss_,points_));
                    i = i+1;

                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return leaguetable;
    }


}
