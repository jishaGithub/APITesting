package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

public enum NavigationItem {

    OVERVIEW("Overview"),
    GETTING_STARTED("Getting started"),
    PROJECT_STRUCTURE("Project structure"),
    CONFIGURATION("Configuration"),
    EXECUTION("Execution"),
    CUCUMBER("Cucumber"),
    CONTRIBUTION("Contribution"),
    MIGRATION_GUIDE("Migration Guide");

    private final String title;

    NavigationItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
