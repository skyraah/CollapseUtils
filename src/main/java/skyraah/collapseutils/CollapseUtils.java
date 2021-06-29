package skyraah.collapseutils;

import crafttweaker.CraftTweakerAPI;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skyraah.collapseutils.block.BlockBarrel;
import skyraah.collapseutils.gui.GuiElement;
import skyraah.collapseutils.integration.crafttweaker.matteroverdrive.IMatterOverdrivePlayerExpansion;
import skyraah.collapseutils.proxy.CommonProxy;
import skyraah.collapseutils.util.RegisterHandler;

import static skyraah.collapseutils.CollapseUtils.MOD_ID;

/**
 * @author skyraah
 */
@Mod(
        modid = MOD_ID,
        name = CollapseUtils.MOD_NAME,
        version = CollapseUtils.VERSION
)
public class CollapseUtils {

    public static final String MOD_ID = "collapseutils";
    public static final String MOD_NAME = "CollapseUtils";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static CollapseUtils INSTANCE;
    @SidedProxy(clientSide = "skyraah.collapseutils.proxy.ClientProxy", serverSide = "skyraah.collapseutils.proxy.CommonProxy")
    public static CommonProxy proxy;
    /*public static final RegistryHelper REGISTRY = new RegistryHelper().enableAutoRegistration();
    public static final Item BARREL_LID = new Item().setCreativeTab(CreativeTabs.MISC).setMaxStackSize(1).setNoRepair().setMaxDamage(0);
    public static final Block BARREL = new BlockBarrel();*/

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        RegisterHandler.onRegister();
        /*REGISTRY.registerBlock(BARREL, "barrel");
        REGISTRY.registerItem(BARREL_LID, "barrel_lid");*/
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        if (!Loader.isModLoaded("randomtweaker") && Loader.isModLoaded("matteroverdrive")) {
            CraftTweakerAPI.registerClass(IMatterOverdrivePlayerExpansion.class);
        }
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        RegisterHandler.blockOreDictRegister();
        new GuiElement();
    }


}
