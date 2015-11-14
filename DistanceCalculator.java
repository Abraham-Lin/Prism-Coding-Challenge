import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.io.*;
import com.google.maps.*;

public class DistanceCalculator {
	/* metric given to us by the user */
	static String metric; 

	public static void main(String[] args) {
		ArrayList<String> destinations = new ArrayList<String>();
		ArrayList<Double> distances = new ArrayList<Double>();
		Double totalDistance = 0.0;

		/* Prompt the user to see if they want miles or kilometers? */
        System.out.println("Do you want Miles or Kilometers? ");
        Scanner scan = new Scanner(System.in);
        String metric = scan.next();
        scan.close();

        /* Read in the destination.txt file on my local machine. Please adjust this line to whatever the filepath is on your machine */
        String filePath = "/Users/AbrahamL/Desktop/Recruiting/Prism-Skylab/destination.txt";
        String line = null;

        try {
 
        	FileReader fileReader = new FileReader(filePath);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	String bufferedLine = bufferedReader.readLine();
        	while(bufferedLine != null) {
        		destinations.add(line);
        		bufferedLine = bufferedReader.readLine();
        	}

        	bufferedReader.close();

        } catch(IOException e) {
        	System.out.println("Threw an IOException. Could not read your file.");
        } catch(FileNotFoundException e) {
        	System.out.println("Threw a FileNotFoundException. Could not find your file.");
        }

        /* Some help from our friends at Google. 
           https://developers.google.com/maps/documentation/geocoding/intro
         */

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBthfbAmMeNpE7AFIhDI0ZVYQg0ZcO3jKE");
        for (int i = 0; i < destinations.size() - 1; i++) {
        	try {
        		GeocodingResult[] from = GeocodingApi.geocode(context, destinations.get(i)).await();
        		GeocodingResult[] to = GeocodingApi.geocode(context, destinations.get(i+1)).await();

        		Double d = getDistance(from, to, metric); 
        		distances.add(d);
        	} catch(Exception e) {
        		System.out.println("Some error when accessing the Google Maps API.");
        	}
        }

        /* We finished all the hard work. Now we simply print out our results. */
        for (int i = 0; i < distances.size(); i++) {
        	totalDistance = totalDistance + distances.get(i);
        	System.out.println(destinations.get(i) + " ->" + destinations.get(i+1) + ": " + distances.get(i) + " " + metric); 
        }

        System.out.println("Total distance covered in your trip was " + totalDistance + metric);
	}

	/* Adapted from our great friends from Stackoverflow. God Bless Google's vast APIs. 
	   http://stackoverflow.com/questions/2296087/using-php-and-google-maps-api-to-work-out-distance-between-2-post-codes-uk
	   http://stackoverflow.com/questions/1110565/distance-between-2-geocodes
	 */
	public static int getDistance(GeocodingResult[] from, GeocodingResult[] to, String metric) {
		double fromLat = from[0].geometry.location.lat;
		double fromLong = from[0].geometry.location.lng;

		double toLat = to[0].geometry.location.lat;
		double toLong = to[0].geometry.location.lng;

		/* The actual straightline distance between the two points. Lots of math here. Lets just trust it works. */
		double distance = (Math.sin(Math.toRadians(fromLat)) * Math.sin(Math.toRadians(toLat)) +  Math.cos(Math.toRadians(fromLat) * Math.cos(Math.toRadians(toLat)) *
                        Math.cos(Math.toRadians(fromLat - toLat))));

		/* Do we want miles or kilometers? */
		if (metric.charAt(0) == 'k') {
			return new Double((Math.toDegrees(Math.acos(distance))) * 69.09 * 1.6093).intValue();
		}
		 return new Double((Math.toDegrees(Math.acos(distance))) * 69.09).intValue();
	}	
}