package fr.summumgames.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.game.MinecraftInfos;
import fr.summumgames.launcher.game.MinecraftLaunching;
import fr.summumgames.launcher.ui.PanelManager;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.utils.Constants;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

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

public class App extends Panel {

    private GridPane centerPane = new GridPane();
    private GridPane appPane = new GridPane();
    private GridPane topPanel = new GridPane();
    private GridPane backgroundImage = new GridPane();

    private String showedMenu;

    Saver saver = Launcher.instance.getSaver();

    ProgressBar progressBar = new ProgressBar();
    Label stepLabel = new Label();
    boolean isDownloading = false;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStyleSheetPath() {
        return "css/app.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        setCanTakeAllSize(backgroundImage);
        backgroundImage.getStyleClass().add("background-image");
        this.layout.add(backgroundImage, 0, 0);

        ColumnConstraints menuPainContraint = new ColumnConstraints();
        menuPainContraint.setHalignment(HPos.LEFT);
        menuPainContraint.setMinWidth(0);
        menuPainContraint.setMaxWidth(0);
        this.appPane.getColumnConstraints().addAll(menuPainContraint, new ColumnConstraints());

        GridPane leftBarPane = new GridPane();
        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);

        Separator rightSeparator = new Separator();
        GridPane.setVgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHalignment(rightSeparator, HPos.RIGHT);
        rightSeparator.setOrientation(Orientation.VERTICAL);
        rightSeparator.setTranslateY(1);
        rightSeparator.setTranslateX(4);
        rightSeparator.setMinWidth(2);
        rightSeparator.setMaxWidth(2);
        rightSeparator.setOpacity(0.30D);

        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setValignment(topPanel, VPos.TOP);
        topPanel.setMinWidth(950);
        topPanel.setMinHeight(340);
        topPanel.setMaxHeight(340);
        topPanel.setTranslateX(50);

        centerPane.getChildren().add(topPanel);

        this.appPane.add(leftBarPane, 1, 0);
        this.appPane.add(this.centerPane, 2, 0);
        GridPane.setVgrow(centerPane, Priority.ALWAYS);
        GridPane.setHgrow(centerPane, Priority.ALWAYS);

        this.layout.getChildren().add(appPane);

        GridPane bottomGridPane = new GridPane();
        GridPane.setVgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHalignment(bottomGridPane, HPos.CENTER);
        GridPane.setValignment(bottomGridPane, VPos.BOTTOM);
        bottomGridPane.setTranslateY(-5);
        bottomGridPane.setMinHeight(40);
        bottomGridPane.setMaxHeight(40);
        bottomGridPane.setMinWidth(300);
        bottomGridPane.setMaxWidth(5000);

        GridPane topGridPane = new GridPane();
        GridPane.setVgrow(topGridPane, Priority.ALWAYS);
        GridPane.setHgrow(topGridPane, Priority.ALWAYS);
        GridPane.setHalignment(topGridPane, HPos.CENTER);
        GridPane.setValignment(topGridPane, VPos.TOP);
        topGridPane.setTranslateY(20);
        topGridPane.setMinHeight(40);
        topGridPane.setMaxHeight(40);
        topGridPane.setMinWidth(300);
        topGridPane.setMaxWidth(5000);

