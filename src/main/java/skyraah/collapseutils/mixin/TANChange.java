package skyraah.collapseutils.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import skyraah.collapseutils.integration.matteroverdrive.IMatterOverdriveAndroid;
import toughasnails.thirst.ThirstHandler;

/**
 * @author skyraah
 */
@Mixin(value = ThirstHandler.class, remap = false)
public abstract  class TANChange {
    /*@Shadow
    private int thirstLevel;
    @Shadow
    private float thirstHydrationLevel;
    @Shadow
    private float thirstExhaustionLevel;
    @Shadow
    private int thirstTimer;
    @Shadow
    private Vector3d movementVec;*/

    @Inject(
            method = "update",
            at = @At(
                    value = "HEAD",
                    remap = false)
            )
    private void injectUpdate(EntityPlayer player, World world, TickEvent.Phase phase, CallbackInfo ci)
    {
        if (
                !IMatterOverdriveAndroid.isPlayerAndroid(player)) {
            return;
        }
    }
    /**
     * @author skyraah
     * @reason Adding determination methods
     *//*
    @Overwrite
    public void update(EntityPlayer player, World world, TickEvent.Phase phase)
    {
        if (!SyncedConfig.getBooleanValue(GameplayOption.ENABLE_THIRST) || player.isCreative() ||
                IMatterOverdriveAndroid.isPlayerAndroid(player)) {
            return;
        }

        if (phase == TickEvent.Phase.START)
        {
            if (movementVec != null)
            {
                Vector3d movement = new Vector3d(player.posX, player.posY, player.posZ);
                movement.sub(movementVec); movement.absolute();
                int distance = (int)Math.round(movement.length() * 100.0F);

                if (distance > 0) {
                    applyMovementExhaustion(player, distance);
                }
            }
        }
        else if (phase == TickEvent.Phase.END)
        {
            this.movementVec = new Vector3d(player.posX, player.posY, player.posZ);

            EnumDifficulty enumdifficulty = world.getDifficulty();

            if (this.thirstExhaustionLevel > 4.0F)
            {
                this.thirstExhaustionLevel -= 4.0F;

                if (this.thirstHydrationLevel > 0.0F)
                {
                    this.thirstHydrationLevel = Math.max(this.thirstHydrationLevel - 1.0F, 0.0F);
                }
                else if (enumdifficulty != EnumDifficulty.PEACEFUL || SyncedConfig.getBooleanValue(GameplayOption.ENABLE_PEACEFUL))
                {
                    this.thirstLevel = Math.max(this.thirstLevel - 1, 0);
                }
            }

            if (this.thirstLevel <= 0)
            {
                ++this.thirstTimer;

                //Inflict thirst damage every 4 seconds
                if (this.thirstTimer >= 80)
                {
                    if ((enumdifficulty == EnumDifficulty.PEACEFUL && SyncedConfig.getBooleanValue(GameplayOption.ENABLE_PEACEFUL) && player.getHealth() > 10.0F) || (enumdifficulty == EnumDifficulty.EASY && player.getHealth() > 10.0F) || (enumdifficulty == EnumDifficulty.NORMAL && player.getHealth() > 1.0F) || enumdifficulty == EnumDifficulty.HARD)
                    {
                        player.attackEntityFrom(DamageSource.STARVE, 1.0F);
                    }

                    this.thirstTimer = 0;
                }
            }
            else
            {
                this.thirstTimer = 0;
            }

            //If thirst is too low, prevent the player from sprinting
            if (player.isSprinting() && thirstLevel <= 6)
            {
                player.setSprinting(false);
            }
        }
    }

    @Shadow
    protected abstract void applyMovementExhaustion(EntityPlayer player, int distance);*/
}
