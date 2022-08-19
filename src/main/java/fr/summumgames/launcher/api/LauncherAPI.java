package fr.summumgames.launcher.api;

import fr.summumgames.launcher.utils.Constants;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * SummumNetwork Launcher
 *
 * @author SummumNetwork Team
 * @version 1.0
 * @since 02-03-2022
 * @link https://www.summum-network.net
 *
 * SummumNetwork© All right Reserved
 *
 **/

public class LauncherAPI {

    JSONObject packageInfo;

    public LauncherAPI() throws IOException {
        setPackageInfo(new URL(Constants.API_URL + "package-info"));

    }

    private void setPackageInfo(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestProperty("http.agent", "Chrome");
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpRequestCode: " + responseCode);
            } else {

                StringBuilder result = new StringBuilder();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while ((line = br.readLine()) != null) {
                    result.append(line);
                }

                this.packageInfo = new JSONObject(result.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getPackageInfo() {
        return packageInfo;
    }
}
