package skyraah.collapseutils.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import skyraah.collapseutils.CollapseUtils;
import skyraah.collapseutils.block.tileentity.TileEntityBarrel;
import skyraah.collapseutils.client.render.RenderBarrel;

/**
 * @author skyraah
 */
@Mod.EventBusSubscriber(modid = CollapseUtils.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new RenderBarrel());
    }
}