        showPlayerBar(bottomGridPane);
        showServerList(topGridPane, topPanel);
        leftBarPane.getChildren().addAll(rightSeparator, bottomGridPane, topGridPane);

    }

    private void showSkyblockMenu(GridPane pane) {
        showedMenu = "skyblock";
        this.onShow();

        backgroundImage.setStyle("-fx-background-image: url(images/background-skyblock.jpg);");

        ImageView imgView = new ImageView();
        Image img = new Image("images/TempoSky.png");
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(100d);

        GridPane.setHgrow(imgView, Priority.ALWAYS);
        GridPane.setVgrow(imgView, Priority.ALWAYS);
        GridPane.setValignment(imgView, VPos.TOP);
        imgView.setTranslateY(20);
        imgView.setTranslateX(-120);
        setCenterH(imgView);
        imgView.setPreserveRatio(true);

        Label title = new Label("SkyBlock");
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(title, VPos.TOP);
        setCenterH(title);
        title.setStyle("-fx-font-size: 50px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        title.setTranslateY(30);
        title.setTranslateX(50);

        Button playButton = new Button("Jouer");
        GridPane.setVgrow(playButton, Priority.ALWAYS);
        GridPane.setHgrow(playButton, Priority.ALWAYS);
        GridPane.setValignment(playButton, VPos.BOTTOM);
        GridPane.setHalignment(playButton, HPos.CENTER);
        playButton.setTranslateY(200);
        playButton.setMinWidth(140);
        playButton.setMaxHeight(40);
        playButton.setStyle("-fx-background-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");
        playButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        playButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        playButton.setOnMouseClicked(e -> {
            playButton.setDisable(true);
            this.play(pane, "skyblock");
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.BOTTOM);
        GridPane.setHalignment(settingsButton, HPos.CENTER);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        settingsButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color:" + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        settingsButton.setTranslateX(88);
        settingsButton.setTranslateY(194);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e -> {
            if (showedMenu == "skyblock" && this.isDownloading) {
                return;
            }
            topPanel.getChildren().clear();
            showSettingsMenu(pane);
        });

        progressBar.setStyle("-fx-background-color: rgb(184, 65, 45);");
        progressBar.setMinWidth(450);
        setBottom(progressBar);
        setCenterH(progressBar);
        progressBar.setTranslateY(225);

        stepLabel.setStyle("-fx-text-fill: rgba(255, 255, 255);");
        stepLabel.setMinWidth(450);
        setBottom(stepLabel);
        setCenterH(stepLabel);
        stepLabel.setTranslateY(242);

        pane.getChildren().addAll(title, imgView, playButton, settingsButton);
    }

    private void showSurvivalMenu(GridPane pane) {
        showedMenu = "survival";
        this.onShow();

        backgroundImage.setStyle("-fx-background-image: url(images/background2.jpg);");

        ImageView imgView = new ImageView();
        Image img = new Image("images/TempoSurvival.png");
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(100d);

        GridPane.setHgrow(imgView, Priority.ALWAYS);
        GridPane.setVgrow(imgView, Priority.ALWAYS);
        GridPane.setValignment(imgView, VPos.TOP);
        imgView.setTranslateY(20);
        imgView.setTranslateX(-105);
        setCenterH(imgView);
        imgView.setPreserveRatio(true);

        Label title = new Label("Survie");
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(title, VPos.TOP);
        setCenterH(title);
        title.setStyle("-fx-font-size: 50px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        title.setTranslateY(30);
        title.setTranslateX(35);

        Button playButton = new Button("Jouer");
        GridPane.setVgrow(playButton, Priority.ALWAYS);
        GridPane.setHgrow(playButton, Priority.ALWAYS);
        GridPane.setValignment(playButton, VPos.BOTTOM);
        GridPane.setHalignment(playButton, HPos.CENTER);
        playButton.setTranslateY(200);
        playButton.setMinWidth(140);
        playButton.setMaxHeight(40);
        playButton.setStyle("-fx-background-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");
        playButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        playButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        playButton.setOnMouseClicked(e -> {
            playButton.setDisable(true);
            this.play(pane, "survival");
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.BOTTOM);
        GridPane.setHalignment(settingsButton, HPos.CENTER);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        settingsButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        settingsButton.setTranslateX(88);
        settingsButton.setTranslateY(194);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e -> {
            if (showedMenu == "survival" && this.isDownloading) {
                return;
            }
            topPanel.getChildren().clear();
            showSettingsMenu(pane);
        });

        progressBar.setStyle("-fx-background-color: rgb(184, 65, 45);");
        progressBar.setMinWidth(450);
        setBottom(progressBar);
        setCenterH(progressBar);
        progressBar.setTranslateY(225);

        stepLabel.setStyle("-fx-text-fill: rgba(255, 255, 255);");
        stepLabel.setMinWidth(450);
        setBottom(stepLabel);
        setCenterH(stepLabel);
        stepLabel.setTranslateY(242);

        pane.getChildren().addAll(title, imgView, playButton, settingsButton);
    }

    private void showApocalypseMenu(GridPane pane) {
        showedMenu = "apocalypse";
        this.onShow();

        backgroundImage.setStyle("-fx-background-image: url(images/background3.jpg);");

        ImageView imgView = new ImageView();
        Image img = new Image("images/TempoApocalypse.png");
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(100d);

        GridPane.setHgrow(imgView, Priority.ALWAYS);
        GridPane.setVgrow(imgView, Priority.ALWAYS);
        GridPane.setValignment(imgView, VPos.TOP);
        imgView.setTranslateY(20);
        imgView.setTranslateX(-135);
        setCenterH(imgView);
        imgView.setPreserveRatio(true);

        Label title = new Label("Apocalypse");
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(title, VPos.TOP);
        setCenterH(title);
        title.setStyle("-fx-font-size: 50px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        title.setTranslateY(30);
        title.setTranslateX(65);

        Button playButton = new Button("Jouer");
        GridPane.setVgrow(playButton, Priority.ALWAYS);
        GridPane.setHgrow(playButton, Priority.ALWAYS);
        GridPane.setValignment(playButton, VPos.BOTTOM);
        GridPane.setHalignment(playButton, HPos.CENTER);
        playButton.setTranslateY(200);
        playButton.setMinWidth(140);
        playButton.setMaxHeight(40);
        playButton.setStyle("-fx-background-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");
        playButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        playButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        playButton.setOnMouseClicked(e -> {
            playButton.setDisable(true);
            this.play(pane, "apocalypse");
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.BOTTOM);
        GridPane.setHalignment(settingsButton, HPos.CENTER);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        settingsButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        settingsButton.setTranslateX(88);
        settingsButton.setTranslateY(194);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e -> {
            if (showedMenu == "apocalypse" && this.isDownloading) {
                return;
            }
            topPanel.getChildren().clear();
            showSettingsMenu(pane);
        });

        progressBar.setStyle("-fx-background-color: rgb(184, 65, 45);");
        progressBar.setMinWidth(450);
        setBottom(progressBar);
        setCenterH(progressBar);
        progressBar.setTranslateY(225);

        stepLabel.setStyle("-fx-text-fill: rgba(255, 255, 255);");
        stepLabel.setMinWidth(450);
        setBottom(stepLabel);
        setCenterH(stepLabel);
        stepLabel.setTranslateY(242);

        pane.getChildren().addAll(title, imgView, playButton, settingsButton);
    }

    private void showOneblockMenu(GridPane pane) {
        showedMenu = "oneblock";
        this.onShow();

        ImageView imgView = new ImageView();
        Image img = new Image("images/TempoOneblock.png");
        imgView.setImage(img);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(100d);

        GridPane.setHgrow(imgView, Priority.ALWAYS);
        GridPane.setVgrow(imgView, Priority.ALWAYS);
        GridPane.setValignment(imgView, VPos.TOP);
        imgView.setTranslateY(20);
        imgView.setTranslateX(-130);
        setCenterH(imgView);
        imgView.setPreserveRatio(true);

        Label title = new Label("OneBlock");
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(title, VPos.TOP);
        setCenterH(title);
        title.setStyle("-fx-font-size: 50px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        title.setTranslateY(30);
        title.setTranslateX(60);

        Button playButton = new Button("Jouer");
        GridPane.setVgrow(playButton, Priority.ALWAYS);
        GridPane.setHgrow(playButton, Priority.ALWAYS);
        GridPane.setValignment(playButton, VPos.BOTTOM);
        GridPane.setHalignment(playButton, HPos.CENTER);
        playButton.setTranslateY(200);
        playButton.setMinWidth(140);
        playButton.setMaxHeight(40);
        playButton.setStyle("-fx-background-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");
        playButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        playButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        playButton.setOnMouseClicked(e -> {
            playButton.setDisable(true);
            this.play(pane, "oneblock");
        });

        Button settingsButton = new Button();
        GridPane.setVgrow(settingsButton, Priority.ALWAYS);
        GridPane.setHgrow(settingsButton, Priority.ALWAYS);
        GridPane.setValignment(settingsButton, VPos.BOTTOM);
        GridPane.setHalignment(settingsButton, HPos.CENTER);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("18px");
        settingsIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        settingsButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        settingsButton.setTranslateX(88);
        settingsButton.setTranslateY(194);
        settingsButton.setMinWidth(26);
        settingsButton.setMaxWidth(26);
        settingsButton.setMinHeight(26);
        settingsButton.setMaxHeight(26);
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        settingsButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        settingsButton.setOnMouseClicked(e -> {
            if (showedMenu == "oneblock" && this.isDownloading) {
                return;
            }
            topPanel.getChildren().clear();
            showSettingsMenu(pane);
        });

        progressBar.setStyle("-fx-background-color: rgb(184, 65, 45);");
        progressBar.setMinWidth(450);
        setBottom(progressBar);
        setCenterH(progressBar);
        progressBar.setTranslateY(225);

        stepLabel.setStyle("-fx-text-fill: rgba(255, 255, 255);");
        stepLabel.setMinWidth(450);
        setBottom(stepLabel);
        setCenterH(stepLabel);
        stepLabel.setTranslateY(242);

        pane.getChildren().addAll(title, imgView, playButton, settingsButton);
    }

    private void showSettingsMenu(GridPane pane) {
        showedMenu = "settingsMenu";
        this.onShow();

        Label Title = new Label("Paramètre du Launcher");
        GridPane.setVgrow(Title, Priority.ALWAYS);
        GridPane.setHgrow(Title, Priority.ALWAYS);
        GridPane.setValignment(Title, VPos.TOP);
        Title.setStyle("-fx-font-size: 26px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        Title.setTranslateY(20);

        Label ramLabel = new Label("Mémoire ram");
        GridPane.setVgrow(ramLabel, Priority.ALWAYS);
        GridPane.setHgrow(ramLabel, Priority.ALWAYS);
        GridPane.setValignment(ramLabel, VPos.TOP);
        ramLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff;");
        ramLabel.setTranslateY(70);

        //RAM SELECTIOR
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("ram-selector");
        for (int i = 512; i <= Math.ceil(memory.getTotal() / Math.pow(1024, 2)); i += 512) {
            comboBox.getItems().add(i / 1024.0 + " Go");
        }

        int val = 1024;
        try {
            if (saver.get("maxRam") != null) {
                val = Integer.parseInt(saver.get("maxRam"));
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException error) {
            saver.set("maxRam", String.valueOf(val));
            saver.save();
        }

        if (comboBox.getItems().contains(val / 1024.0 + " Go")) {
            comboBox.setValue(val / 1024.0 + " Go");
        } else {
            comboBox.setValue("1.0 Go");
        }

        GridPane.setVgrow(comboBox, Priority.ALWAYS);
        GridPane.setHgrow(comboBox, Priority.ALWAYS);
        GridPane.setValignment(comboBox, VPos.TOP);
        comboBox.getStyleClass().add("ramComboBox");
        comboBox.setStyle("-fx-background-color: rgba(184, 65, 45, 0.0); -fx-border-color: #B8412D; -fx-border-radius: 2px;");
        comboBox.setTranslateY(100);

        //Save Button
        Button saveButton = new Button();
        GridPane.setVgrow(saveButton, Priority.ALWAYS);
        GridPane.setHgrow(saveButton, Priority.ALWAYS);
        GridPane.setValignment(saveButton, VPos.TOP);
        GridPane.setHalignment(saveButton, HPos.LEFT);
        MaterialDesignIconView saveIconView = new MaterialDesignIconView(MaterialDesignIcon.CONTENT_SAVE);
        saveIconView.setFill(Constants.LAUNCHER_COLOR_RGB);
        saveIconView.setSize("15");
        saveButton.setStyle("-fx-background-color: rgba(184, 65, 45, 0.0); -fx-border-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        saveButton.setTranslateX(100);
        saveButton.setTranslateY(99.5d);
        saveButton.setMinWidth(29);
        saveButton.setMaxWidth(29);
        saveButton.setMinHeight(29);
        saveButton.setMaxHeight(29);
        saveButton.setGraphic(saveIconView);
        saveButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        saveButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        saveButton.setOnMouseClicked(e -> {
            double _val = Double.parseDouble(comboBox.getValue().replace(" Go", ""));
            _val *= 1024;
            saver.set("maxRam", String.valueOf((int) _val));
            saver.save();
            topPanel.getChildren().clear();
            showSkyblockMenu(pane);
        });

        pane.getChildren().addAll(Title, ramLabel, comboBox, saveButton);
    }

    private void showPlayerBar(GridPane pane) {

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
        logoutButton.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        logoutButton.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        logoutButton.setOnMouseClicked(e -> {
            if (showedMenu == "homeMenu" && this.isDownloading) {
                return;
            }

            saver.remove("accesToken");
            saver.remove("clientToken");
            saver.remove("msAccessToken");
            saver.remove("msRefreshToken");
            saver.remove("offline-username");
            saver.save();
            Launcher.getInstance().setAuthInfos(null);
            this.panelManager.showPanel(new Login());
        });

        pane.getChildren().addAll(blueLeftSeparator, avatarView, playerNameLabel, logoutButton);
    }

    public void showServerList(GridPane pane, GridPane topPanel) {

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
            if (saver.get("serverSelector").contains("skyblock")) { serverLeftSeparator.setTranslateY(0); showSkyblockMenu(topPanel); }
            else if (saver.get("serverSelector").contains("survival")) { serverLeftSeparator.setTranslateY(65); showSurvivalMenu(topPanel); }
            else if (saver.get("serverSelector").contains("apocalypse")) { serverLeftSeparator.setTranslateY(130); showApocalypseMenu(topPanel);}
            else if (saver.get("serverSelector").contains("oneblock")) { serverLeftSeparator.setTranslateY(195); showOneblockMenu(topPanel);}
            else serverLeftSeparator.setTranslateY(0);
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            serverLeftSeparator.setTranslateY(0);
            showSkyblockMenu(topPanel);
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
        skyblockLabel.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        skyblockLabel.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        skyblockLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(0);
            topPanel.getChildren().clear();
            showSkyblockMenu(topPanel);
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
        survivalLabel.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        survivalLabel.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        survivalLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(65);
            topPanel.getChildren().clear();
            showSurvivalMenu(topPanel);
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
        apocalypseLabel.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        apocalypseLabel.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        apocalypseLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(130);
            saver.set("serverSelector", "apocalypse");
            topPanel.getChildren().clear();
            showApocalypseMenu(topPanel);
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
        oneblockLabel.setOnMouseEntered(e -> this.appPane.setCursor(Cursor.HAND));
        oneblockLabel.setOnMouseExited(e -> this.appPane.setCursor(Cursor.DEFAULT));
        oneblockLabel.setOnMouseClicked(e -> {
            serverLeftSeparator.setTranslateY(195);
            saver.set("serverSelector", "oneblock");
            topPanel.getChildren().clear();
            showOneblockMenu(topPanel);
            saver.save();
        });



        pane.getChildren().addAll(serverLeftSeparator, skyblockView, skyblockLabel,
                survivalView, survivalLabel,
                apocalypseView, apocalypseLabel,
                oneblockView, oneblockLabel);

    }

    // Update and Start game
    public void play(GridPane pane, String serverName) {
        new MinecraftLaunching(pane, progressBar, stepLabel, isDownloading, panelManager, saver, serverName);
    }

    public String avatarUrl() {
        if (saver.get("offline-username") != null) {
            return Constants.AVATAR_URL + "MHF_STEVE";
        } else {
            return Constants.AVATAR_URL + Launcher.getInstance().getAuthInfos().getUsername();
        }
    }
}