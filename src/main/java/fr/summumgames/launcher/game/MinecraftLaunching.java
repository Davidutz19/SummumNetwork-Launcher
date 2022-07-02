package fr.summumgames.launcher.game;

import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.download.DownloadList;
import fr.flowarg.flowupdater.download.IProgressCallback;
import fr.flowarg.flowupdater.download.Step;
import fr.flowarg.flowupdater.download.json.CurseFileInfo;
import fr.flowarg.flowupdater.download.json.Mod;
import fr.flowarg.flowupdater.download.json.OptiFineInfo;
import fr.flowarg.flowupdater.utils.ModFileDeleter;
import fr.flowarg.flowupdater.utils.UpdaterOptions;
import fr.flowarg.flowupdater.versions.AbstractForgeVersion;
import fr.flowarg.flowupdater.versions.ForgeVersionBuilder;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.flowarg.openlauncherlib.NewForgeVersionDiscriminator;
import fr.summumgames.launcher.Launcher;
import fr.summumgames.launcher.enums.Urls;
import fr.summumgames.launcher.ui.PanelManager;
import fr.summumgames.launcher.utils.Constants;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

public class MinecraftLaunching {

    public static MinecraftLaunching instance;
    GridPane pane;
    ProgressBar progressBar;
    Label stepLabel;
    private AtomicBoolean isDownloading = new AtomicBoolean(false);
    PanelManager panelManager = PanelManager.instance;
    Saver saver;
    String serverName;

    public Thread t = new Thread(this::update);

    MinecraftInfos minecraftInfos = new MinecraftInfos();

    public MinecraftLaunching(GridPane pane, ProgressBar progressBar, Label stepLabel, Saver saver, String serverName) {
        instance = this;
        this.pane = pane;
        this.progressBar = progressBar;
        this.stepLabel = stepLabel;
        this.saver = saver;
        this.serverName = serverName;
    }

    public void launchGame() {
        updateMinecraftInfos(serverName);
    }

    public void updateMinecraftInfos(String serverName) {
        if (serverName.contains("skyblock")) {
            minecraftInfos.GAME_VERSION = "1.16.5";
            minecraftInfos.FORGE_VERSION_TYPE = ForgeVersionBuilder.ForgeVersionType.NEW;
            minecraftInfos.FORGE_VERSION = "1.16.5-36.2.34";
            minecraftInfos.OPTIFINE_VERSION = "1.16.5_HD_U_G8";
            minecraftInfos.OLL_GAME_TYPE = GameType.V1_13_HIGHER_FORGE;
            minecraftInfos.OLL_FORGE_DISCRIMINATOR = new NewForgeVersionDiscriminator(
                    "36.2.34",
                    minecraftInfos.GAME_VERSION,
                    "20210115.111550"
            );
            minecraftInfos.CURSE_MODS_LIST_URL = Urls.SKYBLOCK_MAIN_MODS.getUrl();
            minecraftInfos.MODS_LIST_URL = Urls.SKYBLOCK_MAIN_MODS.getUrl();
            play(pane);
        }
        else if (serverName.contains("survival")) {
            minecraftInfos.GAME_VERSION = "1.16.5";
            minecraftInfos.FORGE_VERSION_TYPE = ForgeVersionBuilder.ForgeVersionType.NEW;
            minecraftInfos.FORGE_VERSION = "1.16.5-36.2.2";
            minecraftInfos.OPTIFINE_VERSION = "1.16.5_HD_U_G8";
            minecraftInfos.OLL_GAME_TYPE = GameType.V1_13_HIGHER_FORGE;
            minecraftInfos.OLL_FORGE_DISCRIMINATOR = new NewForgeVersionDiscriminator(
                    "36.2.2",
                    minecraftInfos.GAME_VERSION,
                    "20210115.111550"
            );
            play(pane);
        }
        else if (serverName.contains("apocalypse")) {
            minecraftInfos.GAME_VERSION = "1.12.2";
            minecraftInfos.FORGE_VERSION_TYPE = ForgeVersionBuilder.ForgeVersionType.NEW;
            minecraftInfos.FORGE_VERSION = "1.12.2-14.23.5.2860";
            minecraftInfos.OPTIFINE_VERSION = "1.12.2_HD_U_G5";
            minecraftInfos.OLL_GAME_TYPE = GameType.V1_8_HIGHER;
            play(pane);
        }
        else if (serverName.contains("oneblock")) {
            minecraftInfos.GAME_VERSION = "1.16.5";
            minecraftInfos.FORGE_VERSION_TYPE = ForgeVersionBuilder.ForgeVersionType.NEW;
            minecraftInfos.FORGE_VERSION = "1.16.5-36.2.2";
            minecraftInfos.OPTIFINE_VERSION = "1.16.5_HD_U_G8";
            minecraftInfos.OLL_GAME_TYPE = GameType.V1_13_HIGHER_FORGE;
            minecraftInfos.OLL_FORGE_DISCRIMINATOR = new NewForgeVersionDiscriminator(
                    "36.2.2",
                    minecraftInfos.GAME_VERSION,
                    "20210115.111550"
            );
            play(pane);
        }
    }

