package fr.summumgames.launcher.enums;

import fr.summumgames.launcher.Launcher;

public enum ModsUrls {

    SKYBLOCK_MODS(Launcher.getInstance().getLauncherAPI().getPackageInfo().getString("skyblock") + "mods-list"),
    SKYBLOCK_REPLAYMOD(Launcher.getInstance().getLauncherAPI().getPackageInfo().getString("skyblock") + "replaymod"),
    SKYBLOCK_LITEMATICA(Launcher.getInstance().getLauncherAPI().getPackageInfo().getString("skyblock") + "litematica");

    String url;

    ModsUrls(String url) { this.url = url; }

    public String getUrl() {
        return url;
    }
}
