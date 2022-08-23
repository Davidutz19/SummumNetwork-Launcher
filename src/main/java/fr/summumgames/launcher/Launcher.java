package fr.summumgames.launcher;

import com.azuriom.azauth.AuthClient;
import com.azuriom.azauth.exception.AuthException;
import com.azuriom.azauth.model.User;
import fr.summumgames.launcher.auth.AuthAzuriom;
import fr.summumgames.launcher.game.MinecraftLaunching;
import fr.summumgames.launcher.ui.PanelManager;
import fr.summumgames.launcher.ui.panels.pages.Home;
import fr.summumgames.launcher.ui.panels.pages.Login;
import fr.summumgames.launcher.utils.Constants;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;

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

public class Launcher extends Application {
    private PanelManager panelManager;
    public static Launcher instance;
    private final ILogger logger;
    private final Path launcherDir = GameDirGenerator.createGameDir(Constants.LAUNCHER_NAME, true);
    private final Path skyblockDir = launcherDir.resolve("Skyblock");
    private final Path survivalDir = launcherDir.resolve("Survie");
    private final Path apocalypseDir = launcherDir.resolve("Apocalypse");
    private final Path oneblockDir = launcherDir.resolve("Oneblock");
    private final Saver saver;

    private AuthInfos authInfos = null;

    public Launcher() {
        instance = this;
        this.logger = new Logger("[SummumNetworkLauncher]", Paths.get(this.launcherDir.toString(), "launcher.log"));
        if (!this.launcherDir.toFile().exists()) {
            if (!this.launcherDir.toFile().mkdir()) {
                this.logger.err("Unable to create launcher folder");
            }
        }

        saver = new Saver(Paths.get(launcherDir.toString(), "config.properties"));

        saver.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.logger.info("Starting Launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        if (this.isUserAlreadyLoggedIn()) {
            logger.info("Hello " + authInfos.getUsername() + " !");
            panelManager.showPanel(new Home());
        }else {
            this.panelManager.showPanel(new Login());
        }
    }

    public boolean isUserAlreadyLoggedIn() {
        if (saver.get("msAccessToken") != null && saver.get("msRefreshToken") != null) {

            try {
                MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
                MicrosoftAuthResult response = authenticator.loginWithRefreshToken(saver.get("msRefreshToken"));

                saver.set("msAccessToken", response.getAccessToken());
                saver.set("msRefreshToken", response.getRefreshToken());
                saver.remove("offline-username");
                saver.remove("offline-accessToken");
                saver.remove("offline-uuid");
                saver.save();

                this.setAuthInfos(new AuthInfos(
                        response.getProfile().getName(),
                        response.getAccessToken(),
                        response.getProfile().getId()
                ));

                return true;

            } catch (MicrosoftAuthenticationException e) {
                saver.remove("msAccessToken");
                saver.remove("msRefreshToken");
                saver.save();
            }

        }else if (saver.get("az-uuid") != null && saver.get("az-username") != null && saver.get("az-accessToken") != null) {
            AuthClient authenticator = new AuthClient("http://192.168.0.113");

            try {
                User azUser = authenticator.verify(saver.get("az-accessToken"));

                saver.remove("msAccessToken");
                saver.remove("msRefreshToken");
                saver.set("az-username", azUser.getUsername());
                saver.set("az-accessToken", azUser.getAccessToken());
                saver.set("az-uuid", String.valueOf(azUser.getId()));
                saver.save();

                this.setAuthInfos(new AuthInfos(
                        azUser.getUsername(),
                        azUser.getAccessToken(),
                        String.valueOf(azUser.getId())
                ));

                return true;

            } catch (AuthException e) {
                saver.remove("az-username");
                saver.remove("az-accessToken");
                saver.remove("az-uuid");
            }

        }
        return false;
    }

    public void setAuthInfos(AuthInfos authInfos) {
        this.authInfos = authInfos;
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
    }

    public ILogger getLogger() {
        return logger;
    }

    public static Launcher getInstance() {
        return instance;
    }

    public Saver getSaver() {
        return saver;
    }

    public Path getLauncherDir() {
        return launcherDir;
    }

    public Path getSkyblockDir() {
        return skyblockDir;
    }

    public Path getSurvivalDir() {
        return survivalDir;
    }

    public Path getApocalypseDir() {
        return apocalypseDir;
    }

    public Path getOneblockDir() {
        return oneblockDir;
    }

    public void Stop() {
        logger.info("Stopping Launcher");
        try {
            MinecraftLaunching.instance.getT().interrupt();
        } catch (NullPointerException e) {  }
        Platform.exit();
        System.exit(0);
    }

    public void hideWindow() {
        this.panelManager.getStage().hide();
    }

}
