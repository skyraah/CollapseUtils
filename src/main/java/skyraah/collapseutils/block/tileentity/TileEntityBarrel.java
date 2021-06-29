package skyraah.collapseutils.block.tileentity;

import net.darkhax.bookshelf.block.tileentity.TileEntityBasicTickable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * @author skyraah
 */
@SuppressWarnings("NullableProblems")
public class TileEntityBarrel extends TileEntityBasicTickable implements ITickable {

    public static final int SIZE = 2;
    public static final int MAX_TEMP = 573;
    private FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 8) {
        @Override
        protected void onContentsChanged() {
            TileEntityBarrel.this.markDirty();
            getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 2);


        }
    };


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
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank);
        } else {
            return super.getCapability(capability, facing);
        }
    }

    public boolean activate(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (heldItem != ItemStack.EMPTY) {
            if (FluidUtil.getFluidHandler(heldItem) != null || heldItem.getItem() instanceof ItemBucket || heldItem.getItem() instanceof UniversalBucket) {
                FluidStack f = FluidUtil.getFluidContained(heldItem);
                if ((f != null && !f.getFluid().isGaseous() && !(f.getFluid().getTemperature() > MAX_TEMP)) || f == null) {
                    boolean didFill = FluidUtil.interactWithFluidHandler(player, hand, this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side));
                    if (didFill) {
                        this.world.addBlockEvent(this.pos, this.getBlockType(), 1, 0);
                        getWorld().notifyBlockUpdate(pos, state, state, 3);
                        this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), true);
                        this.markDirty();
                    }
                } return true;
            }
        } return false;
    }

    @Override
    public void writeNBT(NBTTagCompound dataTag) {
        dataTag.setTag("inventory", this.inventory.serializeNBT());
        dataTag.setTag("fluid", tank.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readNBT(NBTTagCompound dataTag) {
        this.inventory.deserializeNBT(dataTag.getCompoundTag("inventory"));
        tank.readFromNBT(dataTag.getCompoundTag("fluid"));
    }

    @Override
    public void onEntityUpdate() {
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    public FluidTank getTank() {
        return tank;
    }

    public Fluid getFluid() {
        if (tank.getFluid() != null) {
            return tank.getFluid().getFluid();
        } return null ;
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    public int getAmount() {
        return tank.getFluidAmount();
    }

    public int getCapacity() {
        return tank.getCapacity();
    }
}
