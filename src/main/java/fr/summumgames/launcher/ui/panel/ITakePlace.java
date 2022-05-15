package fr.summumgames.launcher.ui.panel;

import javafx.scene.Node;
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

public interface ITakePlace {
    default void setCanTakeAllSize(Node node) {
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setVgrow(node, Priority.ALWAYS);
    }

    default void setCanTakeAllWidth(Node... nodes) {
        for (Node n : nodes) {
            GridPane.setHgrow(n, Priority.ALWAYS);
        }
    }
}
