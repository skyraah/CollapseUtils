package skyraah.goodbarrels.block;

import net.darkhax.bookshelf.block.BlockSubType;
import net.darkhax.bookshelf.block.property.PropertyString;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * @author cyciling
 */
public class BlockBarrel extends BlockSubType {
    private static final String[] VARIANT = {"barrel", "barrel_open"};
    public BlockBarrel(String... variants) {
        super(Material.WOOD, MapColor.WOOD, variants);
        variants = BlockBarrel.VARIANT;
    }

    @Override
    public PropertyString getVariantProp() {
        return new PropertyString("variants", VARIANT);
    }
}
