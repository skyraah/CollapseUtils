package skyraah.goodbarrels.block.tileentity;

import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author skyraah
 */
public class TileEntityBarrel extends TileEntityBasicTickable implements ITickable {

    public final ItemStackHandler inventory = new ItemStackHandler(2){};

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new IItemHandler() {
                @Override
                public int getSlots() {
                    return inventory.getSlots();
                }
                @Override
                public ItemStack getStackInSlot(int slot) {
                    return inventory.getStackInSlot(slot);
                }
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    if (slot == 0) {
                        return inventory.insertItem(0, stack, simulate);
                    }
                    return stack;
                }
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if (slot == 1) {
                        return inventory.extractItem(1, amount, simulate);
                    }
                    return ItemStack.EMPTY;
                }
                @Override
                public int getSlotLimit(int slot) {
                    return inventory.getSlotLimit(slot);
                }
                @Override
                public boolean isItemValid(int slot, ItemStack stack) {
                    return slot == 0;
                }
            });
    } else {
        return super.getCapability(capability, facing);
    }
    }

    @Override
    public void writeNBT(NBTTagCompound dataTag) {
        dataTag.setTag("Inventory", this.inventory.serializeNBT());

    }

    @Override
    public void readNBT(NBTTagCompound dataTag) {
        this.inventory.deserializeNBT(dataTag.getCompoundTag("Inventory"));
    }

    @Override
    public void onEntityUpdate() {
    }
}
