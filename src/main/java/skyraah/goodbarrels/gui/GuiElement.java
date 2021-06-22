package skyraah.goodbarrels.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import skyraah.goodbarrels.Goodbarrel;
import skyraah.goodbarrels.gui.inventory.ContainerBarrel;
import skyraah.goodbarrels.gui.inventory.GuiContainerBarrel;

import javax.annotation.Nullable;

/**
 * @author skyraah
 */
public class GuiElement implements IGuiHandler {
    public static final int GUI_BARREL = 1;

    public GuiElement() {
        NetworkRegistry.INSTANCE.registerGuiHandler(Goodbarrel.INSTANCE, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_BARREL) {
            return new ContainerBarrel(player, world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_BARREL) {
            return new GuiContainerBarrel(new ContainerBarrel(player, world.getTileEntity(new BlockPos(x, y, z))));
        }
        return null;
    }
}
