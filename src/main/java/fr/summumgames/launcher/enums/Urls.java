package fr.summumgames.launcher.enums;

public enum Urls {
    SKYBLOCK_MAIN_MODS("https://dev.davidutz.ga/SummumNetwork/Skyblock/mods-list-skyblock.json"),
    SKYBLOCK_REPLAYMOD("https://dev.davidutz.ga/SummumNetwork/Skyblock/replaymod.json"),

    SKYBLOCK_LITEMATICA("https://dev.davidutz.ga/SummumNetwork/Skyblock/litematica.json");

    String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
