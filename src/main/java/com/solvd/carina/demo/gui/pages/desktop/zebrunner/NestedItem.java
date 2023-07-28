package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

public enum NestedItem {
    WEB("Web"),
    MOBILE("Mobile"),
    API("API"),
    WINDOWS("Windows"),
    DATABASE("Database"),
    DATA_PROVIDER("DataProvider"),
    DRIVER("Driver"),
    MOBILE_NESTED("Mobile"),
    LOCALIZATION("Localization"),
    PROGRAM_FLOW("Program flow"),
    PROXY("Proxy"),
    SCREENSHOT("Screenshot"),
    SECURITY("Security"),
    ZEBRUNNER("Zebrunner");

    private final String title;

    NestedItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
