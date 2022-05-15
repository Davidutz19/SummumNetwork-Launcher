package fr.summumgames.launcher.game;

import fr.flowarg.flowupdater.versions.ForgeVersionBuilder;
import fr.flowarg.flowupdater.versions.VersionType;
import fr.flowarg.openlauncherlib.NewForgeVersionDiscriminator;
import fr.theshark34.openlauncherlib.minecraft.GameType;

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

public class MinecraftInfos {

    public String GAME_VERSION = "1.16.5";
    public VersionType VERSION_TYPE = VersionType.FORGE;
    public ForgeVersionBuilder.ForgeVersionType FORGE_VERSION_TYPE = ForgeVersionBuilder.ForgeVersionType.NEW;
    public String FORGE_VERSION = "1.16.5-36.2.2";
    public String OPTIFINE_VERSION = "1.16.5_HD_U_G8";

    public GameType OLL_GAME_TYPE = GameType.V1_13_HIGHER_FORGE;
    public NewForgeVersionDiscriminator OLL_FORGE_DISCRIMINATOR = new NewForgeVersionDiscriminator(
            "36.2.2",
            this.GAME_VERSION,
            "20210115.111550"
    );


    public String CURSE_MODS_LIST_URL = "https://dev.davidutz.ga/SummumNetwork/Skyblock/mods-list-skyblock.json";
    public String MODS_LIST_URL = "https://dev.davidutz.ga/SummumNetwork/Skyblock/mods-list-skyblock.json";

}
