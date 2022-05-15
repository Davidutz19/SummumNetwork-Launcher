package fr.summumgames.launcher;

import javafx.application.Application;

import javax.swing.*;

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

public class Main {
    public static void main(String[] args)
    {
        try {
            Class.forName("javafx.application.Application");
            Application.launch(Launcher.class, args);
        } catch (ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur:\n" + e.getMessage() + "\nNot found",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
