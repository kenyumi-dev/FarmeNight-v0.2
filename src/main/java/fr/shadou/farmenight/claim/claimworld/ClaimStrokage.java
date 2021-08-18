package fr.shadou.farmenight.claim.claimworld;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.storage.MapData;
import org.lwjgl.system.CallbackI;

import java.util.UUID;

public class ClaimStrokage extends WorldSavedData{

    private static final String DATA_NAME = "_ClaimData";
    private static int ID =0;
    private static String playerClaim;


    public ClaimStrokage() {
        super(DATA_NAME);
    }

    public static ClaimStrokage get(ServerWorld world, int ID, String playerClaim){
        ClaimStrokage.ID =ID;
        ClaimStrokage.playerClaim = playerClaim;
        return world.getDataStorage().computeIfAbsent(ClaimStrokage::new, DATA_NAME);
    }

    @Override
    public void load(CompoundNBT compound) {
        ID = compound.getInt("ID");
        playerClaim = compound.getString("player");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putInt("ID",ID);
        nbt.putString("player", playerClaim);
        return nbt;
    }
}

