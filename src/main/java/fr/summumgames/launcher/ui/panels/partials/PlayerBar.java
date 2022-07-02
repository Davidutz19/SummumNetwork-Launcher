package fr.summumgames.launcher.ui.panels.partials;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.ui.panels.pages.App;
import fr.summumgames.launcher.ui.panels.pages.Login;
import fr.summumgames.launcher.ui.panels.pages.SkyblockPage;
import fr.summumgames.launcher.utils.Constants;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

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

public class PlayerBar extends Panel {

    Saver saver = Launcher.getInstance().getSaver();

    public PlayerBar() {

    }

    public void showPlayerBar(GridPane pane, GridPane appPane, SkyblockPage skyblockPage, App app) {

        Separator blueLeftSeparator = new Separator();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        blueLeftSeparator.setOrientation(Orientation.VERTICAL);
        blueLeftSeparator.setMinWidth(3);
        blueLeftSeparator.setMaxWidth(3);
        blueLeftSeparator.setMinHeight(40);
        blueLeftSeparator.setMaxHeight(40);
        blueLeftSeparator.setStyle("-fx-background-color: rgb(184, 65, 45); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(184, 65, 45);");

        Image avatarImg = new Image(avatarUrl());
        ImageView avatarView = new ImageView();
        avatarView.setImage(avatarImg);
        avatarView.setPreserveRatio(true);
        avatarView.setFitHeight(50d);

        GridPane.setHgrow(avatarView, Priority.ALWAYS);
        GridPane.setVgrow(avatarView, Priority.ALWAYS);
        GridPane.setValignment(avatarView, VPos.CENTER);
        avatarView.setTranslateX(34);
        avatarView.setPreserveRatio(true);
        avatarView.setFitWidth(28);

        Label playerNameLabel = new Label(Launcher.getInstance().getAuthInfos().getUsername());
        GridPane.setHgrow(playerNameLabel, Priority.ALWAYS);
        GridPane.setVgrow(playerNameLabel, Priority.ALWAYS);
        GridPane.setValignment(playerNameLabel, VPos.CENTER);
        playerNameLabel.setTranslateX(90);
        playerNameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff;");

        Button logoutButton = new Button();
        GridPane.setVgrow(logoutButton, Priority.ALWAYS);
        GridPane.setHgrow(logoutButton, Priority.ALWAYS);
        GridPane.setValignment(logoutButton, VPos.TOP);
        GridPane.setHalignment(logoutButton, HPos.LEFT);
        MaterialDesignIconView logoutIcon = new MaterialDesignIconView(MaterialDesignIcon.LOGOUT);
        logoutIcon.setSize("18px");
        logoutIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        logoutButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        logoutButton.setTranslateY(-5);
        logoutButton.setTranslateX(-5);
        setBottom(logoutButton);
        setRight(logoutButton);
        logoutButton.setMinWidth(26);
        logoutButton.setMaxWidth(26);
        logoutButton.setMinHeight(26);
        logoutButton.setMaxHeight(26);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        logoutButton.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        logoutButton.setOnMouseClicked(e -> {
            if (skyblockPage.minecraftLaunching.isDownloading()) {
                return;
            }

            saver.remove("accesToken");
            saver.remove("clientToken");
            saver.remove("msAccessToken");
            saver.remove("msRefreshToken");
            saver.remove("offline-username");
            saver.save();
            Launcher.getInstance().setAuthInfos(null);
            app.panelManager.showPanel(new Login());
        });

        pane.getChildren().addAll(blueLeftSeparator, avatarView, playerNameLabel, logoutButton);
    }

    public String avatarUrl() {
        if (saver.get("offline-username") != null) {
            return Constants.AVATAR_URL + "MHF_STEVE";
        } else {
            return Constants.AVATAR_URL + Launcher.getInstance().getAuthInfos().getUuid();
        }
    }

    @Override
    public String getStyleSheetPath() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
