package net.frozenorb.data;

public enum GameType {
    HCTEAMS("HCTeams"),
    PRACTICE("Practice");

    String name;

    GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
