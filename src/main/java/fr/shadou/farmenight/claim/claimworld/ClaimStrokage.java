package fr.shadou.farmenight.claim.claimworld;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
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
    private static BlockPos ZONE1;
    private static BlockPos ZONE2;
    private static String playerClaim;


    public ClaimStrokage() {
        super(DATA_NAME);
    }

    public static ClaimStrokage get(ServerWorld world){
        return world.getDataStorage().computeIfAbsent(ClaimStrokage::new, DATA_NAME);
    }

    @Override
    public void load(CompoundNBT compound) {
        this.setID(compound.getInt("ID"));
        this.setPlayerClaim(compound.getString("player"));
        this.setZone1(NBTUtil.readBlockPos(compound.getCompound("pos1")));
        this.setZone2(NBTUtil.readBlockPos(compound.getCompound("pos2")));
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {


        nbt.putInt("ID",ID);
        nbt.put("pos1",NBTUtil.writeBlockPos(ZONE1));
        nbt.put("pos2",NBTUtil.writeBlockPos(ZONE2));
        nbt.putString("player", playerClaim);
        return nbt;
    }

    public int getID(){
        return this.ID;
    }
    public String getPlayerClaim(){
        return this.playerClaim;
    }

    public BlockPos getZone1(){
        return this.ZONE1;
    }

    public BlockPos getZone2(){
        return this.ZONE2;
    }

    public void setID(int id){
        this.ID=id;
    }
    public void setPlayerClaim(String playerClaim){
        this.playerClaim=playerClaim;
    }

    public void setZone1(BlockPos zone1){
        this.ZONE1=zone1;
    }

    public void setZone2(BlockPos zone2){
       this.ZONE2=zone2;
    }

    public void generatClaim( String playerClaim, BlockPos zone1, BlockPos zone2){
        this.setID(this.ID-1);
        this.setPlayerClaim(playerClaim);
        this.setZone1(zone1);
        this.setZone2(zone2);
    }
}

