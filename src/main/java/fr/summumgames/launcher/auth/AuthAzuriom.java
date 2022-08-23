package fr.summumgames.launcher.auth;

import com.azuriom.azauth.AuthClient;
import com.azuriom.azauth.exception.AuthException;
import fr.flowarg.flowlogger.ILogger;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.ui.PanelManager;
import fr.summumgames.launcher.ui.panels.pages.Home;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;

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

public class AuthAzuriom {
    private ILogger logger = Launcher.getInstance().getLogger();
    private Saver saver = Launcher.getInstance().getSaver();
    private Boolean isConnected = false;

    public void login(PanelManager panelManager, String email, String password, Button connect, Label error) {
        if (!email.contains("@")) {
            connect.setDisable(false);
            error.setText("Veuillez saisir une adresse mail");
            return;
        }

        AuthClient authenticator = new AuthClient("http://192.168.0.113");
        try {
            AuthInfos authInfos = authenticator.login(email, password, () -> {
                String code = null;

                while (code == null || code.isEmpty()) {

                    code = JOptionPane.showInputDialog(null, "2FA", JOptionPane.PLAIN_MESSAGE);
                }

                return code;
            }, AuthInfos.class);

            this.saveCredentials(authInfos);

            this.saveAuthInfos(authInfos);

            this.logger.info("Hello" + " " + authInfos.getUsername());

            this.isConnected = true;

            Platform.runLater( () -> { panelManager.showPanel(new Home()); });
        } catch (AuthException e) {
            connect.setDisable(false);
            switch (e.getMessage()) {
                case "Invalid credentials":
                    error.setText("Email ou Mot de passe invalid");
                    break;
                case "Invalid 2FA code":
                    error.setText("Code 2FA invalid");
                    break;
                default:
                    error.setText("Une erreur est survenue");
                    break;
            }
        }
    }

    public void saveCredentials(AuthInfos infos) {
        saver.set("az-accessToken", infos.getAccessToken());
        saver.set("az-username", infos.getUsername());
        saver.set("az-uuid", infos.getUuid());
        saver.remove("msAccessToken");
        saver.remove("msRefreshToken");
        saver.save();
    }

    public void saveAuthInfos(AuthInfos azInfos) {
        AuthInfos infos = new AuthInfos(
                azInfos.getUsername(),
                azInfos.getAccessToken(),
                azInfos.getUuid()
        );

        Launcher.getInstance().setAuthInfos(infos);
    }

    public Boolean isConnected() {
        return isConnected;
    }
}
