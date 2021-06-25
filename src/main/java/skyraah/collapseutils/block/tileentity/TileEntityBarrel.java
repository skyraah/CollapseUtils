package skyraah.collapseutils.block.tileentity;

import javax.annotation.Nullable;
import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author skyraah
 */
@SuppressWarnings("NullableProblems")
public class TileEntityBarrel extends TileEntityBasicTickable implements ITickable {

    public static final int SIZE = 2;

    public final ItemStackHandler inventory = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            TileEntityBarrel.this.markDirty();
        }
    };

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void writeNBT(NBTTagCompound dataTag) {
        dataTag.setTag("inventory", this.inventory.serializeNBT());
    }

    @Override
    public void readNBT(NBTTagCompound dataTag) {
        this.inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
    }

    @Override
    public void onEntityUpdate() {
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }
}
