import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.Reader;
import java.io.InputStream;
import java.util.Scanner;



public class UrlReadDemo {

    // option 1 - using Scanner and special delimiter
    public static String readStringFromURL(String urlString) throws IOException {
	    String allString = "";
	    try {
	    	URL url = new URL(urlString);
	    	InputStream is = url.openStream();

	    	Scanner scanner = new Scanner(is);

	        scanner.useDelimiter("\\A"); // start of string delimiter
	        allString = scanner.hasNext() ? scanner.next() : ""; // now next takes all of it
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
		return allString;
	}// end of method


    // option 2 - using HTTP conn and Buffered reader
    public static String readUsingHTTPConn(String urlstr) throws IOException{
        URL url = new URL(urlstr);
        // making connection to internet resource
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
            + conn.getResponseCode()); // you mean for someone to handle this, or for the program to fail.
        }

        // reading from API response
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output = "";
        String line;
        while ((line = br.readLine()) != null) {
            output += line;
        }
        //System.out.println(output);

        conn.disconnect(); // closing connection.
        return output;
    } // end of method


    // option 3 - using BufferedReader and String builder.
    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1) {
            sb.append((char) cp);
        } // end of while

        return sb.toString();
    }	// end of method

    public static String readFromUrl(String urlString) throws IOException{
        URL url = new URL(urlString);
        InputStream is = url.openStream();
        String responseText = "no response";
        try {
            //buffered read
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader rd = new BufferedReader(isr);
            responseText = readAll(rd); // reads using StringBuilder and reader
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            is.close();
        }
        return responseText;
    } // end of readFromUrl



    public static void main(String[] args) {

        try {
            // API that we are trying to connect to
            String urlstr = "http://numbersapi.com/7"; //"http://numbersapi.com/random/year?json"; // //"https://jsonplaceholder.typicode.com/todos/1";

            String responseString = "";

            responseString = readStringFromURL(urlstr); // option 1
            System.out.println("Response string: ");
            System.out.println(responseString);

            responseString = readUsingHTTPConn(urlstr); // option 2
            System.out.println("Response string: ");
            System.out.println(responseString);

            responseString = readFromUrl(urlstr); // option 3
            System.out.println("Response string: ");
            System.out.println(responseString);

        } catch (MalformedURLException e) {
            System.out.println("Your url may have been incorrect. Please check.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There was a problem in reading from the source.");
            e.printStackTrace();
        } // end of IOException
        finally {
            System.out.println("Reading attempts are over.");
        }
        System.out.println("executes after try-catch-finally");

    } // end of main
} // end of class
