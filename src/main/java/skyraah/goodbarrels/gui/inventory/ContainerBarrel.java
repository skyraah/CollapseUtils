package skyraah.goodbarrels.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import skyraah.goodbarrels.block.tileentity.TileEntityBarrel;

/**
 * @author skyraah
 */
public class ContainerBarrel extends Container {

    private final IItemHandler input;
    private final IItemHandler output;

    public ContainerBarrel(EntityPlayer player, TileEntity tileEntityBarrel) {
        this.input = tileEntityBarrel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.output = tileEntityBarrel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.addSlotToContainer(new SlotItemHandler(this.input, 0, 53, 30){
            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 16;
            }
        });

        this.addSlotToContainer(new SlotItemHandler(this.output, 1, 107, 30){
            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 16;
            }

            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return false ;

            }

            @Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return true;
            }
        });

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 132));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        return null;
    }
}
