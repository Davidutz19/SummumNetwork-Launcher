package fr.summumgames.launcher.ui.panel;

import javafx.scene.Node;

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

public interface IMovable {
    void setLeft(Node node);
    void setRight(Node node);
    void setTop(Node node);
    void setBottom(Node node);
    void setBaseLine(Node node);
    void setCenterH(Node node);
    void setCenterV(Node node);
}
