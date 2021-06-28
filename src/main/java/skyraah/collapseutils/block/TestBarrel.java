package skyraah.collapseutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import skyraah.collapseutils.block.tileentity.TETestBarrel;

/**
 * @author skyraah
 */
public class TestBarrel extends Block implements ITileEntityProvider {

    public TestBarrel() {
        super(Material.WOOD);
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TETestBarrel();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        return ((TETestBarrel)world.getTileEntity(pos)).activate(world,pos,state,player,hand,side,hitX,hitY,hitZ);
    }

    @Override
    public void fillWithRain(World worldIn, BlockPos pos) {
        float f = worldIn.getBiome(pos).getTemperature(pos);

        if (worldIn.getBiomeProvider().getTemperatureAtHeight(f, pos.getY()) >= 0.15F) {
            TileEntity te = worldIn.getTileEntity(pos);

            if (te != null && te instanceof TETestBarrel) {
                TETestBarrel telb = (TETestBarrel) te;
                FluidStack fluid = new FluidStack(FluidRegistry.WATER, 50);

                if (telb.getTank().canFillFluidType(fluid)) {
                    telb.getTank().fill(fluid, true);
                }
            }
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing side) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack){
        super.onBlockPlacedBy(world, pos, state, player, stack);
        if (stack.hasTagCompound()){
            TETestBarrel tile = (TETestBarrel)createNewTileEntity(world, getMetaFromState(state));
            world.setTileEntity(pos, tile);
            tile.getTank().readFromNBT(stack.getTagCompound());
            tile.markDirty();
        }
    }
}
