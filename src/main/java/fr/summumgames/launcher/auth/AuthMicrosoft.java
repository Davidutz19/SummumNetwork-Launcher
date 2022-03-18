package fr.summumgames.launcher.auth;

import fr.flowarg.flowlogger.ILogger;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.summumgames.launcher.Launcher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.scene.control.Label;

public class AuthMicrosoft {

    private ILogger logger = Launcher.getInstance().getLogger();
    private Saver saver = Launcher.getInstance().getSaver();

    private Boolean isConnected = false;

    public void login(String username, String password, Label error) {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        try {
            MicrosoftAuthResult result = authenticator.loginWithCredentials(username, password);
            this.saveCredentials(result);

            this.saveAutInfos(result);

            this.logger.info("Hello" + " " + result.getProfile().getName());

            this.isConnected = true;
        } catch (MicrosoftAuthenticationException e) {
            error.setText("Adresse mail ou mot de passe invalide");
            return;
        }
    }

    public void saveCredentials(MicrosoftAuthResult result) {
        saver.set("msAccessToken", result.getAccessToken());
        saver.set("msRefreshToken", result.getRefreshToken());
        saver.set("offline-username", "");
        saver.save();
    }

    public void saveAutInfos(MicrosoftAuthResult result) {
        AuthInfos infos = new AuthInfos(
                result.getProfile().getName(),
                result.getAccessToken(),
                result.getProfile().getId()
        );

        Launcher.getInstance().setAuthInfos(infos);
    }

    public Boolean isConnected() {
        return isConnected;
    }
}
