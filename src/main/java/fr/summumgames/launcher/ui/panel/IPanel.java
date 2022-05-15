package fr.summumgames.launcher.ui.panel;

import fr.summumgames.launcher.ui.PanelManager;
import javafx.scene.layout.GridPane;

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

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStyleSheetPath();
}
