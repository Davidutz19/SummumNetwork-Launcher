package fr.summumgames.launcher.auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.flowarg.flowlogger.ILogger;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.utils.Constants;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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

public class AuthMineweb {

    private ILogger logger = Launcher.getInstance().getLogger();
    private Saver saver = Launcher.getInstance().getSaver();

    private boolean connected = false;
    private String username;
    private String hash;

    public void login(String username, String password, Label errorLabel) {
        try {
            URLConnection connection = (new URL(Constants.WEB_URL + "auth/start?username=" + username + "&password=" + getSha512(password)).openConnection());
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                switch (inputLine) {
                    case "success_ok":
                        setHash(getSha512(password));
                        setUsername(username);
                        setConnected(true);
                        saveCredentials();
                        saveAutInfos();
                        break;
                    case "error_password":
                        errorLabel.setText("Nom d'utilisateur ou mot de passe invalide");
                    default:
                        break;
                }
            }
            in.close();
        } catch (Exception e) {
            errorLabel.setText("Impossible de se connectez au serveur de authentification");
            logger.warn(e.getMessage());
        }
    }

    public void saveCredentials() {
        saver.set("offline-username", username);
        saver.set("offline-accessToken", getInfos("auth-accessToken"));
        saver.set("offline-uuid", getInfos("auth-uuid"));
        saver.remove("msAccessToken");
        saver.remove("msRefreshToken");
        saver.save();
    }

    public void saveAutInfos() {
        AuthInfos infos = new AuthInfos(username,
                saver.get("offline-accessToken"),
                saver.get("offline-uuid"));

        Launcher.getInstance().setAuthInfos(infos);
    }

    public String getInfos(String info) {

        if (isConnected()) {
            try {
                URLConnection connection = (new URL(Constants.WEB_URL + "auth/getDataLauncher?username=" + username + "&password=" + getHash()).openConnection());
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    JsonObject jsonObject = (JsonObject) new JsonParser().parse(inputLine);
                    return String.valueOf(jsonObject.get(info)).replaceAll("\"", "");
                }
            }catch (Exception e) {
                logger.warn(e.getMessage());
            }
        } else {
            logger.warn("Vous n'êtes pas connecté.");
        }
        return "";
    }

    public String getNameWithToken(String token) {
        try {
            URLConnection connection = (new URL(Constants.WEB_URL + "auth/getDataIngame?accessToken=" + token).openConnection());
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JsonObject jsonObject = (JsonObject) new JsonParser().parse(inputLine);
                return String.valueOf(jsonObject.get("pseudo")).replaceAll("\"", "");
            }
        }catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return "";
    }

    private String getSha512(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        AtomicReference<StringBuffer> stringBuffer = new AtomicReference<>(new StringBuffer());
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) stringBuffer.get().append('0');
            stringBuffer.get().append(hex);
        }
        return stringBuffer.toString();
    }

    public boolean isConnected() {
        return connected;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    private void setConnected(boolean connected) {
        this.connected = connected;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setHash(String hash) {
        this.hash = hash;
    }
}
