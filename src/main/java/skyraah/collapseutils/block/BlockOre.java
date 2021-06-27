package skyraah.collapseutils.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import skyraah.collapseutils.block.base.BlockBase;
import skyraah.collapseutils.register.BlockRegister;

/**
 * @author skyraah
 */
public class BlockOre extends BlockBase {
    public BlockOre(String name, float hardness, int level, String oreDictType) {
        super(Material.ROCK, name, CreativeTabs.BUILDING_BLOCKS, SoundType.STONE, "pickaxe", hardness, level);
        BlockRegister.BLOCK_ORE_DICT_SECOND.put(this, "ore" + oreDictType);
        BlockRegister.BLOCK_ORE_DICT.add(this);
    }

}
