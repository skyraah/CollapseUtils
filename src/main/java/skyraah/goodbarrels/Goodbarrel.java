package skyraah.goodbarrels;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import skyraah.goodbarrels.block.BlockBarrel;
import skyraah.goodbarrels.gui.GuiElement;

import static skyraah.goodbarrels.Goodbarrel.MOD_ID;

/**
 * @author skyraah
 */
@Mod(
        modid = MOD_ID,
        name = Goodbarrel.MOD_NAME,
        version = Goodbarrel.VERSION
)
public class Goodbarrel {

    public static final String MOD_ID = "goodbarrel";
    public static final String MOD_NAME = "GoodBarrel";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static Goodbarrel INSTANCE;
    public static final RegistryHelper REGISTRY = new RegistryHelper().enableAutoRegistration();
    public static final Item BARREL_LID = new Item().setCreativeTab(CreativeTabs.MISC).setMaxStackSize(1).setNoRepair().setMaxDamage(0);
    public static final Block BARREL = new BlockBarrel();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        REGISTRY.registerBlock(BARREL, "barrel");
        REGISTRY.registerItem(BARREL_LID, "barrel_lid");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        new GuiElement();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
