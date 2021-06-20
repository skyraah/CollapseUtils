package skyraah.goodbarrels;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import skyraah.goodbarrels.block.BlockBarrel;

/**
 * @author cyciling
 */
@Mod(
        modid = Goodbarrels.MOD_ID,
        name = Goodbarrels.MOD_NAME,
        version = Goodbarrels.VERSION
)
public class Goodbarrels {

    public static final String MOD_ID = "goodbarrels";
    public static final String MOD_NAME = "GoodBarrels";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static Goodbarrels INSTANCE;

    public static final RegistryHelper REGISTRY = new RegistryHelper().setTab(CreativeTabs.MISC).enableAutoRegistration();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        REGISTRY.registerBlock(new BlockBarrel(), "barrel");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
