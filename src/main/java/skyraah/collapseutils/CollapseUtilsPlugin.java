package skyraah.collapseutils;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author skyraah
 */
@IFMLLoadingPlugin.Name("moretan")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class CollapseUtilsPlugin implements IFMLLoadingPlugin, ITweaker {

    @Override public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.moretan.json");
    }

    @Override public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {

    }

    @Override public String getLaunchTarget() {
        return "";
    }

    @Override public String[] getLaunchArguments() {
        return new String[0];
    }

    public static void initMixin() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.sanitybaubles.json");
    }

    @Override public void injectData(Map<String, Object> data) {
        try {
            ClassLoader appClassLoader = Launch.class.getClassLoader();
            MethodUtils.invokeMethod(appClassLoader, true, "addURL", this.getClass().getProtectionDomain().getCodeSource().getLocation());
            MethodUtils.invokeStaticMethod(appClassLoader.loadClass(this.getClass().getName()), "initMixin");
        } catch (Exception ignored) {}
    }

    @Override public String[] getASMTransformerClass() {
        return null;
    }

    @Override public String getModContainerClass() {
        return null;
    }

    @Override public String getSetupClass() {
        return null;
    }

    @Override public String getAccessTransformerClass() {
        return null;
    }
}
