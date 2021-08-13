package fr.shadou.farmenight.production.server.capability;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.capability.SerializableCapabilityProvider;
import fr.shadou.farmenight.money.capabilite.IMoney;
import fr.shadou.farmenight.money.capabilite.Money;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntArrayNBT;
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

public class CapaServer {

    @CapabilityInject(IServeur.class)
    public static final Capability<IServeur> SERVEUR_CAPABILITY = null;

    public static final Direction DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(Main.MODID, "serveur");

    public static void register() {
        CapabilityManager.INSTANCE.register(IServeur.class, new Capability.IStorage<IServeur>() {
            @Override
            public INBT writeNBT(final Capability<IServeur> capability, final IServeur instance, final Direction side) {
                IntArrayNBT nbt = new IntArrayNBT(new int[]{instance.getDurability(), instance.getFacture()});

                return nbt;
            }

            @Override
            public void readNBT(final Capability<IServeur> capability, final IServeur instance, final Direction side, final INBT nbt) {
                IntArrayNBT arrayNBT = (IntArrayNBT) nbt;

                instance.setDurability(arrayNBT.get(0).getAsInt());
                instance.setFacture(arrayNBT.get(1).getAsInt());
            }
        }, () -> new Serveur(null));
    }

    //public static LazyOptional<IServeur> getMoney(final Block block){
       // return block.getCapability(SERVEUR_CAPABILITY, DEFAULT_FACING);
    //}
    public static ICapabilityProvider createProvider(final IServeur serveur) {
        if (SERVEUR_CAPABILITY != null){
            return new SerializableCapabilityProvider<>(SERVEUR_CAPABILITY, DEFAULT_FACING, serveur);
        }
        return null;
    }

    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Block> event) {
        if (event.getObject() instanceof Block) {
            final Serveur serveur = new Serveur((Block) event.getObject());
            event.addCapability(ID, createProvider(serveur));
        }
    }
}

