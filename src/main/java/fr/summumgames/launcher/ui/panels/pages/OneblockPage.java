package fr.summumgames.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.game.MinecraftLaunching;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.utils.Constants;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;


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

public class OneblockPage extends Panel {

    GridPane pane;
    GridPane appPane;
    GridPane backgroundImage;
    ProgressBar progressBar;
    Label stepLabel;
    Saver saver = Launcher.instance.getSaver();

    protected MinecraftLaunching minecraftLaunching = new MinecraftLaunching(pane, progressBar, stepLabel, saver, "oneblock");

    public OneblockPage(GridPane centerPane, GridPane appPane, GridPane backgroundImage) {
        this.pane = centerPane;
        this.appPane = appPane;
        this.backgroundImage = backgroundImage;
    }

    public void showOneblockMenu() {
        Home.instance.setShowedMenu("oneblock");
        Home.instance.onShow();

        backgroundImage.setStyle("-fx-background-image: url(images/background-skyblock.jpg);");

        ImageView imgView = new ImageView();
        Image img = new Image("images/TempoOneblock.png");
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(100d);

        GridPane.setHgrow(imgView, Priority.ALWAYS);
        GridPane.setVgrow(imgView, Priority.ALWAYS);
        GridPane.setValignment(imgView, VPos.TOP);
        imgView.setTranslateY(20);
        imgView.setTranslateX(-390);
        setCenterH(imgView);
        imgView.setPreserveRatio(true);

        Label title = new Label("Oneblock");
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(title, VPos.TOP);
        setCenterH(title);
        title.setStyle("-fx-font-size: 50px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        title.setTranslateY(30);
        title.setTranslateX(-215);

        Label desc = new Label("Le skyblock est le mode de jeu principal de SummumNetwork. \n" +
                "Celui-ci est un jeu dans lequel vous devez développer votre île \n" +
                "afin d'acquérir de multiples richesses et de construire une île \n" +
                "développé et belle. Il y a aussi le moyen de gagner des \n" +
                "SummumCoins avec les richesses que vous acquérirez \n" +
                "pour pouvoir ainsi placer ces SummumCoins pour \n" +
                "gagner des Coins sur le site et pouvoir encore plus\n" +
                "vous développer et avancer !"
        );
        GridPane.setVgrow(desc, Priority.ALWAYS);
        GridPane.setHgrow(desc, Priority.ALWAYS);
        GridPane.setValignment(desc, VPos.TOP);
        setCenterH(desc);
        desc.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff; -fx-opacity: 90%;");
        desc.setTranslateY(130);
        desc.setTranslateX(-250);

        Button playButton = new Button("Jouer");
        GridPane.setVgrow(playButton, Priority.ALWAYS);
        GridPane.setHgrow(playButton, Priority.ALWAYS);
        GridPane.setValignment(playButton, VPos.TOP);
        setCenterH(playButton);
        playButton.setTranslateY(315);
        playButton.setTranslateX(-310);
        playButton.setMinWidth(140);
        playButton.setMaxHeight(40);
        playButton.setStyle("-fx-background-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");
        playButton.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        playButton.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        playButton.setOnMouseClicked(e -> {
            playButton.setDisable(true);
            this.play(pane, "oneblock");
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.TOP);
        setCenterH(settingsButton);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        settingsButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color:" + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        settingsButton.setTranslateX(-220);
        settingsButton.setTranslateY(322);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e -> appPane.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e -> appPane.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e -> {
            if (!minecraftLaunching.isDownloading()) {
                pane.getChildren().clear();
                Home.instance.showSettingsPage();
            }
        });

        progressBar.setStyle("-fx-accent: rgb(184, 65, 45);");
        GridPane.setVgrow(progressBar, Priority.ALWAYS);
        GridPane.setHgrow(progressBar, Priority.ALWAYS);
        GridPane.setValignment(progressBar, VPos.TOP);
        setCenterH(progressBar);
        progressBar.setMinWidth(230);
        progressBar.setTranslateY(365);
        progressBar.setTranslateX(-295);

        stepLabel.setStyle("-fx-text-fill: rgba(255, 255, 255);");
        GridPane.setVgrow(stepLabel, Priority.ALWAYS);
        GridPane.setHgrow(stepLabel, Priority.ALWAYS);
        GridPane.setValignment(stepLabel, VPos.TOP);
        setCenterH(stepLabel);
        stepLabel.setMinWidth(450);
        stepLabel.setTranslateY(385);
        stepLabel.setTranslateX(-185);

        ArrayList<Image> content = new ArrayList<>();
        content.add(new Image("images/skyblock/item1.png"));
        content.add(new Image("images/skyblock/item2.png"));

//        showImageSlider(pane, content);

        pane.getChildren().addAll(title, imgView, desc, playButton, settingsButton);
    }

    public void play(GridPane pane, String serverName) {
        new MinecraftLaunching(pane, progressBar, stepLabel, saver, serverName);
        minecraftLaunching.setIsDownloading(true);
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
