package fr.shadou.farmenight.money.capabilite;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.capability.SerializableCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CapaMoney {

    @CapabilityInject(IMoney.class)
    public static final Capability<IMoney> MONEY_CAPABILITY = null;

    public static final Direction DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(Main.MODID, "money");

    public static void register() {
        CapabilityManager.INSTANCE.register(IMoney.class, new Capability.IStorage<IMoney>() {
            @Override
            public INBT writeNBT(final Capability<IMoney> capability, final IMoney instance, final Direction side) {
                return IntNBT.valueOf(instance.getMoney());
            }

            @Override
            public void readNBT(final Capability<IMoney> capability, final IMoney instance, final Direction side, final INBT nbt) {
                instance.setMoney(((IntNBT) nbt).getAsInt());
            }
        }, () -> new Money(null));
    }

    public static LazyOptional<IMoney> getMoney(final LivingEntity entity){
        return entity.getCapability(MONEY_CAPABILITY, DEFAULT_FACING);
    }
    public static ICapabilityProvider createProvider(final IMoney money) {
        if (MONEY_CAPABILITY != null){
        return new SerializableCapabilityProvider<>(MONEY_CAPABILITY, DEFAULT_FACING, money);
    }
        return null;
}


    @Mod.EventBusSubscriber(modid = Main.MODID)
    private static class EvenHandler{

        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof LivingEntity) {
                final Money money = new Money((LivingEntity) event.getObject());
                event.addCapability(ID, createProvider(money));
            }
        }
        @SubscribeEvent
        public static void playerClone(final PlayerEvent.Clone event) {
            getMoney(event.getOriginal()).ifPresent(oldmoney -> {
                getMoney(event.getPlayer()).ifPresent(newMaxHealth -> {
                    newMaxHealth.setMoney(oldmoney.getMoney());
                });
            });
        }

    }
}

