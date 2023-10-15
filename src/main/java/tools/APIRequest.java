package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIRequest
{

    private String root;

    public APIRequest(String root)
    {
        this.root = root;
    }

    public String get(String endpoint)
    {
        try
        {
            URL url = new URL(root + endpoint);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder content = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) content.append(line);

                reader.close();

                return content.toString();
            }
        }
        catch (Exception e) {e.printStackTrace();}

        return null;
    }

}
