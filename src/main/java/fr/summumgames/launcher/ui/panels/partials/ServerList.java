package fr.summumgames.launcher.ui.panels.partials;

import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.ui.panels.pages.App;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
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

public class ServerList extends Panel {

    private Saver saver = Launcher.instance.getSaver();

    public void showServerList(GridPane pane, GridPane centerPane, GridPane appPane) {

        Separator serverLeftSeparator = new Separator();
        GridPane.setVgrow(serverLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(serverLeftSeparator, Priority.ALWAYS);
        serverLeftSeparator.setOrientation(Orientation.VERTICAL);
        serverLeftSeparator.setMinWidth(3);
        serverLeftSeparator.setMaxWidth(3);
        serverLeftSeparator.setMinHeight(40);
        serverLeftSeparator.setMaxHeight(40);
        serverLeftSeparator.setStyle("-fx-background-color: rgb(184, 65, 45); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(184, 65, 45);");
        try {
            switch (saver.get("serverSelector")) {
                case "skyblock":
                    serverLeftSeparator.setTranslateY(0);
                    App.instance.showSkyblockPage();
                    break;
                case "survival":
                    serverLeftSeparator.setTranslateY(65);
                    App.instance.showSurvivalPage();
                    break;
                case "apocalypse":
                    serverLeftSeparator.setTranslateY(130);
                    App.instance.showApocalypsePage();
                    break;
                case "oneblock":
                    serverLeftSeparator.setTranslateY(195);
                    App.instance.showOneblockPage();
                    break;
                default:
                    serverLeftSeparator.setTranslateY(0);
                    App.instance.showSkyblockPage();
                    break;
            }
        } catch (NullPointerException e) {
            serverLeftSeparator.setTranslateY(0);
            App.instance.showSkyblockPage();
        }

        ImageView skyblockView = new ImageView();
        Image skyblockImg = new Image("images/TempoSky.png");
        skyblockView.setImage(skyblockImg);
        skyblockView.setPreserveRatio(true);
        skyblockView.setFitHeight(50d);

        GridPane.setHgrow(skyblockView, Priority.ALWAYS);
        GridPane.setVgrow(skyblockView, Priority.ALWAYS);
        GridPane.setValignment(skyblockView, VPos.CENTER);
        skyblockView.setTranslateX(34);
        skyblockView.setPreserveRatio(true);
        skyblockView.setFitWidth(28);

        Label skyblockLabel = new Label("SkyBlock");
        GridPane.setHgrow(skyblockLabel, Priority.ALWAYS);
        GridPane.setVgrow(skyblockLabel, Priority.ALWAYS);
        GridPane.setValignment(skyblockLabel, VPos.CENTER);
        skyblockLabel.setTranslateX(90);
        skyblockLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #fff;");
        skyblockLabel.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        skyblockLabel.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        skyblockLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(0);
            centerPane.getChildren().clear();
            App.instance.showSkyblockPage();
            saver.set("serverSelector", "skyblock");
            saver.save();
        });

        ImageView survivalView = new ImageView();
        Image survivalImg = new Image("images/TempoSurvival.png");
        survivalView.setImage(survivalImg);
        survivalView.setPreserveRatio(true);
        survivalView.setFitHeight(50d);

        GridPane.setHgrow(survivalView, Priority.ALWAYS);
        GridPane.setVgrow(survivalView, Priority.ALWAYS);
        GridPane.setValignment(survivalView, VPos.CENTER);
        survivalView.setTranslateX(34);
        survivalView.setTranslateY(65);
        survivalView.setPreserveRatio(true);
        survivalView.setFitWidth(28);

        Label survivalLabel = new Label("Survie");
        GridPane.setHgrow(survivalLabel, Priority.ALWAYS);
        GridPane.setVgrow(survivalLabel, Priority.ALWAYS);
        GridPane.setValignment(survivalLabel, VPos.CENTER);
        survivalLabel.setTranslateX(90);
        survivalLabel.setTranslateY(65);
        survivalLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #fff;");
        survivalLabel.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        survivalLabel.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        survivalLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(65);
            centerPane.getChildren().clear();
            App.instance.showSurvivalPage();
            saver.set("serverSelector", "survival");
            saver.save();
        });

        ImageView apocalypseView = new ImageView();
        Image apocalypseImg = new Image("images/TempoApocalypse.png");
        apocalypseView.setImage(apocalypseImg);
        apocalypseView.setPreserveRatio(true);
        apocalypseView.setFitHeight(50d);

        GridPane.setHgrow(apocalypseView, Priority.ALWAYS);
        GridPane.setVgrow(apocalypseView, Priority.ALWAYS);
        GridPane.setValignment(apocalypseView, VPos.CENTER);
        apocalypseView.setTranslateX(34);
        apocalypseView.setTranslateY(130);
        apocalypseView.setPreserveRatio(true);
        apocalypseView.setFitWidth(28);

        Label apocalypseLabel = new Label("Apocalypse");
        GridPane.setHgrow(apocalypseLabel, Priority.ALWAYS);
        GridPane.setVgrow(apocalypseLabel, Priority.ALWAYS);
        GridPane.setValignment(apocalypseLabel, VPos.CENTER);
        apocalypseLabel.setTranslateX(90);
        apocalypseLabel.setTranslateY(130);
        apocalypseLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #fff;");
        apocalypseLabel.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        apocalypseLabel.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        apocalypseLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(130);
            saver.set("serverSelector", "apocalypse");
            centerPane.getChildren().clear();
            App.instance.showApocalypsePage();
            saver.save();
        });

        ImageView oneblockView = new ImageView();
        Image oneblockImg = new Image("images/TempoOneblock.png");
        oneblockView.setImage(oneblockImg);
        oneblockView.setPreserveRatio(true);
        oneblockView.setFitHeight(50d);

        GridPane.setHgrow(oneblockView, Priority.ALWAYS);
        GridPane.setVgrow(oneblockView, Priority.ALWAYS);
        GridPane.setValignment(oneblockView, VPos.CENTER);
        oneblockView.setTranslateX(34);
        oneblockView.setTranslateY(195);
        oneblockView.setPreserveRatio(true);
        oneblockView.setFitWidth(28);

        Label oneblockLabel = new Label("OneBlock");
        GridPane.setHgrow(oneblockLabel, Priority.ALWAYS);
        GridPane.setVgrow(oneblockLabel, Priority.ALWAYS);
        GridPane.setValignment(oneblockLabel, VPos.CENTER);
        oneblockLabel.setTranslateX(90);
        oneblockLabel.setTranslateY(195);
        oneblockLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #fff;");
        oneblockLabel.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        oneblockLabel.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        oneblockLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(195);
            saver.set("serverSelector", "oneblock");
            centerPane.getChildren().clear();
            App.instance.showOneblockPage();
            saver.save();
        });


        pane.getChildren().addAll(serverLeftSeparator, skyblockView, skyblockLabel,
                survivalView, survivalLabel,
                apocalypseView, apocalypseLabel,
                oneblockView, oneblockLabel);
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
