package fr.summumgames.launcher.ui.panel;

import fr.summumgames.launcher.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStyleSheetPath();
}
