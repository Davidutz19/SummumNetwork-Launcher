package fr.summumgames.launcher.ui.panels.partials;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.summumgames.launcher.ui.panel.Panel;
import fr.summumgames.launcher.utils.Constants;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
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

public class ImageSlider extends Panel {

    public void showImageSlider(GridPane pane, ArrayList<Image> content) {

        ImageView imgView = new ImageView();
        imgView.setImage(content.get(0));
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(230d);
        imgView.setPreserveRatio(true);
        imgView.setTranslateX(190);
        imgView.setTranslateY(100);
        setCenterH(imgView);
        setTop(imgView);

        AtomicInteger imageDisplayed = new AtomicInteger(0);

        Button nextItem = new Button();
        setTop(nextItem);
        setCenterH(nextItem);
        MaterialDesignIconView nextItemIcon = new MaterialDesignIconView(MaterialDesignIcon.ARROW_RIGHT_BOLD);
        nextItemIcon.setSize("18px");
        nextItemIcon.setFill(Constants.LAUNCHER_COLOR_RGB);
        nextItem.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0); -fx-border-color:" + Constants.LAUNCHER_COLOR + "; -fx-border-radius: 2px;");
        nextItem.setMinWidth(26);
        nextItem.setMaxWidth(26);
        nextItem.setMinHeight(26);
        nextItem.setMaxHeight(26);
        nextItem.setTranslateX((imgView.getTranslateX() + 180));
        nextItem.setTranslateY((imgView.getTranslateY() + 100));
        nextItem.setGraphic(nextItemIcon);
        nextItem.setOnMouseEntered(e -> pane.setCursor(Cursor.HAND));
        nextItem.setOnMouseExited(e -> pane.setCursor(Cursor.DEFAULT));
        nextItem.setOnMouseClicked(e -> {
            imageDisplayed.incrementAndGet();

            if (imageDisplayed.get() == (content.size())) imageDisplayed.set(0);

            imgView.setImage(content.get(imageDisplayed.get()));
        });

        pane.getChildren().addAll(imgView, nextItem);
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
