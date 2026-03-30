import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class WeatherApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        try {
            String apiKey = "5e7a41eb1daaf4b458925b95128a75ba"; // replace later
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Print raw JSON (for now)
           // Convert response to JSON
org.json.JSONObject json = new org.json.JSONObject(response.toString());

// Extract values
double temp = json.getJSONObject("main").getDouble("temp");
int humidity = json.getJSONObject("main").getInt("humidity");
String weather = json.getJSONArray("weather")
                     .getJSONObject(0)
                     .getString("description");

// Print nicely
System.out.println("\n🌦 Weather in " + city);
System.out.println("Temperature: " + temp + "°C");
System.out.println("Humidity: " + humidity + "%");
System.out.println("Condition: " + weather);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}