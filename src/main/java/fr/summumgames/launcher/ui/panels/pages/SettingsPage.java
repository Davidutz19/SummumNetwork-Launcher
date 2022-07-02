package fr.summumgames.launcher.ui.panels.pages;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.utils.Constants;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

public class SettingsPage extends Panel {

    GridPane pane;
    GridPane appPane;
    Saver saver = Launcher.instance.getSaver();

    public SettingsPage(GridPane centerPane, GridPane appPane) {
        this.pane = centerPane;
        this.appPane = appPane;
    }

    public void showSettingsMenu() {
        App.instance.setShowedMenu("settingsMenu");
        App.instance.onShow();

        Label title = new Label("Paramètre du Launcher");
        GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(title, VPos.TOP);
        setLeft(title);
        title.setStyle("-fx-font-size: 34px; -fx-text-fill: #fff; -fx-font-weight: bold;");
        title.setTranslateY(30);
        title.setTranslateX(35);

        Label ramLabel = new Label("Mémoire ram");
        GridPane.setVgrow(ramLabel, Priority.ALWAYS);
        GridPane.setHgrow(ramLabel, Priority.ALWAYS);
        GridPane.setValignment(ramLabel, VPos.TOP);
        setLeft(ramLabel);
        ramLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #fff;");
        ramLabel.setTranslateY(90);
        ramLabel.setTranslateX(35);

        //RAM SELECTIOR
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getStyleClass().add("ram-selector");
        for (int i = 512; i <= Math.ceil(memory.getTotal() / Math.pow(1024, 2)); i += 512) {
            comboBox.getItems().add(i / 1024.0 + " Go");
        }

        int val = 3072;
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
        setLeft(comboBox);
        comboBox.setStyle("-fx-background-color: rgba(184, 65, 45, 0.0); -fx-border-color: #B8412D; -fx-border-radius: 2px;");
        comboBox.setTranslateY(125);
        comboBox.setTranslateX(35);

        // Skyblock Options
        Label skyblockLabel = new Label("Skyblock");
        GridPane.setVgrow(skyblockLabel, Priority.ALWAYS);
        GridPane.setHgrow(skyblockLabel, Priority.ALWAYS);
        GridPane.setValignment(skyblockLabel, VPos.TOP);
        setLeft(skyblockLabel);
        skyblockLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #fff;");
        skyblockLabel.setTranslateY(165);
        skyblockLabel.setTranslateX(35);

        Label skyblockOptionalReplaymodLabel = new Label("Lancer le jeu avec ReplayMod: ");
        GridPane.setVgrow(skyblockOptionalReplaymodLabel, Priority.ALWAYS);
        GridPane.setHgrow(skyblockOptionalReplaymodLabel, Priority.ALWAYS);
        GridPane.setValignment(skyblockOptionalReplaymodLabel, VPos.TOP);
        setLeft(skyblockOptionalReplaymodLabel);
        skyblockOptionalReplaymodLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff;");
        skyblockOptionalReplaymodLabel.setTranslateY(195);
        skyblockOptionalReplaymodLabel.setTranslateX(35);

        CheckBox skyblockOptionalReplaymodCheckBox = new CheckBox();
        GridPane.setVgrow(skyblockOptionalReplaymodCheckBox, Priority.ALWAYS);
        GridPane.setHgrow(skyblockOptionalReplaymodCheckBox, Priority.ALWAYS);
        GridPane.setValignment(skyblockOptionalReplaymodCheckBox, VPos.TOP);
        setLeft(skyblockOptionalReplaymodCheckBox);
        skyblockOptionalReplaymodCheckBox.setStyle("-fx-background-color: rgba(0, 0, 0,0);");
        skyblockOptionalReplaymodCheckBox.getStyleClass().add("check-box");
        skyblockOptionalReplaymodCheckBox.setTranslateY(190);
        skyblockOptionalReplaymodCheckBox.setTranslateX(255);
        skyblockOptionalReplaymodCheckBox.setMinWidth(29);
        skyblockOptionalReplaymodCheckBox.setMaxWidth(29);
        skyblockOptionalReplaymodCheckBox.setMinHeight(29);
        skyblockOptionalReplaymodCheckBox.setMaxHeight(29);

        Label skyblockOptionalLitematicaLabel = new Label("Lancer le jeu avec Litematica (non stable): ");
        GridPane.setVgrow(skyblockOptionalLitematicaLabel, Priority.ALWAYS);
        GridPane.setHgrow(skyblockOptionalLitematicaLabel, Priority.ALWAYS);
        GridPane.setValignment(skyblockOptionalLitematicaLabel, VPos.TOP);
        setLeft(skyblockOptionalLitematicaLabel);
        skyblockOptionalLitematicaLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #fff;");
        skyblockOptionalLitematicaLabel.setTranslateY(220);
        skyblockOptionalLitematicaLabel.setTranslateX(35);

        CheckBox skyblockOptionalLitematicaCheckBox = new CheckBox();
        GridPane.setVgrow(skyblockOptionalLitematicaCheckBox, Priority.ALWAYS);
        GridPane.setHgrow(skyblockOptionalLitematicaCheckBox, Priority.ALWAYS);
        GridPane.setValignment(skyblockOptionalLitematicaCheckBox, VPos.TOP);
        setLeft(skyblockOptionalLitematicaCheckBox);
        skyblockOptionalLitematicaCheckBox.setStyle("-fx-background-color: rgba(0, 0, 0,0);");
        skyblockOptionalLitematicaCheckBox.getStyleClass().add("check-box");
        skyblockOptionalLitematicaCheckBox.setTranslateY(215);
        skyblockOptionalLitematicaCheckBox.setTranslateX(340);
        skyblockOptionalLitematicaCheckBox.setMinWidth(29);
        skyblockOptionalLitematicaCheckBox.setMaxWidth(29);
        skyblockOptionalLitematicaCheckBox.setMinHeight(29);
        skyblockOptionalLitematicaCheckBox.setMaxHeight(29);

        if (saver.get("skyblockOptionalReplaymod") != null) {
            switch (saver.get("skyblockOptionalReplaymod")) {
                case "false":
                    skyblockOptionalReplaymodCheckBox.setSelected(false);
                    break;
                case "true":
                    skyblockOptionalReplaymodCheckBox.setSelected(true);
                    break;
                default:
                    saver.remove("skyblockOptionalReplaymod");
                    break;
            }
        }
        if (saver.get("skyblockOptionalLitematica") != null) {
            switch (saver.get("skyblockOptionalLitematica")) {
                case "false":
                    skyblockOptionalLitematicaCheckBox.setSelected(false);
                    break;
                case "true":
                    skyblockOptionalLitematicaCheckBox.setSelected(true);
                    break;
                default:
                    saver.remove("skyblockOptionalLitematica");
                    break;
            }
        }

        //Save Button
        Button saveButton = new Button();
        GridPane.setVgrow(saveButton, Priority.ALWAYS);
        GridPane.setHgrow(saveButton, Priority.ALWAYS);
        GridPane.setValignment(saveButton, VPos.TOP);
        GridPane.setHalignment(saveButton, HPos.LEFT);
        MaterialDesignIconView saveIconView = new MaterialDesignIconView(MaterialDesignIcon.CONTENT_SAVE);
        saveIconView.setFill(Constants.LAUNCHER_COLOR_RGB);
        saveIconView.setSize("15");
        setLeft(saveButton);
        saveButton.setStyle("-fx-background-color: rgba(184, 65, 45, 0.0); -fx-border-color: " + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        saveButton.setTranslateY(125);
        saveButton.setTranslateX(145);
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
            saver.set("skyblockOptionalReplaymod", String.valueOf(skyblockOptionalReplaymodCheckBox.isSelected()));
            saver.set("skyblockOptionalLitematica", String.valueOf(skyblockOptionalLitematicaCheckBox.isSelected()));
            saver.save();
            pane.getChildren().clear();

            switch (saver.get("serverSelector")) {
                case "skyblock":
                    App.instance.showSkyblockPage();
                    break;
                case "survival":
                    App.instance.showSurvivalPage();
                    break;
                case "apocalypse":
                    App.instance.showApocalypsePage();
                    break;
                case "oneblock":
                    App.instance.showOneblockPage();
                    break;
                default: App.instance.showSkyblockPage();
            }
        });

        pane.getChildren().addAll(title, ramLabel, comboBox,
                skyblockLabel, skyblockOptionalReplaymodLabel, skyblockOptionalReplaymodCheckBox, skyblockOptionalLitematicaLabel, skyblockOptionalLitematicaCheckBox,
                saveButton);
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