    public void play(GridPane pane) {
        setIsDownloading(true);
        setProgress(0, 0);
        pane.getChildren().addAll(progressBar, stepLabel);

        Platform.runLater(() -> t.start());
    }

    public void update() {

        IProgressCallback callback = new IProgressCallback() {
            private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
            private String stepTxt = "";
            private String percentTxt = "0.0%";

            @Override
            public void step(Step step) {
                Platform.runLater(() -> {
                    stepTxt = StepInfo.valueOf(step.name()).getDetails();
                    setStatus(String.format("%s, (%s)", stepTxt, percentTxt));
                });
            }

            @Override
            public void update(DownloadList.DownloadInfo downloaded) {
                Platform.runLater(() -> {
                    percentTxt = decimalFormat.format(downloaded.getDownloadedFiles() * 100d / downloaded.getTotalToDownloadFiles()) + "%";
                    setStatus(String.format("%s, (%s)", stepTxt, percentTxt));
                    setProgress(downloaded.getDownloadedFiles(), downloaded.getTotalToDownloadFiles());
                });
            }
        };
        try {
            final VanillaVersion vanillaVersion = new VanillaVersion.VanillaVersionBuilder()
                    .withName(minecraftInfos.GAME_VERSION)
                    .build();
            final UpdaterOptions options = new UpdaterOptions.UpdaterOptionsBuilder()
                    .build();

            List<CurseFileInfo> curseMods = null;
            List<Mod> mods = null;

            if (serverName.contains("skyblock")) {
                curseMods = CurseFileInfo.getFilesFromJson(minecraftInfos.CURSE_MODS_LIST_URL);
                mods = Mod.getModsFromJson(minecraftInfos.MODS_LIST_URL);

                //Optional Mod
                if (saver.get("skyblockOptionalReplaymod").equals("true")) {
                    for (Mod m : Mod.getModsFromJson(Urls.SKYBLOCK_REPLAYMOD.getUrl())) {
                        mods.add(m);
                    }
                }
                if (saver.get("skyblockOptionalLitematica").equals("true")) {
                    for (Mod m : Mod.getModsFromJson(Urls.SKYBLOCK_LITEMATICA.getUrl())) {
                        mods.add(m);
                    }
                }

            }

            final AbstractForgeVersion forge = new ForgeVersionBuilder(minecraftInfos.FORGE_VERSION_TYPE)
                    .withOptiFine(new OptiFineInfo(minecraftInfos.OPTIFINE_VERSION, false))
                    .withForgeVersion(minecraftInfos.FORGE_VERSION)
                    .withCurseMods(curseMods)
                    .withMods(mods)
                    .withFileDeleter(new ModFileDeleter(true))
                    .build();

            final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder()
                    .withVanillaVersion(vanillaVersion)
                    .withModLoaderVersion(forge)
                    .withLogger(Launcher.getInstance().getLogger())
                    .withProgressCallback(callback)
                    .withUpdaterOptions(options)
                    .build();

            if (serverName.contains("skyblock")) updater.update(Launcher.getInstance().getSkyblockDir());
            else if (serverName.contains("survival")) updater.update(Launcher.getInstance().getSurvivalDir());
            else if (serverName.contains("apocalypse")) updater.update(Launcher.getInstance().getApocalypseDir());
            else if (serverName.contains("oneblock")) updater.update(Launcher.getInstance().getOneblockDir());
            else {Platform.exit(); System.exit(1);}
            
            this.startGame(updater.getVanillaVersion().getName());
        } catch (Exception exception) {
            Launcher.getInstance().getLogger().err(exception.toString());
            exception.printStackTrace();
            Platform.runLater(() -> panelManager.getStage().show());
            Platform.runLater(() -> this.stepLabel.setText("Impossible de récuperer les données de jeu"));
        }
    }

