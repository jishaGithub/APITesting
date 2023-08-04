package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

public enum NavigationElementsEnum {
    OVERVIEW("Overview", null),
    GETTING_STARTED("Getting started", null),
    PROJECT_STRUCTURE("Project structure", null),
    CONFIGURATION("Configuration", null),
    EXECUTION("Execution", null),
    AUTOMATION("Automation", new String[] {"Web", "Mobile", "API", "Windows"}),
    ADVANCED("Advanced", new String[] {
            "Database",
            "DataProvider",
            "Driver",
            "Mobile",
            "Localization",
            "Program flow",
            "Proxy",
            "Screenshot",
            "Security"}),
    INTEGRATION("Integration", new String[] {"Zebrunner"}),
    CUCUMBER("Cucumber", null),
    CONTRIBUTION("Contribution", null),
    MIGRATION_GUIDE("Migration Guide", null);

    private final String title;
    private final String[] nestedTitles;

    NavigationElementsEnum(String title, String[] nestedTitles) {
        this.title = title;
        this.nestedTitles = nestedTitles;
    }

    public String getTitle() {
        return title;
    }
    public String[] getNestedTitles() {
        return nestedTitles;
    }
}
