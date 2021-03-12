package lecture.l01_android_simple_buttons.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by shreyakumar.
 */

public class NetworkUtils {

    // this method is specific to the countries URL
    public static URL buildCountriesUrl(){
        // get string url
        String countryUrlString = "https://api.openaq.org/v1/countries";
        URL countryUrl = null;
        try{
            countryUrl = new URL(countryUrlString);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return countryUrl;
    } // end of buil

    // this method can be used with any URL object
    public static String getResponseFromUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // getting the connection open
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A"); // delimiter for end of message
            boolean hasInput = scanner.hasNext();
            if(hasInput) return scanner.next(); // success
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return null;
    } // end of get Resp

    public static ArrayList<String> parseCountriesJson(String countriesResponseString){
        //String [] countryList = new String[100];
        ArrayList<String> countryList = new ArrayList<String>();
        try{
            JSONObject allCountriesObject = new JSONObject(countriesResponseString);
            JSONArray allCountriesArray = allCountriesObject.getJSONArray("results");
            //countryList = new String[allCountriesArray.length()];
            for(int i = 0; i < allCountriesArray.length(); i++){
                JSONObject childJson = allCountriesArray.getJSONObject(i);
                // check if it has name
                if(childJson.has("name")){
                    String name = childJson.getString("name");
                    if(name != null) countryList.add(name);
                }
            } // end for
        } catch(JSONException e){
            e.printStackTrace();
        }
        return countryList;
    } // end of parse

    
    public static URL buildRedditUrl(String searchTerm){
        final String baseUrl = "https://www.reddit.com/r/";
        final String endformat = ".json";
        URL redditUrl = null;
        String urlString = baseUrl +  searchTerm + endformat;
        try{
            redditUrl = new URL(urlString);
            Log.d("informational", "URL:" + urlString);
        } catch(MalformedURLException e){
            System.out.println("The url is not correctly formatted.");
            e.printStackTrace();
        }
        return redditUrl;
    } // end buildUrl

    public static String[] parseRedditJson(String redditResponseString){
        String[] newsTitles = new String[25];
        try{
            JSONObject allNewsReddit = new JSONObject(redditResponseString);
            JSONObject allNewsObject = allNewsReddit.getJSONObject("data");
            JSONArray children = allNewsObject.getJSONArray("children");
            newsTitles = new String[children.length()];
            for (int i = 0; i < newsTitles.length; i++){
                JSONObject childJson = children.getJSONObject(i);
                JSONObject childData = childJson.getJSONObject("data");
                String title = childData.getString("title");

                newsTitles[i] = title;
            }   // end of for
        }catch(JSONException e){
            e.printStackTrace();
        }
        return newsTitles;
    }  // end of parseRedditJson





} // end of class

