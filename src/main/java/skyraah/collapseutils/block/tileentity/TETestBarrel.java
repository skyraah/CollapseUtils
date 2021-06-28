package skyraah.collapseutils.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.TileFluidHandler;

import javax.annotation.Nullable;

/**
 * @author skyraah
 */
public class TETestBarrel extends TileFluidHandler {

    public static int capacity = Fluid.BUCKET_VOLUME * 16;
    public static final int MAX_TEMP = 573;

    public TETestBarrel() {
        super();
        tank = new FluidTank(capacity) {
            @Override
            protected void onContentsChanged() {
                markDirty();
                getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 2);
            }
        };
        tank.setTileEntity(this);
        tank.setCanFill(true);
        tank.setCanDrain(true);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    public int getCapacity() {
        return tank.getCapacity();
    }

    public int getAmount() {
        return tank.getFluidAmount();
    }

    public FluidTank getTank() {
        return tank;
    }

    public Fluid getFluid() {
        if (tank.getFluid() != null) {
            return tank.getFluid().getFluid();
        }
        return null;
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
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            getWorld().notifyBlockUpdate(pos, getWorld().getBlockState(pos), getWorld().getBlockState(pos), 3);
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), true);
            this.markDirty();
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }
}
