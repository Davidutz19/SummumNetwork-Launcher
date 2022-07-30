package fr.summumgames.launcher.auth;

import fr.flowarg.flowlogger.ILogger;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.ui.PanelManager;
import fr.summumgames.launcher.ui.panels.pages.App;
import fr.summumgames.launcher.ui.panels.pages.Login;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.scene.control.Label;

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

public class AuthMicrosoft {

    private ILogger logger = Launcher.getInstance().getLogger();
    private Saver saver = Launcher.getInstance().getSaver();
    private Boolean isConnected = false;

    public void login(PanelManager panelManager, Label error) {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();

        authenticator.loginWithAsyncWebview().whenComplete(((result, throwable) -> {
            if (throwable != null) {
                error.setText("Une erreur est survenue");
            } else {
                this.saveCredentials(result);

                this.saveAutInfos(result);

                this.logger.info("Hello" + " " + result.getProfile().getName());

                this.isConnected = true;

                Platform.runLater( () -> { panelManager.showPanel(new App()); });
            }
        }));
    }

    public void saveCredentials(MicrosoftAuthResult result) {
        saver.set("msAccessToken", result.getAccessToken());
        saver.set("msRefreshToken", result.getRefreshToken());
        saver.remove("offline-username");
        saver.remove("offline-accessToken");
        saver.remove("offline-uuid");
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
