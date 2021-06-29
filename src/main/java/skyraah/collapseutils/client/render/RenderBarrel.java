package skyraah.collapseutils.client.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.client.model.animation.FastTESR;
import skyraah.collapseutils.block.tileentity.TileEntityBarrel;

/**
 * @author skyraah
 */
public class RenderBarrel extends FastTESR<TileEntityBarrel> {
    @Override
    public void renderTileEntityFast(TileEntityBarrel te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        /*GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.5,0.2,0.4);
        GlStateManager.scale(0.6, 0.6, 0.6);
        Minecraft.getMinecraft().getRenderManager().renderEntity(entityItem, 0,0,0,0,0, false);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();*/
    }



}
