package skyraah.collapseutils.util;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IRegistryDelegate;
import skyraah.collapseutils.CollapseUtils;
import skyraah.collapseutils.block.base.BlockBase;
import skyraah.collapseutils.register.BlockRegister;

import java.util.Map;

/**
 * @author skyraah
 */
public class RegisterHandler {
    private static final RegistryHelper REGISTRY = new RegistryHelper().enableAutoRegistration();

    public static void onRegister() {
        for (BlockBase block : BlockRegister.BLOCKS) {
            REGISTRY.registerBlock(block, block.getId());
        }
    }

    public static void blockOreDictRegister() {
        for (BlockBase block : BlockRegister.BLOCK_ORE_DICT) {
            OreDictionary.registerOre(StringUntil.toLowerCamelCase("ore_" + block.getId()), block);
        }

        for (Map.Entry<Block, String> blockBase : BlockRegister.BLOCK_ORE_DICT_SECOND.entrySet()) {
            OreDictionary.registerOre(blockBase.getValue(), blockBase.getKey());
        }
    }
}
