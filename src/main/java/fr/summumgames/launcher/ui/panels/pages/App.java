package fr.summumgames.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.game.MinecraftLaunching;
import fr.summumgames.launcher.ui.PanelManager;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.ui.panels.partials.PlayerBar;
import fr.summumgames.launcher.ui.panels.partials.ServerList;
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

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static App instance;
    protected GridPane centerPane = new GridPane();
    protected GridPane appPane = new GridPane();
    //    private GridPane topPanel = new GridPane();
    protected GridPane backgroundImage = new GridPane();
    protected String showedMenu;
    protected Saver saver = Launcher.instance.getSaver();

    // PAGES
    private SkyblockPage skyblockPage = new SkyblockPage(centerPane, appPane, backgroundImage);
    private SurvivalPage survivalPage = new SurvivalPage(centerPane, appPane, backgroundImage);
    private ApocalypsePage apocalypsePage = new ApocalypsePage(centerPane, appPane, backgroundImage);
    private OneblockPage oneblockPage = new OneblockPage(centerPane, appPane, backgroundImage);
    private SettingsPage settingsPage = new SettingsPage(centerPane, appPane);
    private PlayerBar playerBar = new PlayerBar();
    private ServerList serverList = new ServerList();

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
        instance = this;
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

        this.appPane.add(leftBarPane, 1, 0);
        this.appPane.add(this.centerPane, 2, 0);
        GridPane.setVgrow(centerPane, Priority.ALWAYS);
        GridPane.setHgrow(centerPane, Priority.ALWAYS);
        centerPane.setMinWidth(950);
        centerPane.setMinHeight(340);

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

        this.playerBar.showPlayerBar(bottomGridPane, appPane, skyblockPage, this);
        this.serverList.showServerList(topGridPane, centerPane, appPane);
        leftBarPane.getChildren().addAll(rightSeparator, bottomGridPane, topGridPane);
    }

    public void showSkyblockPage() {
        skyblockPage.showSkyblockMenu();
    }
    public void showSurvivalPage() {
        survivalPage.showSurvivalMenu();
    }
    public void showApocalypsePage() {
        apocalypsePage.showApocalypseMenu();
    }
    public void showOneblockPage() {
        oneblockPage.showOneblockMenu();
    }
    public void showSettingsPage() {
        settingsPage.showSettingsMenu();
    }
    public void setShowedMenu(String showedMenu) {
        this.showedMenu = showedMenu;
    }
}