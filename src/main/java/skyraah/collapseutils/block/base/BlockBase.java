package skyraah.collapseutils.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import skyraah.collapseutils.CollapseUtils;
import skyraah.collapseutils.register.BlockRegister;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author skyraah
 */
public class BlockBase extends Block {

    public String id;

    public BlockBase(Material materialIn, String name, CreativeTabs tab, SoundType soundType, String toolType, float hardness, int level) {
        super(materialIn);
        this.setHardness(hardness);
        this.setSoundType(soundType);
        this.setHarvestLevel(toolType, level);
        this.setCreativeTab(tab);
        this.setId(name);

        BlockRegister.BLOCKS.add(this);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format(CollapseUtils.MOD_ID + stack.getTranslationKey() + ".info"));
        } else {
            tooltip.add(I18n.format(CollapseUtils.MOD_ID + ".inventory.info"));
        }
    }
}