    public void startGame(String gameVersion) {
        
        Path gameFolder = null;
        GameInfos infos;

        if (serverName.contains("skyblock")) gameFolder = Launcher.getInstance().getSkyblockDir();
        else if (serverName.contains("survival")) gameFolder = Launcher.getInstance().getSurvivalDir();
        else if (serverName.contains("apocalypse")) gameFolder = Launcher.getInstance().getApocalypseDir();
        else if (serverName.contains("oneblock")) gameFolder = Launcher.getInstance().getOneblockDir();
        else {Platform.exit(); System.exit(1);}

        if (serverName.contains("skyblock")) {
             infos = new GameInfos(
                    Constants.LAUNCHER_NAME,
                    gameFolder,
                    new GameVersion(gameVersion, minecraftInfos.OLL_GAME_TYPE.setNFVD(minecraftInfos.OLL_FORGE_DISCRIMINATOR)),
                    new GameTweak[]{}
            );
        }else if (serverName.contains("survival")) {
            System.out.println(minecraftInfos.OLL_FORGE_DISCRIMINATOR.toString());
            infos = new GameInfos(
                    Constants.LAUNCHER_NAME,
                    gameFolder,
                    new GameVersion(gameVersion, minecraftInfos.OLL_GAME_TYPE.setNFVD(minecraftInfos.OLL_FORGE_DISCRIMINATOR)),
                    new GameTweak[]{}
            );
        }else if (serverName.contains("apocalypse")) {
            infos = new GameInfos(
                    Constants.LAUNCHER_NAME,
                    gameFolder,
                    new GameVersion(gameVersion, minecraftInfos.OLL_GAME_TYPE),
                    new GameTweak[]{GameTweak.FORGE}
            );
        }else if (serverName.contains("oneblock")) {
            infos = new GameInfos(
                    Constants.LAUNCHER_NAME,
                    gameFolder,
                    new GameVersion(gameVersion, minecraftInfos.OLL_GAME_TYPE.setNFVD(minecraftInfos.OLL_FORGE_DISCRIMINATOR)),
                    new GameTweak[]{}
            );
        }else {
             infos = new GameInfos(
                    Constants.LAUNCHER_NAME,
                    gameFolder,
                    new GameVersion(gameVersion, minecraftInfos.OLL_GAME_TYPE),
                    new GameTweak[]{}
            );
        }
        try {
            ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(infos, GameFolder.FLOW_UPDATER, Launcher.getInstance().getAuthInfos());
            profile.getVmArgs().add(this.getRamArgsFromSaver());
            ExternalLauncher launcher = new ExternalLauncher(profile);

            Platform.runLater(() -> panelManager.getStage().hide());

            Process p = launcher.launch();

            Platform.runLater(() -> {
                try {
                    p.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
            Launcher.getInstance().getLogger().err(exception.toString());
        }
    }

    public String getRamArgsFromSaver() {
        int val = 1024;
        try {
            if (saver.get("maxRam") != null) {
                val = Integer.parseInt(saver.get("maxRam"));
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException error) {
            saver.set("maxRam", String.valueOf(val));
            saver.save();
        }

        return "-Xmx" + val + "M";
    }

    public void setStatus(String status) {
        this.stepLabel.setText(status);
    }

    public void setProgress(double current, double max) {
        this.progressBar.setProgress(current / max);
    }

    public boolean isDownloading() {
        return isDownloading.get();
    }

    public void setIsDownloading(boolean bool) {
        isDownloading.set(bool);
    }

    public Thread getT() {
        return t;
    }

    public enum StepInfo {
        INTEGRATION("Chargement des intégrations..."),
        READ("Récupération des données de jeu..."),
        DL_LIBS("Téléchargement de Minecraft..."),
        DL_ASSETS("Téléchargement des données de jeu..."),
        EXTRACT_NATIVES("Extraction des autres donnés..."),
        MOD_LOADER("Installation du Mod Loader..."),
        MODS("Téléchargement des mise à jour..."),
        EXTERNAL_FILES("Téléchargement des fichier externes..."),
        POST_EXECUTIONS("Mise à jour terminé..."),
        END("Lancement du Jeu !");

        String details;

        StepInfo(String details) {
            this.details = details;
        }

        public String getDetails() {
            return details;
        }
    }
}
