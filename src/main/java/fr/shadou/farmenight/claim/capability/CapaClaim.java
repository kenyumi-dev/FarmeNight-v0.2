package fr.shadou.farmenight.claim.capability;

import fr.shadou.farmenight.Main;
import fr.shadou.farmenight.capability.SerializableCapabilityProvider;
import fr.shadou.farmenight.money.capabilite.IMoney;
import fr.shadou.farmenight.money.capabilite.Money;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.*;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class CapaClaim {

    private static List<Integer> reg = new ArrayList();

    @CapabilityInject(IClaim.class)
    public static final Capability<IClaim> CLAIM_CAPABILITY = null;

    public static final Direction DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(Main.MODID, "claim");

    public static void register() {
        CapabilityManager.INSTANCE.register(IClaim.class, new Capability.IStorage<IClaim>() {
            @Override
            public INBT writeNBT(final Capability<IClaim> capability, final IClaim instance, final Direction side) {
                CompoundNBT compound = new CompoundNBT();
                reg.add(instance.gettest());

                IntNBT nbt = IntNBT.valueOf(instance.gettest());

                compound.putInt("test", instance.gettest());
                compound.put("coord0",NBTUtil.writeBlockPos(instance.getcoord0()));
                compound.put("coord1", NBTUtil.writeBlockPos(instance.getcoord1()));
                compound.putString("player", instance.getplayer());
                return nbt;
            }

            @Override
            public void readNBT(final Capability<IClaim> capability, final IClaim instance, final Direction side, final INBT nbt) {
                CompoundNBT compound = new CompoundNBT();

                IntArrayNBT arrayNBT = (IntArrayNBT) nbt;

                instance.settest(((IntNBT) nbt).getAsInt());

                instance.setcoord0(NBTUtil.readBlockPos(compound.getCompound("coord0")));
                instance.setcoord1(NBTUtil.readBlockPos(compound.getCompound("coord1")));
            }
        }, () -> new Claim(null));
    }

    public static LazyOptional<IClaim> getWord(final World world){
        return world.getCapability(CLAIM_CAPABILITY,DEFAULT_FACING);
    }
    public static ICapabilityProvider createProvider(final IClaim claim) {
        if (CLAIM_CAPABILITY != null){
            return new SerializableCapabilityProvider<>(CLAIM_CAPABILITY, DEFAULT_FACING, claim);
        }
        return null;
    }

    @Mod.EventBusSubscriber(modid = Main.MODID)
    private static class EvenHandler {

        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<World> event) {
            if (event.getObject() instanceof World) {
                final Claim claim = new Claim((World) event.getObject());
                event.addCapability(ID, createProvider(claim));
            }
        }
    }
}
