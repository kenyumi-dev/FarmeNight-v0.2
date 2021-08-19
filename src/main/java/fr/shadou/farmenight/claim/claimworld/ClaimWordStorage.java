package fr.shadou.farmenight.claim.claimworld;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

public class ClaimWordStorage extends WorldSavedData {
    private static final String DATA_NAME = "_CLAIMDATA";

    public ClaimWordStorage() {
        super(DATA_NAME);
    }

    public static ClaimWordStorage get(ServerWorld world){
        return (ClaimWordStorage) world.getDataStorage().computeIfAbsent(ClaimWordStorage::new, DATA_NAME);
    }

    @Override
    public void load(CompoundNBT p_76184_1_) {

    }

    @Override
    public CompoundNBT save(CompoundNBT p_189551_1_) {
        return null;
    }
}
