package fr.shadou.farmenight.money.capabilite;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.capability.SerializableCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

import javax.annotation.Nullable;

public class CapaTaxe {

    @CapabilityInject(ITaxe.class)
    public static final Capability<ITaxe> TAXE_CAPABILITY = null;

    public static final Direction DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(Main.MODID,"taux");

    public static void register(){
        CapabilityManager.INSTANCE.register(ITaxe.class, new Capability.IStorage<ITaxe>() {
            @Override
            public INBT writeNBT(final Capability<ITaxe> capability, ITaxe instance, Direction side) {
                return IntNBT.valueOf(instance.getTaxe());
            }

            @Override
            public void readNBT(final Capability<ITaxe> capability, ITaxe instance, Direction side, INBT nbt) {
                instance.setTaxe(((IntNBT) nbt).getAsInt());
            }
        }, () -> new Taxe(null));
    }
    public static LazyOptional<ITaxe> getTaxe(final LivingEntity entity){
        return entity.getCapability(TAXE_CAPABILITY, DEFAULT_FACING);
    }

    public static ICapabilityProvider createProvider(final ITaxe taxe){
        return new SerializableCapabilityProvider<>(TAXE_CAPABILITY, DEFAULT_FACING, taxe);
    }

    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity) {
            final Taxe taxe = new Taxe((LivingEntity) event.getObject());
            event.addCapability(ID, createProvider(taxe));
        }
    }
    @Mod.EventBusSubscriber(modid = Main.MODID)
    private static class EvenHandler{

        @SubscribeEvent
        public static void playerClone(final PlayerEvent.Clone event) {
            getTaxe(event.getOriginal()).ifPresent(oldtaxe -> {
                getTaxe(event.getPlayer()).ifPresent(newTaxe -> {
                    newTaxe.setTaxe(oldtaxe.getTaxe());
                });
            });
        }

    }
}

