package skyraah.collapseutils.register;

import net.minecraft.block.Block;
import skyraah.collapseutils.block.BlockOre;
import skyraah.collapseutils.block.base.BlockBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author skyraah
 */
public class BlockRegister {
    public static List<BlockBase> BLOCKS = new ArrayList<>();
    public static List<BlockBase> BLOCK_ORE_DICT = new ArrayList<>();
    public static Map<Block, String> BLOCK_ORE_DICT_SECOND = new HashMap<>();
    public static final Block
            CHALCOPYRITE = new BlockOre("chalcopyrite", 3.0F, 3, "Copper"),
            CHALCOCITE = new BlockOre("chalcocite", 3.0F, 3, "Copper"),
            TETRAHEDRITE = new BlockOre("tetrahedrite", 3.0F, 3, "Copper"),
            CUPRITE = new BlockOre("cuprite", 2.5F, 3, "Copper"),
            MALACHITE = new BlockOre("malachite", 4.0F, 4, "Copper"),
            HEMATITE = new BlockOre("hematite", 5.2F, 4, "Iron"),
            LIMONITE = new BlockOre("limonite", 4.5F, 4, "Iron"),
            ILMENITE = new BlockOre("ilmenite", 6.0F, 4, "Iron");
}
