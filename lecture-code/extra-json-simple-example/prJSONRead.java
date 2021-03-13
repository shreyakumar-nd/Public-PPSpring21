//simple read and parse json example
import java.io.FileReader;
import org.json.simple.JSONArray; // these are being imported from a special jar file in the local directory
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

// note: to compile and run this code, you will need to tell java how to find the org.json.simple package. There is a jar file in the local directory which has the package. So we will update the CLASSPATH variable through the terminal to show Java where to look for imported classes.
// in bash, this is done with the following line
// export CLASSPATH=$CLASSPATH:./org-json-simple.jar
// after running that line, you will be able to compile and run.

public class prJSONRead {

	public static void main(String[] args) throws Exception {
		Object obj = new JSONParser().parse(new FileReader("mini-movies.json"));
		
		// parsing the object obtained through parse
		JSONArray ja = (JSONArray) obj;
		
		// iterate through the array
		for (Object o : ja) { // Note polymorphism helps here.
			JSONObject jo = (JSONObject) o;
			// get movie title
			String mtitle = (String) jo.get("title"); // dynamic/explicit cast
			System.out.print(mtitle + "		");
			
			// get year
			String myearString = (String) jo.get("releaseYear");
			int myear = Integer.parseInt(myearString); // static method call
			System.out.print(myear  + "		");
			
			// get genres
			String allGenre = "";
			JSONArray genreArray  = (JSONArray) jo.get("genre"); // another explicit cast
			for (Object oGenre : genreArray) {
				String oneGenre = (String) oGenre;
				allGenre = allGenre + ", " + oneGenre;
			}	// end of genre for
			System.out.println(allGenre);
			
		}	// end of for - iterates over each movie
		
	}	// end of main
}

