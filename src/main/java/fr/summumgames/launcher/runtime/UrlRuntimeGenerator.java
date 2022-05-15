package fr.summumgames.launcher.runtime;

import fr.summumgames.launcher.Launcher;
import javafx.application.Platform;

import java.util.Objects;

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

public class UrlRuntimeGenerator {

    public static String getRuntimeUrl(Integer version) {
        String os = ((String) Objects.requireNonNull(System.getProperty("os.name"))).toLowerCase();
        String os_arch = ((String) Objects.requireNonNull(System.getProperty("os.arch"))).toLowerCase();

        if (os.contains("win")) {
            if (os_arch.contains("amd64")) {
                return "https://api.azul.com/zulu/download/community/v1.0/bundles/?java_version=" + version + "&jdk_version=" + version + "&zulu_version=" + version + "&os=windows&arch=amd64&hw_bitness=642&abi=soft_float&ext=zip&bundle_type=jre&javafx=false&release_status=ga&release_type=PSU&latest=true&support_term=lts";
            }  else {
                Launcher.getInstance().getLogger().err("L'architecture de votre processeur n'est pas compatible (32 bits)");
                Platform.exit();
                System.exit(1);
            }
        } else if (os.contains("mac")) {
            if (os_arch.contains("amd64")) {
                return "https://api.azul.com/zulu/download/community/v1.0/bundles/?java_version=" + version + "&jdk_version=" + version + "&zulu_version=" + version + "&os=macos&arch=amd64&hw_bitness=642&abi=soft_float&ext=zip&bundle_type=jre&javafx=true&release_status=ga&release_type=PSU&latest=true&support_term=lts";
            }else if (os_arch.contains("arm")) {
                return "https://api.azul.com/zulu/download/community/v1.0/bundles/?java_version=" + version + "&jdk_version=" + version + "&zulu_version=" + version + "&os=macos&arch=arm&hw_bitness=642&abi=soft_float&ext=zip&bundle_type=jre&javafx=true&release_status=ga&release_type=PSU&latest=true&support_term=lts";
            } else {
                Launcher.getInstance().getLogger().err("L'architecture de votre processeur n'est pas compatible (32 bits)");
                Platform.exit();
                System.exit(1);
            }
        } else if (os.contains("linux")) {
            if (os_arch.contains("amd64")) {
                return "https://api.azul.com/zulu/download/community/v1.0/bundles/?java_version=" + version + "&jdk_version=" + version + "&zulu_version=" + version + "&os=linux&arch=amd64&hw_bitness=642&abi=soft_float&ext=zip&bundle_type=jre&javafx=true&release_status=ga&release_type=PSU&latest=true&support_term=lts";
            }
        }

        return null;
    }
}
