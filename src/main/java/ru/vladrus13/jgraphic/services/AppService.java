package ru.vladrus13.jgraphic.services;

import javax.swing.*;

/**
 * @author vladrus13 on 22.03.2021
 **/
public class AppService {
    /**
     * Jpanel object
     */
    private static JPanel app;

    /**
     * @return app
     */
    public static JPanel getApp() {
        return app;
    }

    /**
     * @param app app
     */
    public static void setApp(JPanel app) {
        AppService.app = app;
    }
}
